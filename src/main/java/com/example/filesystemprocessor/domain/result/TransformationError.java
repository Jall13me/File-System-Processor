package com.example.filesystemprocessor.domain.result;

public record TransformationError(String reason) implements ProcessError {

    @Override
    public String getMessage(){
        return "Error durante la transformacion: " + reason;
    }

    @Override
    public String getErrorType(){
        return "TRANSFORMATION";
    }

}
