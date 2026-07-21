package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.service.ResumeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {

    private static final String UPLOAD_DIR =
            "D:\\Smart-Job-Matching\\backend\\uploads\\resumes";

    @Override
    public String uploadResume(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Please select a resume file.");
        }

        try {

            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path destination = uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
}