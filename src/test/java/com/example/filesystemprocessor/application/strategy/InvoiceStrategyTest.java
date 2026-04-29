package com.example.filesystemprocessor.application.strategy;

import com.example.filesystemprocessor.application.service.FileProcessorEngine;
import com.example.filesystemprocessor.config.TestConfig;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import com.example.filesystemprocessor.domain.result.ProcessResultDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceStrategyTest {

    private final FileProcessorEngine processorEngine = TestConfig.buildEngine();

    @Test
    void shouldProcessValidInvoiceSuccessfully() {
        File file = new File(
                "invoice.xml",
                100,
                "customerId=1 amount=100",
                FileType.INVOICE
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getTotalProcessed());
        assertEquals(1, result.getSuccessCount());
        assertEquals(0, result.getFailureCount());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void shouldRejectInvoiceWithInvalidExtension() {
        File file = new File(
                "invoice.pdf",
                100,
                "customerId=1 amount=100",
                FileType.INVOICE
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getFailureCount());
        assertFalse(result.getErrors().isEmpty());
    }

    @Test
    void shouldRejectInvoiceWithoutCustomerId() {
        File file = new File(
                "invoice.xml",
                100,
                "amount=100",
                FileType.INVOICE
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getFailureCount());
        assertFalse(result.getErrors().isEmpty());
    }
}