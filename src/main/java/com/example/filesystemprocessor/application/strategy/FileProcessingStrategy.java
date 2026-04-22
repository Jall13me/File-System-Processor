package com.example.filesystemprocessor.application.strategy;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.result.ProcessResult;

public interface FileProcessingStrategy {
    ProcessResult process(File file);
}
