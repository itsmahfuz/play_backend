package com.itsmahfuz.playbackend.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "experience")
@Data
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String exp;

    private LocalDateTime expValidity;

    @PrePersist
    public void prePersist() {
        exp = UUID.randomUUID().toString();
        if (expValidity == null) {
            expValidity = LocalDateTime.now();
        }
    }

}
