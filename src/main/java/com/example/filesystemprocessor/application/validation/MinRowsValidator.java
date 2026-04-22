package com.example.filesystemprocessor.application.validation;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ValidationError;

import java.util.Optional;

public class MinRowsValidator extends AbstractValidationHandler {

    private final int minRows;

    public MinRowsValidator(int minRows){
        this.minRows = minRows;
    }

    @Override
    public Optional<ValidationError> validate(File file) {
        long lines = file.getContent() == null ? 0 : file.getContent().lines().count();

        if (lines < minRows){
            return Optional.of(new ValidationError(
                    "rowCount",String.format("El reporte tiene %d filas, se requieren al menos %d",lines,minRows)
            ));
        }
        return passToNext(file);
    }

}
