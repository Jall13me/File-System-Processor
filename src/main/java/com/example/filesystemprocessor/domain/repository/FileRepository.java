package com.example.filesystemprocessor.domain.repository;

import com.example.filesystemprocessor.domain.model.File;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
    void save(File file);
    Optional<File> findByName(String name);
    List<File> findAll();
    void clear();
}
