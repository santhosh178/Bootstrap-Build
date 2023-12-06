package com.example.firstproject.repository;

import com.example.firstproject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ImageRepository extends JpaRepository<Image, Long> {
}
