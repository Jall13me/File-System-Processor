package com.example.filesystemprocessor.domain.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessResultDtoTest {

    @Test
    void shouldCreateDtoFromProcessResult() {
        ProcessResult result = new ProcessResult();
        result.addSuccess();
        result.addFailure("invoice.pdf", new ValidationError("extension", "invalid extension"));

        ProcessResultDto dto = ProcessResultDto.from(result);

        assertEquals(2, dto.getTotalProcessed());
        assertEquals(1, dto.getSuccessCount());
        assertEquals(1, dto.getFailureCount());
        assertEquals(1, dto.getErrors().size());
        assertTrue(dto.getErrors().get(0).contains("VALIDATION"));
    }
}
