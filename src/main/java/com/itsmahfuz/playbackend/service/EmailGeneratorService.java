package com.itsmahfuz.playbackend.service;

import com.itsmahfuz.playbackend.dto.CreateEmailRequest;
import com.itsmahfuz.playbackend.dto.ExperienceDto;
import com.itsmahfuz.playbackend.dto.GenerateEmailResponse;
import com.itsmahfuz.playbackend.entities.Experience;
import com.itsmahfuz.playbackend.repository.ExperienceRepository;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class EmailGeneratorService {

    //    private final JavaMailSender mailSender;
    private final ExperienceRepository experienceRepository;

    public EmailGeneratorService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public GenerateEmailResponse generateEmail(CreateEmailRequest request) throws Exception {
        try {
            Resend resend = new Resend("re_YAXBA2GW_8xUw6cTPYP8dNcc9u2JKYUgh");

            CreateEmailOptions sendEmailRequest = CreateEmailOptions.builder()
                    .from("Experience2 <no-reply@send.xn--u8j2c021jhz8agu9a.com>")
                    .to(request.getToEmail())
                    .subject("Your Next Experience Awaits")
                    .html(
                            "<div style='font-family: Arial, sans-serif; line-height:1.6; color:#333;'>" +
                                    "<p>Something opened in you.<br/>" +
                                    "Something softened.</p>" +

                                    "<p>And now, the next step is waiting.</p>" +

                                    "<p><strong>Yes, send me the link to Experience Two:</strong><br/>" +
                                    "<a href='{{EX2_LINK}}' " +
                                    "style='color:#2563eb; text-decoration:none; font-weight:bold;'>Open Experience Two</a>" +
                                    "</p>" +

                                    "<p style='font-size: 12px; color:#666;'>The link expires in 7 days, so please open it soon.</p>" +
                                    "</div>"
                    )
                    .build();

            CreateEmailResponse data = resend.emails().send(sendEmailRequest);
            GenerateEmailResponse response = new GenerateEmailResponse();
            response.setId(data.getId());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error generating email");
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
