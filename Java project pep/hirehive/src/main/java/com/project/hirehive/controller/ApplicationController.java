package com.project.hirehive.controller;

import com.project.hirehive.model.Application;
import com.project.hirehive.model.Job;
import com.project.hirehive.model.User;
import com.project.hirehive.repository.ApplicationRepository;
import com.project.hirehive.service.JobService;
import com.project.hirehive.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping("/apply")
    public String openApplyForm(Model model, Principal principal) {

    System.out.println("apply opened");

    // Load all jobs
    model.addAttribute("jobs", jobService.getAllJobs());

    // Load user only if logged in
    User user = null;
    if (principal != null) {
        user = userService.findByEmail(principal.getName());
    }
    model.addAttribute("user", user);

    return "apply-form";
}

    @PostMapping("/submit-application")
    public String submitApplication(@RequestParam("jobId") Long jobId,
                                    @RequestParam("resume") MultipartFile resume,
                                    @RequestParam("applicantName") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("coverLetter") String coverLetter) {

        try {
            String uploadDir = "uploads/";
            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, resume.getBytes());

            Job job = jobService.getJobById(jobId);

            Application app = new Application();
            app.setApplicantName(name);
            app.setResumeLink(path.toString());
            app.setJob(job);

            applicationRepository.save(app);

            return "redirect:/jobs?applied=true";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/jobs?error=true";
        }
    }
}