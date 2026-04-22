package com.example.filesystemprocessor.application.validation;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ValidationError;

import java.util.Optional;

public class NameValidator extends AbstractValidationHandler{

    @Override
    public Optional<ValidationError> validate(File file){
        if (file.getName() == null || file.getName().isBlank()){
            return Optional.of(new ValidationError("name", "El nombre del archivo no puede estar vacio"));
        }
        return passToNext(file);
    }

}
