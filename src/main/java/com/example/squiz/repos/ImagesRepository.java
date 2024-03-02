package com.example.squiz.repos;

import com.example.squiz.entities.ImagesEB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<ImagesEB, Long> {
}
