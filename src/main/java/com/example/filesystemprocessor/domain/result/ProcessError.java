package com.example.filesystemprocessor.domain.result;

public sealed interface ProcessError permits ValidationError, TransformationError, StorageError{

    String getMessage();
    String getErrorType();
}
