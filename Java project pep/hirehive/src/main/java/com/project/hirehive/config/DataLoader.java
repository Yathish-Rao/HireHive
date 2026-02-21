package com.project.hirehive.config;

import com.project.hirehive.model.Job;
import com.project.hirehive.repository.JobRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private JobRepository jobRepository;

    @PostConstruct
    public void loadJobs() {

        if(jobRepository.count() == 0) {

            jobRepository.save(new Job(null,"Java Developer","Spring Boot Developer","TCS","Bangalore"));
            jobRepository.save(new Job(null,"Frontend Developer","React Developer","Infosys","Pune"));
            jobRepository.save(new Job(null,"Backend Developer","Microservices","Wipro","Bangalore"));
            jobRepository.save(new Job(null,"Full Stack Developer","Java + React","Capgemini","Hyderabad"));
            jobRepository.save(new Job(null,"DevOps Engineer","CI/CD","Accenture","Bangalore"));
            jobRepository.save(new Job(null,"Cloud Engineer","AWS Specialist","IBM","Delhi"));
            jobRepository.save(new Job(null,"Data Analyst","SQL + PowerBI","HCL","Noida"));
            jobRepository.save(new Job(null,"QA Engineer","Automation Testing","Cognizant","Bangalore"));
            jobRepository.save(new Job(null,"Android Developer","Kotlin","Zoho","Chennai"));
            jobRepository.save(new Job(null,"Python Developer","Django","Tech Mahindra","Bangalore"));
        }
    }
}
