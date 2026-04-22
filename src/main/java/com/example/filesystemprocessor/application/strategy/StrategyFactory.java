package com.example.filesystemprocessor.application.strategy;

import com.example.filesystemprocessor.domain.model.FileType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Component
public class StrategyFactory {

    private final Map<FileType, FileProcessingStrategy> strategies;

    public StrategyFactory(
            InvoiceStrategy invoiceStrategy,
            ContractStrategy contractStrategy,
            ReportStrategy reportStrategy
    ) {
        strategies = new EnumMap<>(FileType.class);
        strategies.put(FileType.INVOICE,  invoiceStrategy);
        strategies.put(FileType.CONTRACT, contractStrategy);
        strategies.put(FileType.REPORT,   reportStrategy);
    }

    public Optional<FileProcessingStrategy> getStrategy(FileType type) {
        return Optional.ofNullable(strategies.get(type));
    }
}