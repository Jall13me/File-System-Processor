package com.example.filesystemprocessor.application.strategy;

import com.example.filesystemprocessor.application.notification.NotificationService;
import com.example.filesystemprocessor.application.validation.ValidationChainBuilder;
import com.example.filesystemprocessor.application.validation.ValidationHandler;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import org.springframework.stereotype.Component;

@Component
public class ContractStrategy extends AbstractFileStrategy {

    public ContractStrategy(FileRepository repository, NotificationService notificationService) {
        super(repository, notificationService);
    }

    @Override
    protected ValidationHandler buildValidationChain() {
        return ValidationChainBuilder.buildForContract();
    }

    @Override
    protected String buildNotificationMessage(File file) {
        return String.format("Contrato firmado recibido: '%s'. Requiere revisión legal.", file.getName());
    }
}