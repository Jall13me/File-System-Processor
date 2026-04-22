package com.example.filesystemprocessor.infrastructure.web;

import java.util.List;

public record FolderUploadRequest(
        String name,
        List<FileUploadRequest> files,
        List<FolderUploadRequest> subFolders
) {
}
