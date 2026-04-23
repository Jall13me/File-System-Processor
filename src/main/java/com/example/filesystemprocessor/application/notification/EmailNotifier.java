package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifier implements Notifier {

    @Override
    public void notify(File file, String message) {
        System.out.printf("[EMAIL] → '%s' | %s%n", file.getName(), message);
    }

    @Override
    public NotifierType getType() { return NotifierType.EMAIL; }
}
