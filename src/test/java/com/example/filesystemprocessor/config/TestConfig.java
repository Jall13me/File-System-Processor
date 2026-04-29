package com.example.filesystemprocessor.config;

import com.example.filesystemprocessor.application.notification.EmailNotifier;
import com.example.filesystemprocessor.application.notification.NotificationConfig;
import com.example.filesystemprocessor.application.notification.NotificationService;
import com.example.filesystemprocessor.application.notification.Notifier;
import com.example.filesystemprocessor.application.notification.SlackNotifier;
import com.example.filesystemprocessor.application.notification.SmsNotifier;
import com.example.filesystemprocessor.application.service.FileProcessorEngine;
import com.example.filesystemprocessor.application.strategy.ContractStrategy;
import com.example.filesystemprocessor.application.strategy.InvoiceStrategy;
import com.example.filesystemprocessor.application.strategy.ReportStrategy;
import com.example.filesystemprocessor.application.strategy.StrategyFactory;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import com.example.filesystemprocessor.infrastructure.persistence.InMemoryFileRepository;

import java.util.List;

public final class TestConfig {

    private TestConfig() {
    }

    public static FileProcessorEngine buildEngine() {
        return buildEngine(new InMemoryFileRepository());
    }

    public static FileProcessorEngine buildEngine(FileRepository repository) {

        List<Notifier> notifiers = List.of(
                new EmailNotifier(),
                new SlackNotifier(),
                new SmsNotifier()
        );

        NotificationConfig notificationConfig = new NotificationConfig();
        NotificationService notificationService = new NotificationService(notifiers, notificationConfig);

        InvoiceStrategy invoiceStrategy = new InvoiceStrategy(repository, notificationService);
        ContractStrategy contractStrategy = new ContractStrategy(repository, notificationService);
        ReportStrategy reportStrategy = new ReportStrategy(repository, notificationService);

        StrategyFactory strategyFactory = new StrategyFactory(
                invoiceStrategy,
                contractStrategy,
                reportStrategy
        );

        return new FileProcessorEngine(strategyFactory);
    }
}