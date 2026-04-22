package com.example.filesystemprocessor.infrastructure.web;

import com.example.filesystemprocessor.domain.model.FileType;

public record FileUploadRequest(
        String name,
        long size,
        String content,
        FileType fileType
) {
}
