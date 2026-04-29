package com.example.filesystemprocessor.infrastructure.web;

import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FolderUploadRequestTest {

    @Test
    void shouldExposeRecordValues(){
        FileUploadRequest file = new FileUploadRequest("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE);
        FolderUploadRequest request = new FolderUploadRequest("root", List.of(file), List.of());

        assertEquals("root",request.name());
        assertEquals(1,request.files().size());
        assertTrue(request.subFolders().isEmpty());

    }

}
