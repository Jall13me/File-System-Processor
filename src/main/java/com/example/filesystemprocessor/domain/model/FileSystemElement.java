package com.example.filesystemprocessor.domain.model;

import java.util.List;

public interface FileSystemElement {
    String getName();
    long getSize();
    boolean isDirectory();
    List<FileSystemElement> getChildren();
    String getContent();
    FileType getFileType();
}
