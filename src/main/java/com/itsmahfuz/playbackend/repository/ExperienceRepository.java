package com.itsmahfuz.playbackend.repository;

import com.itsmahfuz.playbackend.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

}
