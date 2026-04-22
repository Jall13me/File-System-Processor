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
public class ReportStrategy extends AbstractFileStrategy {

    public ReportStrategy(FileRepository repository, NotificationService notificationService) {
        super(repository, notificationService);
    }

    @Override
    protected ValidationHandler buildValidationChain() {
        return ValidationChainBuilder.buildForReport();
    }

    @Override
    protected Optional<TransformationError> transform(File file) {
        try {
            long totalRows = file.getContent().lines().count();
            String summary = String.format(
                    "[REPORT_SUMMARY] Archivo: %s | Total filas: %d | Generado: %s",
                    file.getName(), totalRows, java.time.LocalDateTime.now()
            );
            System.out.printf("[TRANSFORM] Resumen generado para '%s': %s%n",
                    file.getName(), summary);
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(new TransformationError(
                    "Fallo al generar resumen de reporte '" + file.getName() + "': " + e.getMessage()
            ));
        }
    }

    @Override
    protected String buildNotificationMessage(File file) {
        long rows = file.getContent() == null ? 0 : file.getContent().lines().count();
        return String.format("Reporte '%s' procesado. %d filas registradas.", file.getName(), rows);
    }
}