package com.example.filesystemprocessor.domain.result;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class ProcessResult {

    @Getter
    private int totalProcessed = 0;
    @Getter
    private int successCount = 0;
    @Getter
    private int failureCount = 0;
    private final List<ErrorDetail> errors = new ArrayList<>();

    public void addSuccess(){
        totalProcessed++;
        successCount++;
    }

    public void addFailure(String fileName, ProcessError error){
        totalProcessed++;
        failureCount++;
        errors.add(new ErrorDetail(fileName, error));
    }

    public void merge(ProcessResult other){
        this.totalProcessed += other.totalProcessed;
        this.successCount += other.successCount;
        this.failureCount += other.failureCount;
        this.errors.addAll(other.errors);
    }

    public List<ErrorDetail> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public record ErrorDetail(String fileName, ProcessError error){
        @Override
        public String toString(){
            return "[" + error.getErrorType() + "] " + fileName + " → " + error.getMessage();
        }
    }

}
