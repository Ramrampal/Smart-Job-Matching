package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;

import java.util.List;

public interface EmployerService {

    List<Job> getMyJobs(String employerEmail);

    List<JobApplication> getApplicantsByJob(Long jobId);

    JobApplication updateApplicationStatus(Long applicationId, String status);
}