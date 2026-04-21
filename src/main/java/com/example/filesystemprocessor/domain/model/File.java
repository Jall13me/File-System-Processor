package com.example.filesystemprocessor.domain.model;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class File implements FileSystemElement {

    private final String name;
    private final long size;
    private final String content;
    private final FileType fileType;

    public File(String name , long size , String content, FileType fileType){
        this.name = name;
        this.size = size;
        this.content = content;
        this.fileType = fileType;

    }

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

    @Override
    public String toString(){
        return String.format("File{name='%s', type=%s}");
    }

}
