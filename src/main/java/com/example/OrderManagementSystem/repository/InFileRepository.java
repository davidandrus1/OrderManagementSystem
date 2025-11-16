package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.BaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class InFileRepository<T extends BaseModel> extends BaseRepository<T> {

    protected final String filePath;
    protected final File file;
    protected final ObjectMapper objectMapper;
    private final Class<T> modelClass;

    public InFileRepository(String filePath, Class<T> modelClass) {
        super();
        this.filePath = filePath;
        this.file = new File(filePath);
        this.objectMapper = new ObjectMapper();
        this.modelClass = modelClass;
        this.loadItems();
    }

    @Override
    protected void loadItems() {
        try{
            this.items = this.objectMapper.readValue(
                    this.file,
                    this.objectMapper.getTypeFactory().constructCollectionType(List.class, this.modelClass));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void saveItems() {
        try
        {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.items);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}