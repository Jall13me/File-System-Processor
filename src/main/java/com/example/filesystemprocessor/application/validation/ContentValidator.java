package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ValidationError;

import java.util.Optional;
import java.util.Set;

public class ContentValidator extends AbstractValidationHandler {

    private final Set<String> requiredFields;

    public ContentValidator(Set<String>requiredFields){
        this.requiredFields = requiredFields;
    }

    @Override
    public Optional<ValidationError> validate(File file){
        String content = file.getContent();

        if (content == null || content.isBlank()){
            return Optional.of(new ValidationError("content", "El contenido del archivo esta vacio"));
        }

        for (String field : requiredFields){
            if (!content.contains(field)){
                return Optional.of(new ValidationError("content",String.format("Campo obligatorio ausente: '%s'",field)
                ));
            }
        }

        return passToNext(file);

    }

}
