package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.JobApplication;

import java.util.List;
import java.util.Optional;

public interface JobApplicationService {

    JobApplication apply(JobApplication jobApplication);

    List<JobApplication> getAllApplications();

    Optional<JobApplication> getApplicationById(Long id);

    JobApplication updateApplication(Long id, JobApplication jobApplication);

    void deleteApplication(Long id);
}