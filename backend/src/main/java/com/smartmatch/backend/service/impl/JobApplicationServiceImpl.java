package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.repository.JobApplicationRepository;
import com.smartmatch.backend.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository repository;

    @Override
    public JobApplication apply(JobApplication jobApplication) {
        return repository.save(jobApplication);
    }

    @Override
    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    @Override
    public List<JobApplication> getMyApplications(String email) {
        return repository.findByUserEmail(email);
    }

    @Override
    public List<JobApplication> getApplicationsByJob(Long jobId) {
        Job job = new Job();
        job.setId(jobId);
        return repository.findByJob(job);
    }

    @Override
    public List<JobApplication> getApplicationsByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public Optional<JobApplication> getApplicationById(Long id) {
        return repository.findById(id);
    }

    @Override
    public JobApplication updateApplication(Long id, JobApplication jobApplication) {

        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setUser(jobApplication.getUser());
        application.setJob(jobApplication.getJob());
        application.setResume(jobApplication.getResume());
        application.setCoverLetter(jobApplication.getCoverLetter());
        application.setAppliedDate(jobApplication.getAppliedDate());
        application.setStatus(jobApplication.getStatus());

        return repository.save(application);
    }

    @Override
    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }
}