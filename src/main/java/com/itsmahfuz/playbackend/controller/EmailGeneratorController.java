package com.itsmahfuz.playbackend.controller;

import com.itsmahfuz.playbackend.dto.CreateEmailRequest;
import com.itsmahfuz.playbackend.dto.ExperienceDto;
import com.itsmahfuz.playbackend.dto.GenerateEmailResponse;
import com.itsmahfuz.playbackend.service.EmailGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "email/")
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    public EmailGeneratorController(EmailGeneratorService emailGeneratorService) {
        this.emailGeneratorService = emailGeneratorService;
    }

    @PostMapping(path = "generate")
    public ResponseEntity<?> generateEmail(@RequestBody CreateEmailRequest request) throws Exception {
        GenerateEmailResponse response = emailGeneratorService.generateEmail(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "create-user")
    public ResponseEntity<?> createUser(@RequestBody ExperienceDto request){
        ExperienceDto result = emailGeneratorService.createUser(request);
        return ResponseEntity.ok(result);
    }

}
