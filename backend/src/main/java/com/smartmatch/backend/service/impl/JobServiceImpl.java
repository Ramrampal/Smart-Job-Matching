package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.repository.JobRepository;
import com.smartmatch.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public Job updateJob(Long id, Job job) {

        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));

        existingJob.setTitle(job.getTitle());
        existingJob.setCompany(job.getCompany());
        existingJob.setLocation(job.getLocation());
        existingJob.setSalary(job.getSalary());
        existingJob.setDescription(job.getDescription());
        existingJob.setJobType(job.getJobType());

        existingJob.setExperience(job.getExperience());
        existingJob.setSkills(job.getSkills());
        existingJob.setStatus(job.getStatus());
        existingJob.setPostedBy(job.getPostedBy());
        existingJob.setPostedDate(job.getPostedDate());

        return jobRepository.save(existingJob);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> searchByTitle(String keyword) {
        return jobRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public List<Job> searchByCompany(String keyword) {
        return jobRepository.findByCompanyContainingIgnoreCase(keyword);
    }

    @Override
    public List<Job> searchByLocation(String keyword) {
        return jobRepository.findByLocationContainingIgnoreCase(keyword);
    }
}