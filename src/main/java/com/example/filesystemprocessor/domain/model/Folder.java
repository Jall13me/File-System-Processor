package com.example.filesystemprocessor.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Getter
public class Folder implements FileSystemElement {

    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    public Folder(String name){
        this.name = name;
    }

    public void add(FileSystemElement element){
        children.add(element);
    }

    @Override
    public boolean isDirectory(){
        return true;
    }

    @Override
    public List<FileSystemElement> getChildren(){
        return Collections.unmodifiableList(children);
    }

    @Override
    public String getName(){return name;}

    @Override
    public String getContent(){
        return null;
    }

    @Override
    public FileType getFileType(){
        return null;
    }

    @Override
    public long getSize(){
        return children.stream().mapToLong(FileSystemElement::getSize).sum();
    }

    @Override
    public String toString(){
        return String.format("Folder{name='%s', children=%d}", name , children.size());
    }

}
