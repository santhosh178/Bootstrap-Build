package com.example.firstproject.repository;

import com.example.firstproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    boolean existsBySubjectName(String subjectName);
}
