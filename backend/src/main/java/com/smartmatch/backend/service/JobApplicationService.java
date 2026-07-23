package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.JobApplication;

import java.util.List;
import java.util.Optional;

public interface JobApplicationService {

    // Apply for a Job
    JobApplication apply(JobApplication jobApplication);

    // Get All Applications (Admin)
    List<JobApplication> getAllApplications();

    // Get Logged-in User Applications
    List<JobApplication> getMyApplications(String email);

    // Get Application By ID
    Optional<JobApplication> getApplicationById(Long id);

    // Update Application
    JobApplication updateApplication(Long id, JobApplication jobApplication);

    // Delete Application
    void deleteApplication(Long id);
}