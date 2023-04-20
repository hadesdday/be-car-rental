package com.carrental.controller;


import com.carrental.model.FileInfo;
import com.carrental.service.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
public class UploadFileController {
    @Autowired
    IFileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam(value = "files") MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        try {
            Arrays.stream(files).forEach(file -> {
                String fileName = storageService.save(file);
                fileNames.add(fileName);
            });
            return ResponseEntity.status(HttpStatus.OK).body(fileNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(fileNames);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UploadFileController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}