package com.smartmatch.backend.controller;

import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;

    @PostMapping
    public ResponseEntity<JobApplication> apply(@RequestBody JobApplication application) {
        return ResponseEntity.ok(service.apply(application));
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplication(@PathVariable Long id) {

        JobApplication application = service.getApplicationById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return ResponseEntity.ok(application);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application) {

        return ResponseEntity.ok(service.updateApplication(id, application));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {

        service.deleteApplication(id);

        return ResponseEntity.ok("Application Deleted Successfully");
    }
}