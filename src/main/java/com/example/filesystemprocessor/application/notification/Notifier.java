package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;

public interface Notifier {
    void notify(File file, String message);
    NotifierType getType();
}
