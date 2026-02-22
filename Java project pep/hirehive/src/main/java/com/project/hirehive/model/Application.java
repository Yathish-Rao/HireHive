package com.project.hirehive.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private String email;
    private String phone;
    private String coverLetter;
    private String resumeLink;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}