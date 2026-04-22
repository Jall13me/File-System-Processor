package com.example.filesystemprocessor.domain.result;

public final class UnsupportedTypeError implements ProcessError {

    private final String fileName;

    public UnsupportedTypeError(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMessage() {
        return "No existe una estrategia de procesamiento para el tipo de archivo: " + fileName;
    }

    @Override
    public String getErrorType() { return "UNSUPPORTED_TYPE"; }
}