package com.itsmahfuz.playbackend.service;

import com.itsmahfuz.playbackend.dto.ExperienceDto;
import com.itsmahfuz.playbackend.entities.Experience;
import com.itsmahfuz.playbackend.repository.ExperienceRepository;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailGeneratorService {

//    private final JavaMailSender mailSender;
    private final ExperienceRepository experienceRepository;
    private final Resend resend;
    private final String from;

    public EmailGeneratorService(ExperienceRepository experienceRepository, @Value("${resend.api-key}") String apiKey,
                                 @Value("${email.from}") String from) {
        this.experienceRepository = experienceRepository;
        this.resend = new Resend(apiKey);
        this.from = from;
    }

//    public EmailGeneratorService(JavaMailSender mailSender, ExperienceRepository experienceRepository) {
//        this.mailSender = mailSender;
//        this.experienceRepository = experienceRepository;
//    }

    public String generateEmail() {

        CreateEmailOptions options = CreateEmailOptions.builder()
                .from(from)
                .to("mahfuzur.rahman.swe@gmail.com")
                .subject("no-reply")
                .html("Hello, this is test mail..!")
                .build();
        try {
            CreateEmailResponse response = resend.emails().send(options);
            return response.getId();
        } catch (ResendException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String generateEmail() {
//
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("pqenirob@gmail.com");
//            message.setTo("mahfuz00741@gmail.com");
//            message.setSubject("Test Mail");
//            message.setText("Hi, this is test mail");
//
//            mailSender.send(message);
//            return "Success";
//        } catch (Exception e) {
//            System.err.println("Email generation failed");
//            throw new RuntimeException("Failed to email generation", e);
//
//        }
//    }

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
