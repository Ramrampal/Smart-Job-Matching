package com.smartmatch.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    String uploadResume(MultipartFile file);

}