package com.smartmatch.backend.repository;

import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.JobApplication;
import com.smartmatch.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // All applications of a user
    List<JobApplication> findByUser(User user);

    // All applications for a job
    List<JobApplication> findByJob(Job job);

    // Applications by status
    List<JobApplication> findByStatus(String status);

    // Check if a user has already applied for a job
    boolean existsByUserAndJob(User user, Job job);

    // Applications by user email
    List<JobApplication> findByUserEmail(String email);
}