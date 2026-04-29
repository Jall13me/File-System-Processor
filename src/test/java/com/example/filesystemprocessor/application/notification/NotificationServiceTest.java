package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldRejectNullOrEmptyNotifierList() {
        assertThrows(IllegalArgumentException.class, () -> new NotificationService(null, new NotificationConfig()));
        assertThrows(IllegalArgumentException.class, () -> new NotificationService(List.of(), new NotificationConfig()));
    }

    @Test
    void shouldSendUsingConfiguredChannels() {
        NotificationService service = new NotificationService(
                List.of(new EmailNotifier(), new SlackNotifier(), new SmsNotifier()),
                new NotificationConfig()
        );

        File contract = new File("contract.pdf", 100L, "clientName=Acme signed=true", FileType.CONTRACT);

        service.send(contract, "processed");

        String logs = output.toString();
        assertTrue(logs.contains("[EMAIL]"));
        assertTrue(logs.contains("[SLACK]"));
        assertFalse(logs.contains("[SMS]"));
    }

    @Test
    void shouldThrowWhenNotifierIsMissing() {
        NotificationService service = new NotificationService(
                List.of(new EmailNotifier()),
                new NotificationConfig()
        );
        File contract = new File("contract.pdf", 100L, "clientName=Acme signed=true", FileType.CONTRACT);

        assertThrows(IllegalStateException.class, () -> service.send(contract, "processed"));
    }

    @Test
    void shouldIgnoreEmptyChannelList() {
        NotificationService service = new NotificationService(
                List.of(new EmailNotifier()),
                new NotificationConfig()
        );
        File file = new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE);

        service.notify(file, "processed", List.of());

        assertEquals("", output.toString());
    }
}
