package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.result.ProcessError;
import com.example.filesystemprocessor.domain.result.ValidationError;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;

import java.util.Locale;


public class ExtensionValidator implements ValidationHandler {

    private ValidationHandler next;

    @Override
    public ValidationHandler setNext(ValidationHandler next){
        this.next = next;
        return next;
    }

    @Override
    public ProcessError validate(File file){
        String name = file.getName().toLowerCase();

        boolean valid = switch (file.getFileType()){
            case INVOICE -> name.endsWith(".xml") || name.endsWith(".json");
            case CONTRACT -> name.endsWith(".pdf");
            case REPORT -> name.endsWith(".csv") || name.endsWith(".xlsx");
        };
        if (!valid){
            return new ValidationError("extension","Extension no permitida para tipo" + file.getFileType() + ": " + file.getName());
        }

        return next != null ? next.validate(file): null;
    }

}
