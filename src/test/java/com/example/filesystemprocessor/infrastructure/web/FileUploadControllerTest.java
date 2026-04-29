package com.example.filesystemprocessor.infrastructure.web;

import com.example.filesystemprocessor.application.service.FileProcessorEngine;
import com.example.filesystemprocessor.config.TestConfig;
import com.example.filesystemprocessor.domain.model.FileType;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import com.example.filesystemprocessor.domain.result.ProcessResultDto;
import com.example.filesystemprocessor.infrastructure.persistence.InMemoryFileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadControllerTest {

    @Test
    void shouldUploadSingleFileAndListIt() {
        FileRepository repository = new InMemoryFileRepository();
        FileProcessorEngine engine = TestConfig.buildEngine(repository);
        FileUploadController controller = new FileUploadController(engine, repository);

        FileUploadRequest request = new FileUploadRequest(
                "invoice.xml",
                100L,
                "customerId=1 amount=100",
                FileType.INVOICE
        );

        ResponseEntity<ProcessResultDto> uploadResponse = controller.uploadFile(request);
        ResponseEntity<List<String>> listResponse = controller.listFiles();

        assertEquals(200, uploadResponse.getStatusCode().value());
        assertEquals(1, uploadResponse.getBody().getSuccessCount());
        assertEquals(List.of("invoice.xml"), listResponse.getBody());
    }

    @Test
    void shouldUploadFolderWithMixedResults() {
        FileRepository repository = new InMemoryFileRepository();
        FileProcessorEngine engine = TestConfig.buildEngine(repository);
        FileUploadController controller = new FileUploadController(engine, repository);

        FolderUploadRequest request = new FolderUploadRequest(
                "root",
                List.of(
                        new FileUploadRequest("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE),
                        new FileUploadRequest("contract.txt", 100L, "clientName=Acme signed=true", FileType.CONTRACT)
                ),
                List.of()
        );

        ResponseEntity<ProcessResultDto> response = controller.uploadFolder(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().getTotalProcessed());
        assertEquals(1, response.getBody().getSuccessCount());
        assertEquals(1, response.getBody().getFailureCount());
    }

    @Test
    void shouldClearRepository() {
        FileRepository repository = new InMemoryFileRepository();
        FileProcessorEngine engine = TestConfig.buildEngine(repository);
        FileUploadController controller = new FileUploadController(engine, repository);
        controller.uploadFile(new FileUploadRequest("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE));

        ResponseEntity<Void> response = controller.clearRepository();

        assertEquals(204, response.getStatusCode().value());
        assertTrue(repository.findAll().isEmpty());
    }
}
