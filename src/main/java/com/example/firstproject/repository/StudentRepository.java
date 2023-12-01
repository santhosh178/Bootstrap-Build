package com.example.firstproject.repository;

import com.example.firstproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
