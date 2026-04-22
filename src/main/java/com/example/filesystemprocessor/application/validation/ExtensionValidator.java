package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ValidationError;

import java.util.Optional;
import java.util.Set;

public class ExtensionValidator extends AbstractValidationHandler {

    private final Set<String> allowedExtensions;

    public ExtensionValidator(Set<String> allowedExtensions){
        this.allowedExtensions = allowedExtensions;
    }

    @Override
    public Optional<ValidationError> validate(File file){
        String name = file.getName().toLowerCase();
        boolean valid = allowedExtensions.stream().anyMatch(name::endsWith);

        if (!valid){
            return Optional.of(new ValidationError(
                    "extension", String.format("Extension no permitida para tipo %s: '%s'. Permitidas: %s", file.getFileType(), file.getName(), allowedExtensions)
            ));
        }
        return passToNext(file);
    }

}