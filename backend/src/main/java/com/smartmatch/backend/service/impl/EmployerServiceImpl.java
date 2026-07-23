package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.repository.JobApplicationRepository;
import com.smartmatch.backend.repository.JobRepository;
import com.smartmatch.backend.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public List<Job> getMyJobs(String employerEmail) {
        return jobRepository.findByPostedBy(employerEmail);
    }

    @Override
    public List<JobApplication> getApplicantsByJob(Long jobId) {

        Job job = new Job();
        job.setId(jobId);

        return jobApplicationRepository.findByJob(job);
    }

    @Override
    public JobApplication updateApplicationStatus(Long applicationId, String status) {

        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);

        return jobApplicationRepository.save(application);
    }
}