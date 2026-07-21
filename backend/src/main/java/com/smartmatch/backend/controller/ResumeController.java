package com.smartmatch.backend.controller;

import com.smartmatch.backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    private static final String UPLOAD_DIR =
            "D:\\Smart-Job-Matching\\backend\\uploads\\resumes";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file) {

        String fileName = resumeService.uploadResume(file);

        return ResponseEntity.ok("Resume uploaded successfully: " + fileName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable String fileName) throws MalformedURLException {

        Path path = Paths.get(UPLOAD_DIR).resolve(fileName);

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}