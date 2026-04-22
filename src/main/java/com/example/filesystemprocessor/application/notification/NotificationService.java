package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.File;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final Map<NotifierType, Notifier> notifiersByType;
    private final NotificationConfig config;

    public NotificationService(List<Notifier> notifiers, NotificationConfig config) {
        if (notifiers == null || notifiers.isEmpty())
            throw new IllegalArgumentException("La lista no puede estar vacia o ser nula");

        this.config = config;
        this.notifiersByType = notifiers.stream()
                .collect(Collectors.toMap(Notifier::getType, Function.identity()));
    }

    public void send(File file, String message) {
        List<NotifierType> channels = config.getChannelsFor(file.getFileType());
        notify(file, message, channels);
    }

    public void notify(File file, String message, List<NotifierType> notifierTypes) {
        if (file == null) throw new IllegalArgumentException("El archivo no puede ser nulo ");
        if (notifierTypes == null || notifierTypes.isEmpty()) return;

        for (NotifierType type : notifierTypes) {
            Notifier notifier = notifiersByType.get(type);
            if (notifier == null)
                throw new IllegalStateException("El archivo debe ser registrado como: " + type);
            notifier.notify(file, message);
        }
    }
}