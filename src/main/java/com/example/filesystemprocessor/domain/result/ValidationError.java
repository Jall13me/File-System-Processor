package com.example.filesystemprocessor.domain.result;

public record ValidationError(
        String field,
        String reason)implements ProcessError {
    @Override
    public String getMessage(){
        return "Error de validacion en '"+ field + "':" + reason;
    }
    @Override
    public String getErrorType(){
        return "VALIDATION";
    }

}
