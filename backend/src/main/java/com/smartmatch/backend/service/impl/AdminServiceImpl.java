package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.entity.User;
import com.smartmatch.backend.repository.JobApplicationRepository;
import com.smartmatch.backend.repository.JobRepository;
import com.smartmatch.backend.repository.UserRepository;
import com.smartmatch.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }

    @Override
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalUsers", userRepository.count());
        stats.put("totalJobs", jobRepository.count());
        stats.put("totalApplications", jobApplicationRepository.count());

        long totalEmployers = userRepository.findAll().stream()
                .filter(user -> "EMPLOYER".equalsIgnoreCase(user.getRole()))
                .count();

        stats.put("totalEmployers", totalEmployers);

        return stats;
    }
}