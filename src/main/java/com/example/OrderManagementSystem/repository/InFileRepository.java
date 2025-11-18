package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.BaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

// Clasa implementează RepositoryInterface<T> (așa cum cere BaseService)
public abstract class InFileRepository<T extends BaseModel> implements RepositoryInterface<T> {

    // Logica In-Memory
    protected List<T> items;
    private final AtomicLong idGenerator = new AtomicLong(0);

    // Logica de Persistență JSON
    protected final File file;
    protected final ObjectMapper objectMapper;
    private final Class<T> modelClass;

    public InFileRepository(String resourcePath, Class<T> modelClass) {
        this.objectMapper = new ObjectMapper();
        this.modelClass = modelClass;

        // FIX: Inițializarea listei înainte de orice operație I/O pentru a preveni NullPointerException
        this.items = new ArrayList<>();

        File resolvedFile;

        try {
            ClassPathResource resource = new ClassPathResource(resourcePath);

            if (resource.exists()) {
                resolvedFile = resource.getFile();
            } else {
                // Creează fișierul în calea de dezvoltare (src/main/resources/)
                resolvedFile = new File("src/main/resources/" + resourcePath);

                if (resolvedFile.getParentFile() != null && !resolvedFile.getParentFile().exists()) {
                    resolvedFile.getParentFile().mkdirs();
                }
            }

            this.file = resolvedFile;

            // Încărcăm datele (dacă fișierul există). loadItems() gestionează eșecul.
            loadItems();

            // Dacă nu a existat inițial sau a fost gol, îl salvăm.
            if (!resource.exists() || this.items.isEmpty()) {
                saveItems();
            }

            // Inițializăm generatorul de ID-uri (acum this.items nu este null)
            initializeIdGenerator();

        } catch (IOException e) {
            System.err.println("Eroare la inițializarea InFileRepository pentru: " + resourcePath);
            e.printStackTrace();
            throw new RuntimeException("Eroare la inițializarea bazei de date din fișier.", e);
        }
    }

    // --- Metode de Persistență ---

    protected void loadItems() {
        if (!this.file.exists() || this.file.length() == 0) {
            this.items = new ArrayList<>();
            return;
        }
        try {
            // ObjectMapper citește datele și le mapează la lista de obiecte
            List<T> loadedItems = this.objectMapper.readValue(
                    this.file,
                    this.objectMapper.getTypeFactory().constructCollectionType(List.class, this.modelClass));

            this.items = loadedItems != null ? loadedItems : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + this.file.getAbsolutePath());
            e.printStackTrace();
            this.items = new ArrayList<>();
        }
    }

    protected void saveItems() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Logica de ID-uri ---

    private void initializeIdGenerator() {
        // FIX: Am eliminat blocul 'try-catch' redundant exterior, deoarece erorile de tip NumberFormat
        // sunt tratate deja în interiorul stream-ului.
        long maxId = items.stream()
                .map(T::getId)
                .filter(Objects::nonNull)
                .map(id -> {
                    try {
                        return Long.parseLong(id);
                    } catch (NumberFormatException e) {
                        return 0L; // Ignoră ID-urile non-numerice
                    }
                })
                .max(Comparator.naturalOrder())
                .orElse(0L);
        idGenerator.set(maxId);
    }

    private String generateNextId() {
        return String.valueOf(idGenerator.incrementAndGet());
    }

    // --- Implementarea Metodelor CRUD (Respectând Semnăturile) ---

    // NOTĂ: Metoda save ar trebui să returneze T, dar păstrăm void conform semnăturii actuale.
    @Override
    public void save(T item) {
        T savedItem = item; // Folosim o copie locală

        if (savedItem.getId() == null || savedItem.getId().isEmpty()) {
            // CREATE: Asigură un ID nou
            savedItem.setId(generateNextId());
            items.add(savedItem);
        } else {
            // UPDATE: Înlocuiește obiectul existent
            boolean updated = items.stream()
                    .filter(i -> i.getId().equals(savedItem.getId()))
                    .findFirst()
                    .map(existingItem -> {
                        Collections.replaceAll(items, existingItem, savedItem);
                        return true;
                    })
                    .orElse(false);

            if (!updated) {
                items.add(savedItem);
            }
        }
        saveItems(); // Salvează modificările în JSON
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public Optional<T> findById(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(String id) {
        boolean removed = items.removeIf(item -> item.getId().equals(id));
        if (removed) {
            saveItems(); // Salvează în JSON doar dacă ștergerea a avut loc
        }
    }

    // FIX: Implementarea metodei delete(T item) care lipsea.
    @Override
    public void delete(T item) {
        if (item != null && item.getId() != null) {
            deleteById(item.getId());
        }
    }
}