package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationConfigTest {

    private final NotificationConfig config = new NotificationConfig();

    @Test
    void shouldReturnInvoiceChannels() {
        assertEquals(List.of(NotifierType.EMAIL), config.getChannelsFor(FileType.INVOICE));
    }

    @Test
    void shouldReturnContractChannels() {
        assertEquals(List.of(NotifierType.EMAIL, NotifierType.SLACK), config.getChannelsFor(FileType.CONTRACT));
    }

    @Test
    void shouldReturnReportChannels() {
        assertEquals(List.of(NotifierType.SLACK), config.getChannelsFor(FileType.REPORT));
    }

    @Test
    void shouldReturnEmptyListForNullType() {
        assertTrue(config.getChannelsFor(null).isEmpty());
    }
}
