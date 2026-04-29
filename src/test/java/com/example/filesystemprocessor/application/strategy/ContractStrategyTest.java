package com.example.filesystemprocessor.application.strategy;

import com.example.filesystemprocessor.application.service.FileProcessorEngine;
import com.example.filesystemprocessor.config.TestConfig;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import com.example.filesystemprocessor.domain.result.ProcessResultDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractStrategyTest {

    private final FileProcessorEngine processorEngine = TestConfig.buildEngine();

    @Test
    void shouldProcessValidContractSuccessfully() {
        File file = new File(
                "contract.pdf",
                100,
                "clientName=Acme signed=true",
                FileType.CONTRACT
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getTotalProcessed());
        assertEquals(1, result.getSuccessCount());
        assertEquals(0, result.getFailureCount());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void shouldRejectContractWithInvalidExtension() {
        File file = new File(
                "contract.txt",
                100,
                "clientName=Acme signed=true",
                FileType.CONTRACT
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getTotalProcessed());
        assertEquals(0, result.getSuccessCount());
        assertEquals(1, result.getFailureCount());
        assertFalse(result.getErrors().isEmpty());
    }

    @Test
    void shouldRejectUnsignedContract() {
        File file = new File(
                "contract.pdf",
                100,
                "clientName=Acme signed=false",
                FileType.CONTRACT
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getTotalProcessed());
        assertEquals(0, result.getSuccessCount());
        assertEquals(1, result.getFailureCount());
        assertFalse(result.getErrors().isEmpty());
    }
}