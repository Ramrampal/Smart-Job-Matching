package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.Job;
import java.util.List;
import java.util.Optional;

public interface JobService {

    List<Job> getAllJobs();

    Optional<Job> getJobById(Long id);

    Job saveJob(Job job);

    Job updateJob(Long id, Job job);

    void deleteJob(Long id);
}