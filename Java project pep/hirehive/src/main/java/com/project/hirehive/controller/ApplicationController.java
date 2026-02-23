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

    // -------------------- APPLY FORM --------------------
    @GetMapping("/apply/{jobId}")
    public String openApplyForm(@PathVariable Long jobId, Model model, Principal principal) {

        Job job = jobService.getJobById(jobId);

        if (job == null) {
            return "redirect:/jobs?notFound=true";
        }

        model.addAttribute("job", job);

        if (principal != null) {
            model.addAttribute("user", userService.findByEmail(principal.getName()));
        }

        return "apply-form";
    }

    // -------------------- SUBMIT APPLICATION --------------------
    @PostMapping("/submit-application")
    public String submitApplication(
            @RequestParam("jobId") Long jobId,
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("applicantName") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("coverLetter") String coverLetter
    ) {
        try {
            // Upload Folder
            String uploadDir = "uploads/";
            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();

            // File name
            String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, resume.getBytes());

            // Get job
            Job job = jobService.getJobById(jobId);

            // Save Application
            Application app = new Application();
            app.setApplicantName(name);
            app.setEmail(email);
            app.setPhone(phone);
            app.setCoverLetter(coverLetter);
            app.setResumeLink(filePath.toString());
            app.setJob(job);

            applicationRepository.save(app);

            return "redirect:/jobs?success=true";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/jobs?error=true";
        }
    }

    // -------------------- VIEW APPLICATIONS (NEWLY ADDED) --------------------
    @GetMapping("/applications/{jobId}")
    public String viewApplications(@PathVariable Long jobId, Model model) {

        Job job = jobService.getJobById(jobId);

        model.addAttribute("job", job);
        model.addAttribute("applications", applicationRepository.findByJobId(jobId));

        return "applications-list";  // MUST MATCH templates/applications-list.html
    }
}