package com.example.filesystemprocessor.infrastructure.persistence;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryFileRepositoryTest {

    @Test
    void shouldSaveAndFindFileByName() {
        InMemoryFileRepository repository = new InMemoryFileRepository();
        File file = new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE);

        repository.save(file);

        assertTrue(repository.findByName("invoice.xml").isPresent());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldReplaceFileWithSameName() {
        InMemoryFileRepository repository = new InMemoryFileRepository();

        repository.save(new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE));
        repository.save(new File("invoice.xml", 200L, "customerId=2 amount=200", FileType.INVOICE));

        assertEquals(1, repository.findAll().size());
        assertEquals(200L, repository.findByName("invoice.xml").orElseThrow().getSize());
    }

    @Test
    void shouldClearRepository() {
        InMemoryFileRepository repository = new InMemoryFileRepository();
        repository.save(new File("report.csv", 100L, "a,b\n1,2\n3,4", FileType.REPORT));

        repository.clear();

        assertTrue(repository.findAll().isEmpty());
    }
}
