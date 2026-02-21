package com.project.hirehive.repository;

import com.project.hirehive.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}

