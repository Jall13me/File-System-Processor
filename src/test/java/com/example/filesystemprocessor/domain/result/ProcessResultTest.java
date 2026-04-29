package com.example.filesystemprocessor.domain.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessResultTest {

    @Test
    void shouldRegisterSuccessAndFailure() {
        ProcessResult result = new ProcessResult();

        result.addSuccess();
        result.addFailure("invoice.xml", new ValidationError("content", "missing amount"));

        assertEquals(2, result.getTotalProcessed());
        assertEquals(1, result.getSuccessCount());
        assertEquals(1, result.getFailureCount());
        assertEquals(1, result.getErrors().size());
    }

    @Test
    void shouldMergeTwoResults() {
        ProcessResult first = new ProcessResult();
        first.addSuccess();

        ProcessResult second = new ProcessResult();
        second.addFailure("contract.txt", new ValidationError("extension", "invalid"));

        first.merge(second);

        assertEquals(2, first.getTotalProcessed());
        assertEquals(1, first.getSuccessCount());
        assertEquals(1, first.getFailureCount());
        assertEquals(1, first.getErrors().size());
    }

    @Test
    void shouldExposeErrorsAsUnmodifiableList() {
        ProcessResult result = new ProcessResult();
        result.addFailure("file.txt", new ValidationError("name", "invalid"));

        assertThrows(UnsupportedOperationException.class, () -> result.getErrors().clear());
    }
}
