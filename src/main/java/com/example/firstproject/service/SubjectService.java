package com.example.firstproject.service;

import com.example.firstproject.dto.SubjectRequest;
import com.example.firstproject.entity.Subject;
import com.example.firstproject.exception.BadRequestException;
import com.example.firstproject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(SubjectRequest subjectRequest) {
        if (subjectRepository.existsBySubjectName(subjectRequest.getSubjectName())) {
            throw new BadRequestException("subject already register.");
        }

        Subject subject = new Subject();
        subject.setSubjectName(subjectRequest.getSubjectName());
        subject.setTeacherName(subjectRequest.getTeacherName());

        return subjectRepository.save(subject);
    }

    public Optional<Subject> getStudentDetails(long subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public void deleteStudent(long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new BadRequestException("Subject id not exist with id: " + subjectId));
        subjectRepository.deleteById(subject.getId());
    }

    public Subject updateSubject(long subjectId, SubjectRequest subjectRequest) {
        Subject existingsubject = subjectRepository.findById(subjectId).orElseThrow(() -> new BadRequestException("Subject id not exist with id: " + subjectId));

        existingsubject.setSubjectName(subjectRequest.getSubjectName());
        existingsubject.setTeacherName(subjectRequest.getTeacherName());

        return subjectRepository.save(existingsubject);
    }

    public List<Subject> findAllSubject() {
        return subjectRepository.findAll();
    }

}
