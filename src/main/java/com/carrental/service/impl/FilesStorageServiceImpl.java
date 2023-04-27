package com.carrental.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import com.carrental.service.IFileStorageService;
import com.carrental.utils.FileExtensionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements IFileStorageService {
    private String UPLOAD_DIR = "src/main/resources/uploads";
    private Path rootPath = Paths.get(UPLOAD_DIR);

    private File rootFile;

    @Override
    public void init() {
        try {
            this.rootFile = new File(UPLOAD_DIR);
            System.out.println(this.rootFile.getAbsolutePath());
            if (!this.rootFile.exists())
                Files.createDirectory(rootPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload folder !");
        }
    }

    @Override
    public String save(MultipartFile file, String extend) {
        try {
            Path truthPath = this.rootPath;
            if (extend != null) {
                File newDir = new File(this.rootFile, extend);
                if (!newDir.exists()){
                    Path extendPath = Paths.get(extend);
                    truthPath = this.rootPath.resolve(extendPath);
                    Files.createDirectory(truthPath);
                }
            }
            String fileExtension = FileExtensionUtils.getExtensionByStringHandling(file.getOriginalFilename()).get();
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExtension;
            Files.copy(file.getInputStream(), truthPath.resolve(fileName));
            System.out.println(truthPath);
            System.out.println(this.rootPath);
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save file : " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = rootPath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Failed to load file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootPath.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootPath, 1).filter(path -> !path.equals(this.rootPath)).map(this.rootPath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public String getFilename(MultipartFile file) {
        String originFilename = file.getOriginalFilename();
        String fileExtension = FileExtensionUtils.getExtensionByStringHandling(originFilename).get();
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        String result = uuidAsString + "." + fileExtension;
        return result;
    }
}