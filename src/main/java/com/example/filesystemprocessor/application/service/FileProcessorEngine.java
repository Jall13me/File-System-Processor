package com.example.filesystemprocessor.application.service;

import com.example.filesystemprocessor.application.strategy.FileProcessingStrategy;
import com.example.filesystemprocessor.application.strategy.StrategyFactory;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileSystemElement;
import com.example.filesystemprocessor.domain.model.Folder;
import com.example.filesystemprocessor.domain.result.ProcessResult;
import com.example.filesystemprocessor.domain.result.ProcessResultDto;
import com.example.filesystemprocessor.domain.result.UnsupportedTypeError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileProcessorEngine {

    private final StrategyFactory strategyFactory;

    public FileProcessorEngine(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public ProcessResultDto process(FileSystemElement element) {
        ProcessResult result = processElement(element);
        return ProcessResultDto.from(result);
    }

    private ProcessResult processElement(FileSystemElement element) {
        if (element.isDirectory()) {
            return processFolder((Folder) element);
        } else {
            return processFile((File) element);
        }
    }

    private ProcessResult processFolder(Folder folder) {
        ProcessResult folderResult = new ProcessResult();

        for (FileSystemElement child : folder.getChildren()) {
            ProcessResult childResult = processElement(child);
            folderResult.merge(childResult);
        }

        return folderResult;
    }

    private ProcessResult processFile(File file) {
        ProcessResult result = new ProcessResult();

        if (file.getFileType() == null) {
            result.addFailure(file.getName(), new UnsupportedTypeError(file.getName()));
            return result;
        }

        Optional<FileProcessingStrategy> strategy = strategyFactory.getStrategy(file.getFileType());

        if (strategy.isEmpty()) {
            result.addFailure(file.getName(), new UnsupportedTypeError(file.getName()));
            return result;
        }

        return strategy.get().process(file);
    }
}