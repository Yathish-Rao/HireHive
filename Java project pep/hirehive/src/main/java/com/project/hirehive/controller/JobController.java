package com.project.hirehive.controller;

import com.project.hirehive.model.Job;
import com.project.hirehive.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    // Show all jobs
    @GetMapping("/jobs")
    public String viewJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "job-list";
    }

    // Show post job page
    @GetMapping("/post-job")
    public String postJobPage() {
        return "post-job";
    }

    // Save job
    @PostMapping("/post-job")
    public String saveJob(Job job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }
}

