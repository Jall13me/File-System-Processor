package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NotifierImplementationsTest {

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
    void shouldNotifyByEmail() {
        EmailNotifier notifier = new EmailNotifier();
        File file = new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE);

        notifier.notify(file, "processed");

        assertEquals(NotifierType.EMAIL, notifier.getType());
        assertTrue(output.toString().contains("[EMAIL]"));
        assertTrue(output.toString().contains("invoice.xml"));
    }

    @Test
    void shouldNotifyBySlack() {
        SlackNotifier notifier = new SlackNotifier();
        File file = new File("contract.pdf", 100L, "clientName=Acme signed=true", FileType.CONTRACT);

        notifier.notify(file, "processed");

        assertEquals(NotifierType.SLACK, notifier.getType());
        assertTrue(output.toString().contains("[SLACK]"));
        assertTrue(output.toString().contains("contract.pdf"));
    }

    @Test
    void shouldNotifyBySms() {
        SmsNotifier notifier = new SmsNotifier();
        File file = new File("report.csv", 100L, "a,b\n1,2\n3,4", FileType.REPORT);

        notifier.notify(file, "processed");

        assertEquals(NotifierType.SMS, notifier.getType());
        assertTrue(output.toString().contains("[SMS]"));
        assertTrue(output.toString().contains("report.csv"));
    }
}
