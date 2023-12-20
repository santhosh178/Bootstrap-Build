package com.example.firstproject.service;

import com.example.firstproject.dto.MarksRequest;
import com.example.firstproject.entity.Marks;
import com.example.firstproject.entity.Student;
import com.example.firstproject.entity.Subject;
import com.example.firstproject.exception.BadRequestException;
import com.example.firstproject.repository.MarksRepository;
import com.example.firstproject.repository.StudentRepository;
import com.example.firstproject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Marks addMarks(MarksRequest marksRequest) {
        Student student = studentRepository.findById(marksRequest.getStudent_id()).orElseThrow(()->new BadRequestException("Student not found ."));
        Subject subject = subjectRepository.findById(marksRequest.getSubject_id()).orElseThrow(()->new BadRequestException("subject not found"));

        Marks mark = new Marks();
        mark.setMarks(marksRequest.getMarks());
        mark.setStudent(student);
        mark.setSubject(subject);

        return marksRepository.save(mark);
    }

    public List<Marks> getMarks(long studentId) {
        return marksRepository.findByStudentId(studentId);
    }

    public void deleteStudent(long marksId) {
        Marks marks = marksRepository.findById(marksId).orElseThrow(() -> new BadRequestException("Marks id not exist with id: " + marksId));
        marksRepository.deleteById(marks.getId());
    }

    public Marks updateStudent(long id, MarksRequest marksRequest) {
        Marks existingMarks = marksRepository.findById(id).orElseThrow(() -> new BadRequestException("Marks id not exist with id: " + id));

        Student student = studentRepository.findById(marksRequest.getStudent_id()).orElseThrow(()->new BadRequestException("Student not found ."));
        Subject subject = subjectRepository.findById(marksRequest.getSubject_id()).orElseThrow(()->new BadRequestException("subject not found"));

        existingMarks.setMarks(marksRequest.getMarks());
        existingMarks.setStudent(student);
        existingMarks.setSubject(subject);

        return marksRepository.save(existingMarks);
    }

    public List<Marks> findAllMarks() {
        return marksRepository.findAll();
    }

    public int getStudentTotalMarks(Long studentId) {
        List<Marks> marksList = marksRepository.findByStudentId(studentId);
        return marksList.stream().mapToInt(Marks::getMarks).sum();
    }

    public Student getStudentDetails(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }


}
