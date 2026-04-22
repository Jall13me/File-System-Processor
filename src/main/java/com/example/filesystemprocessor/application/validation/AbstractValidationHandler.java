package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ValidationError;

import java.util.Optional;

public abstract class AbstractValidationHandler implements  ValidationHandler{

    private ValidationHandler next;

    @Override
    public ValidationHandler setNext(ValidationHandler next){
        this.next = next;
        return next;
    }

    protected Optional<ValidationError> passToNext(File file){
        return next !=null ? next.validate(file) : Optional.empty();
    }

}
