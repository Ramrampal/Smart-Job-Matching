package com.smartmatch.backend.controller;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
@CrossOrigin(origins = "*")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    // Get jobs posted by logged-in employer
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getMyJobs() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return ResponseEntity.ok(employerService.getMyJobs(email));
    }

    // Get applicants for a job
    @GetMapping("/jobs/{jobId}/applications")
    public ResponseEntity<List<JobApplication>> getApplicants(@PathVariable Long jobId) {

        return ResponseEntity.ok(employerService.getApplicantsByJob(jobId));
    }

    // Update application status
    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<JobApplication> updateStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                employerService.updateApplicationStatus(applicationId, status)
        );
    }
}