package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;
import org.springframework.stereotype.Component;

@Component
public class SmsNotifier implements Notifier {

    @Override
    public void notify(File file, String message) {
        System.out.printf("[SMS]   → '%s' | %s%n", file.getName(), message);
    }

    @Override
    public NotifierType getType() { return NotifierType.SMS; }
}
