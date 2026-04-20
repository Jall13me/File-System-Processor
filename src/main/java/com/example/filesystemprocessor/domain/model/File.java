package com.example.filesystemprocessor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class File implements FileSystemElement {

    private final String name;
    private final long size;
    private final String content;
    private final FileType fileType;

    @Override
    public boolean isDirectory(){
        return false;
    }

    @Override
    public List<FileSystemElement> getChildren(){
        return Collections.emptyList();
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public long getSize(){
        return size;
    }

    @Override
    public String getContent(){
        return content;
    }

    @Override
    public FileType getFileType(){
        return fileType;
    }

}
