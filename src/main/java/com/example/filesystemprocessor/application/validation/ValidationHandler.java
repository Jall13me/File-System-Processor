package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ProcessError;

public interface ValidationHandler {

    ValidationHandler setNext(ValidationHandler next);

    ProcessError validate(File file);

}
