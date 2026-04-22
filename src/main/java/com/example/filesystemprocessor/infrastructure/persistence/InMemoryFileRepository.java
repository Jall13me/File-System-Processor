package com.example.filesystemprocessor.infrastructure.persistence;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFileRepository implements FileRepository {

    private final Map<String , File> store = new LinkedHashMap<>();

    @Override
    public void save(File file){
        store.put(file.getName(), file);
    }

    @Override
    public Optional<File> findByName(String name){
        return Optional.ofNullable(store.get(name));
    }

    @Override
    public List<File> findAll(){
        return Collections.unmodifiableList(new ArrayList<>(store.values()));
    }

    @Override
    public void clear(){
        store.clear();
    }

}
