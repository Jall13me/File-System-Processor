package com.example.filesystemprocessor.application.strategy;

import com.example.filesystemprocessor.application.notification.NotificationService;
import com.example.filesystemprocessor.application.validation.ValidationHandler;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import com.example.filesystemprocessor.domain.result.*;

import java.util.Optional;

public abstract class AbstractFileStrategy implements FileProcessingStrategy {

    protected final FileRepository repository;
    protected final NotificationService notificationService;

    protected AbstractFileStrategy(FileRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public final ProcessResult process(File file) {
        ProcessResult result = new ProcessResult();

        Optional<ValidationError> validationError = buildValidationChain().validate(file);
        if (validationError.isPresent()) {
            result.addFailure(file.getName(), validationError.get());
            return result;
        }

        Optional<TransformationError> transformError = transform(file);
        if (transformError.isPresent()) {
            result.addFailure(file.getName(), transformError.get());
            return result;
        }

        Optional<StorageError> storageError = store(file);
        if (storageError.isPresent()) {
            result.addFailure(file.getName(), storageError.get());
            return result;
        }

        notificationService.send(file, buildNotificationMessage(file));

        result.addSuccess();
        return result;
    }

    protected abstract ValidationHandler buildValidationChain();

    protected Optional<TransformationError> transform(File file) {
        return Optional.empty();
    }

    protected Optional<StorageError> store(File file) {
        try {
            repository.save(file);
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(new StorageError(e.getMessage()));
        }
    }

    protected String buildNotificationMessage(File file) {
        return String.format("Archivo '%s' de tipo %s procesado exitosamente.", file.getName(), file.getFileType());
    }
}