package com.itsmahfuz.playbackend.service;

import com.itsmahfuz.playbackend.dto.ExperienceDto;
import com.itsmahfuz.playbackend.entities.Experience;
import com.itsmahfuz.playbackend.repository.ExperienceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailGeneratorService {

    private final JavaMailSender mailSender;
    private final ExperienceRepository experienceRepository;

    public EmailGeneratorService(JavaMailSender mailSender, ExperienceRepository experienceRepository) {
        this.mailSender = mailSender;
        this.experienceRepository = experienceRepository;
    }

    public String generateEmail() {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("pqenirob@gmail.com");
            message.setTo("mahfuz00741@gmail.com");
            message.setSubject("Test Mail");
            message.setText("Hi, this is test mail");

            mailSender.send(message);
            return "Success";
        } catch (Exception e) {
            System.err.println("Email generation failed");
            throw new RuntimeException("Failed to email generation", e);

        }
    }

    public ExperienceDto createUser(ExperienceDto request) {
        try {
            Experience experience = new Experience();
            BeanUtils.copyProperties(request, experience);

            Experience savedExperience = experienceRepository.save(experience);

            ExperienceDto response = new ExperienceDto();
            BeanUtils.copyProperties(savedExperience, response);
            return response;

        } catch (Exception e) {
            System.err.println("User creation failed: " + e.getMessage());
            throw new RuntimeException("Failed to create user experience", e);
        }
    }

}
