package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.entity.User;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<User> getAllUsers();

    List<Job> getAllJobs();

    List<JobApplication> getAllApplications();

    void deleteUser(Long userId);

    void deleteJob(Long jobId);

    Map<String, Long> getDashboardStats();
}