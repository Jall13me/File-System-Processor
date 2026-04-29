package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.FileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractNotifierTest {
    @Test
    void FormattedMessageAndDelegateNotification(){
        File file = new File("invoice.xml", 100L, "customerId=1 amount=100", FileType.INVOICE);
        TestNotifier notifier = new TestNotifier();


        notifier.notify(file, "processed");

        assertTrue(notifier.lastMessage.contains("invoice.xml"));
        assertTrue(notifier.lastMessage.contains("INVOICE"));
        assertTrue(notifier.lastMessage.contains("processed"));
    }

    @Test
    void shouldRejectNullFile() {
        TestNotifier notifier = new TestNotifier();

        assertThrows(IllegalArgumentException.class, () -> notifier.notify(null, "processed"));
    }

    private static class TestNotifier extends AbstractNotifier {
        private String lastMessage;

        @Override
        public NotifierType getType() {
            return NotifierType.EMAIL;
        }

        @Override
        protected void doNotify(String formattedMessage) {
            this.lastMessage = formattedMessage;
        }
    }
}
