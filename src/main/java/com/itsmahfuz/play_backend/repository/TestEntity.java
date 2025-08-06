package com.itsmahfuz.play_backend.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Test_table")
@Data
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String exp2;

    private LocalDateTime exp1Validity;

    @PrePersist
    public void prePersist() {
        exp2 = UUID.randomUUID().toString();
        if (exp1Validity == null) {
            exp1Validity = LocalDateTime.now();
        }
    }

}
