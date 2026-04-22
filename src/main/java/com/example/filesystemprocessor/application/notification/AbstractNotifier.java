package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;

public abstract class AbstractNotifier implements Notifier {

    @Override
    public void notify(File file, String message) {
        validateFile(file);
        String formattedMessage = buildMessage(file, message);
        doNotify(formattedMessage);
    }

    protected void validateFile(File file) {
        if (file == null) throw new IllegalArgumentException("El archivo no puede ser nulo");
    }

    protected String buildMessage(File file, String message) {
        String safeMessage = message == null ? "" : message;
        return String.format("fileName=%s | fileType=%s | message=%s",
                file.getName(), file.getFileType(), safeMessage);
    }

    protected abstract void doNotify(String formattedMessage);
}
