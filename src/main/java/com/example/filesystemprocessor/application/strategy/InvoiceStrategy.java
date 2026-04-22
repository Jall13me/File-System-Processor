package com.example.filesystemprocessor.application.strategy;

import com.example.filesystemprocessor.application.notification.NotificationService;
import com.example.filesystemprocessor.application.validation.ValidationChainBuilder;
import com.example.filesystemprocessor.application.validation.ValidationHandler;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import com.example.filesystemprocessor.domain.result.TransformationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceStrategy extends AbstractFileStrategy {

    public InvoiceStrategy(FileRepository repository, NotificationService notificationService) {
        super(repository, notificationService);
    }

    @Override
    protected ValidationHandler buildValidationChain() {
        return ValidationChainBuilder.buildForInvoice();
    }

    @Override
    protected Optional<TransformationError> transform(File file) {
        try {
            String raw = file.getContent();

            String normalized = "[INVOICE_NORMALIZED] " + raw
                    .replaceAll("\\s+", " ")
                    .trim();

            System.out.printf("[TRANSFORM] Factura '%s' normalizada: %s%n",
                    file.getName(), normalized);

            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(new TransformationError(
                    "Fallo al normalizar factura '" + file.getName() + "': " + e.getMessage()
            ));
        }
    }

    @Override
    protected String buildNotificationMessage(File file) {
        return String.format("Nueva factura recibida: '%s'. Pendiente de revisión contable.", file.getName());
    }
}