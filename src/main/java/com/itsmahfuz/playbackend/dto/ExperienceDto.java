package com.itsmahfuz.playbackend.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ExperienceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userEmail;

    private String userName;

    private String userThought;

    private long expVersion = 1;

    private String currentExp;

    private String expToken;

    private boolean isExpTokenUsed = false;

    private LocalDateTime expValidity;

    @PrePersist
    public void prePersist() {
        expToken = UUID.randomUUID().toString();
        if (expValidity == null) {
            expValidity = LocalDateTime.now();
        }
    }

}
