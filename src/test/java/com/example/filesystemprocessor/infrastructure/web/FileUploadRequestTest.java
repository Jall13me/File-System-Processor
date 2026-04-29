package com.example.filesystemprocessor.infrastructure.web;

import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileUploadRequestTest {

    @Test
    void shouldExposeRecordValues(){
        FileUploadRequest request = new FileUploadRequest("invoice.xml",100L,"customerId=1 amount=100",FileType.INVOICE);

        assertEquals("invoice.xml",request.name());
        assertEquals(100L , request.size());
        assertEquals("customerId=1 amount=100",request.content());
        assertEquals(FileType.INVOICE,request.fileType());

    }

}
