package com.example.filesystemprocessor.domain.result;

public record StorageError(String reason) implements ProcessError {

    @Override
    public String getMessage(){
        return "Error de almacenamiento: " + reason;
    }

    @Override
    public String getErrorType(){
        return "STORAGE";
    }

}
