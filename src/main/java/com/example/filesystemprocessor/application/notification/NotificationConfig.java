package com.example.filesystemprocessor.application.notification;

import com.example.filesystemprocessor.domain.model.FileType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationConfig {

    private final Map<FileType, List<NotifierType>> configMap;

    public NotificationConfig() {
        configMap = new EnumMap<>(FileType.class);
        configMap.put(FileType.INVOICE,  List.of(NotifierType.EMAIL));
        configMap.put(FileType.CONTRACT, List.of(NotifierType.EMAIL, NotifierType.SLACK));
        configMap.put(FileType.REPORT,   List.of(NotifierType.SLACK));
    }

    public List<NotifierType> getChannelsFor(FileType type) {
        return configMap.getOrDefault(type, List.of());
    }
}
