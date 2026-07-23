package com.smartmatch.backend.controller;

import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;

    // Apply for a Job
    @PostMapping
    public ResponseEntity<JobApplication> apply(@RequestBody JobApplication application) {
        return ResponseEntity.ok(service.apply(application));
    }

    // Get All Applications (Admin)
    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    // Get Logged-in User Applications
    @GetMapping("/my")
    public ResponseEntity<List<JobApplication>> getMyApplications() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return ResponseEntity.ok(service.getMyApplications(email));
    }

    // Get Application By ID
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplication(@PathVariable Long id) {

        JobApplication application = service.getApplicationById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return ResponseEntity.ok(application);
    }

    // Update Application
    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application) {

        return ResponseEntity.ok(service.updateApplication(id, application));
    }

    // Delete Application
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {

        service.deleteApplication(id);

        return ResponseEntity.ok("Application Deleted Successfully");
    }
}