package com.example.filesystemprocessor.application.service;

import com.example.filesystemprocessor.config.TestConfig;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import com.example.filesystemprocessor.domain.model.Folder;
import com.example.filesystemprocessor.domain.result.ProcessResultDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorEngineTest {

    private final FileProcessorEngine processorEngine = TestConfig.buildEngine();

    @Test
    void shouldProcessFolderWithValidAndInvalidFiles() {
        Folder folder = new Folder("root");

        folder.add(new File(
                "invoice.xml",
                100,
                "customerId=1 amount=100",
                FileType.INVOICE
        ));

        folder.add(new File(
                "contract.txt",
                100,
                "clientName=Acme signed=true",
                FileType.CONTRACT
        ));

        ProcessResultDto result = processorEngine.process(folder);

        assertEquals(2, result.getTotalProcessed());
        assertEquals(1, result.getSuccessCount());
        assertEquals(1, result.getFailureCount());
        assertFalse(result.getErrors().isEmpty());
    }

    @Test
    void shouldRejectFileWithoutType() {
        File file = new File(
                "unknown.file",
                100,
                "some content",
                null
        );

        ProcessResultDto result = processorEngine.process(file);

        assertEquals(1, result.getTotalProcessed());
        assertEquals(0, result.getSuccessCount());
        assertEquals(1, result.getFailureCount());
        assertFalse(result.getErrors().isEmpty());
    }
}