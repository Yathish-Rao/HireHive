package com.project.hirehive.service;

import com.project.hirehive.model.Application;
import com.project.hirehive.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }
}

