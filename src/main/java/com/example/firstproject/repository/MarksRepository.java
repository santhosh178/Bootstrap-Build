package com.example.firstproject.repository;

import com.example.firstproject.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Long> {
    List<Marks> findByStudentId(long studentId);

}
