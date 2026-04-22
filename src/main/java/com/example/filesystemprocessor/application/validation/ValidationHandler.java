package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ValidationError;

import java.util.Optional;

public interface ValidationHandler {

    ValidationHandler setNext(ValidationHandler next);

    Optional<ValidationError> validate(File file);

}
