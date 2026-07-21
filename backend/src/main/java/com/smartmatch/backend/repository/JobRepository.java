package com.smartmatch.backend.repository;

import com.smartmatch.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByTitleContainingIgnoreCase(String keyword);

    List<Job> findByCompanyContainingIgnoreCase(String keyword);

    List<Job> findByLocationContainingIgnoreCase(String keyword);

}