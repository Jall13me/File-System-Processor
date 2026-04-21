package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ProcessError;
import com.example.filesystemprocessor.domain.result.ValidationError;

public class NameValidator implements ValidationHandler{

    private ValidationHandler next;

    @Override
    public ValidationHandler setNext(ValidationHandler next){
        this.next = next;
        return next;
    }

    @Override
    public ProcessError validate(File file){
        if (file.getName()==null || file.getName().trim().isEmpty()){
            return new ValidationError("name", "El nombre del archivo no puede estar vacio");
        }

        return next !=null ? next.validate(file) : null;

    }


}
