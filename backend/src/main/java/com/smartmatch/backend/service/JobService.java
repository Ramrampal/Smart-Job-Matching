package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    Job createJob(Job job);

    List<Job> getAllJobs();

    Optional<Job> getJobById(Long id);

    Job updateJob(Long id, Job job);

    void deleteJob(Long id);

    // Search Methods
    List<Job> searchByTitle(String keyword);

    List<Job> searchByCompany(String keyword);

    List<Job> searchByLocation(String keyword);
}