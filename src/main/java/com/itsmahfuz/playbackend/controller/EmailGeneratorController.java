package com.itsmahfuz.playbackend.controller;

import com.itsmahfuz.playbackend.dto.ExperienceDto;
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
    public ResponseEntity<?> generateEmail(){
        String result = emailGeneratorService.generateEmail();
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "create-user")
    public ResponseEntity<?> createUser(@RequestBody ExperienceDto request){
        ExperienceDto result = emailGeneratorService.createUser(request);
        return ResponseEntity.ok(result);
    }

}
