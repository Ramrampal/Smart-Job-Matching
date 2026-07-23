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

    // Get Applications By Job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(service.getApplicationsByJob(jobId));
    }

    // Get Applications By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplication>> getApplicationsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getApplicationsByStatus(status));
    }

    // Get Application By ID
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplication(@PathVariable Long id) {

        return service.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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