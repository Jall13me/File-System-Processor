package com.example.filesystemprocessor.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FolderTest {

    @Test
    void shouldRepresentDirectory(){
        Folder folder = new Folder("root");

        folder.add(new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE));
        folder.add(new File("contract.pdf", 250L, "clientName=Acme signed=true", FileType.CONTRACT));

        assertEquals("root", folder.getName());
        assertTrue(folder.isDirectory());
        assertEquals(2,folder.getChildren().size());
        assertEquals(350L, folder.getSize());
        assertNull(folder.getContent());
        assertNull(folder.getFileType());
    }

    @Test
    void ShouldExposeChildrenAsUnmodifiableList(){
        Folder folder = new Folder("root");
        folder.add(new File("report.csv", 10L, "a,b\n1,2\n3,4", FileType.REPORT));

        assertThrows(UnsupportedOperationException.class, ()->
                folder.getChildren().add(new File("other.csv", 1L, "x", FileType.REPORT)));
    }

}
