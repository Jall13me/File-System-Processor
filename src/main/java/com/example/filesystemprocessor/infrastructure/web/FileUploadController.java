package com.example.filesystemprocessor.infrastructure.web;

import com.example.filesystemprocessor.application.service.FileProcessorEngine;
import com.example.filesystemprocessor.domain.model.File;
import com.example.filesystemprocessor.domain.model.Folder;
import com.example.filesystemprocessor.domain.repository.FileRepository;
import com.example.filesystemprocessor.domain.result.ProcessResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileUploadController {

    private final FileProcessorEngine processorEngine;
    private final FileRepository fileRepository;

    public FileUploadController(FileProcessorEngine processorEngine,
                                FileRepository fileRepository) {
        this.processorEngine = processorEngine;
        this.fileRepository  = fileRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<ProcessResultDto> uploadFile(@RequestBody FileUploadRequest request) {
        File file = toFile(request);
        ProcessResultDto result = processorEngine.process(file);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload/folder")
    public ResponseEntity<ProcessResultDto> uploadFolder(@RequestBody FolderUploadRequest request) {
        Folder folder = toFolder(request);
        ProcessResultDto result = processorEngine.process(folder);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<String>> listFiles() {
        List<String> names = fileRepository.findAll().stream()
                .map(File::getName)
                .toList();
        return ResponseEntity.ok(names);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearRepository() {
        fileRepository.clear();
        return ResponseEntity.noContent().build();
    }

    private File toFile(FileUploadRequest req) {
        return new File(req.name(), req.size(), req.content(), req.fileType());
    }

    private Folder toFolder(FolderUploadRequest req) {
        Folder folder = new Folder(req.name());

        if (req.files() != null) {
            req.files().forEach(f -> folder.add(toFile(f)));
        }

        if (req.subFolders() != null) {
            req.subFolders().forEach(sub -> folder.add(toFolder(sub)));
        }

        return folder;
    }
}
