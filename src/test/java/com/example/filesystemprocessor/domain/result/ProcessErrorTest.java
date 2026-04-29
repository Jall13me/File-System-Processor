package com.example.filesystemprocessor.domain.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessErrorTest {

    @Test
    void shouldBuildValidationErrorMessage() {
        ValidationError error = new ValidationError("content", "Campo obligatorio ausente");

        assertEquals("VALIDATION", error.getErrorType());
        assertTrue(error.getMessage().contains("content"));
        assertTrue(error.getMessage().contains("Campo obligatorio ausente"));
    }

    @Test
    void shouldBuildStorageErrorMessage() {
        StorageError error = new StorageError("disk full");

        assertEquals("STORAGE", error.getErrorType());
        assertTrue(error.getMessage().contains("disk full"));
    }

    @Test
    void shouldBuildTransformationErrorMessage() {
        TransformationError error = new TransformationError("invalid format");

        assertEquals("TRANSFORMATION", error.getErrorType());
        assertTrue(error.getMessage().contains("invalid format"));
    }

    @Test
    void shouldBuildUnsupportedTypeErrorMessage() {
        UnsupportedTypeError error = new UnsupportedTypeError("unknown.file");

        assertEquals("UNSUPPORTED_TYPE", error.getErrorType());
        assertTrue(error.getMessage().contains("unknown.file"));
    }
}