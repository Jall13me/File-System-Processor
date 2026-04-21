package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import com.example.filesystemprocessor.domain.result.ProcessError;
import com.example.filesystemprocessor.domain.result.ValidationError;

public class ContentValidator implements ValidationHandler {

    private ValidationHandler next;

    @Override
    public ValidationHandler setNext(ValidationHandler next){
        this.next = next;
        return next;
    }

    @Override
    public ProcessError validate(File file){
        String content = file.getContent();

        if (content == null || content.trim().isEmpty()){
            return new ValidationError("content", "El contenido del archivo esta vacio");
        }

        return switch (file.getFileType()){
            case INVOICE -> validateInvoiceContent(content);
            case CONTRACT -> validateContractContent(content);
            case REPORT -> validateReportContent(content);
        };

    }

    private ProcessError validateInvoiceContent(String content){
        if (!content.contains("customerId") || !content.contains("amount")){
            return new ValidationError("content", "La factura debe contener 'customerId' y 'amount'");
        }
        return null;
    }

    private ProcessError validateContractContent(String content){
        if (!content.contains("clientName") || !content.contains("signed=true")){
            return new ValidationError("content", "El contrato debe contar con 'clientName' y 'signed=true'");
        }
        return null;
    }

    private ProcessError validateReportContent (String content){
        long lineCount = content.lines().count();
        if (lineCount < 3){
            return new ValidationError("content", "El reporte debe tener al menos 3 filas");
        }
        return null;
    }


}


