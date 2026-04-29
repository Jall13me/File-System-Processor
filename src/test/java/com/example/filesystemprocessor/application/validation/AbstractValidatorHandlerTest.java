package com.example.filesystemprocessor.application.validation;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import com.example.filesystemprocessor.domain.result.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AbstractValidationHandlerTest {

    @Test
    void shouldPassToNextHandler() {
        TestHandler first = new TestHandler(Optional.empty());
        TestHandler second = new TestHandler(Optional.of(new ValidationError("field", "error")));
        File file = new File("file.txt", 1L, "content", FileType.REPORT);

        first.setNext(second);

        assertTrue(first.validate(file).isPresent());
        assertTrue(second.wasCalled);
    }

    private static class TestHandler extends AbstractValidationHandler {
        private final Optional<ValidationError> error;
        private boolean wasCalled;

        private TestHandler(Optional<ValidationError> error) {
            this.error = error;
        }

        @Override
        public Optional<ValidationError> validate(File file) {
            wasCalled = true;
            return error.isPresent() ? error : passToNext(file);
        }
    }
}
