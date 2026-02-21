package com.project.hirehive.controller.api;

import com.project.hirehive.model.Job;
import com.project.hirehive.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobRestController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> postJob(@RequestBody Job job) {
        jobService.saveJob(job);
        return ResponseEntity.ok(job);
    }
}

