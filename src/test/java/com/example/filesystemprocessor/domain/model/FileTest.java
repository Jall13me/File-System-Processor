package com.example.filesystemprocessor.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @Test
    void shouldRepresentRegularFile(){

        File file = new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE);

        assertEquals("invoice.xml",file.getName());
        assertEquals(100L, file.getSize());
        assertEquals("customerId=1 amount=100", file.getContent());
        assertEquals(FileType.INVOICE, file.getFileType());
        assertFalse(file.isDirectory());
        assertTrue(file.getChildren().isEmpty());
    }

}
