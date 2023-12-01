package com.example.firstproject.service;

import com.example.firstproject.dto.StudentRequest;
import com.example.firstproject.entity.Student;
import com.example.firstproject.exception.BadRequestException;
import com.example.firstproject.repository.MarksRepository;
import com.example.firstproject.repository.StudentRepository;
import com.example.firstproject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    public Student addStudent(StudentRequest studentRequest) {
        if (studentRepository.existsByPhoneNumber(studentRequest.getPhoneNumber())) {
            throw new BadRequestException("Email address already in use.");
        }

        Student student  = new Student();
        student.setName(studentRequest.getName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setGender(studentRequest.getGender());

        return studentRepository.save(student);
    }

    public Optional<Student> getStudentDetails(long studentId) {
        return studentRepository.findById(studentId);
    }

    public void deleteStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student id not exist with id: " + studentId));
        studentRepository.deleteById(student.getId());
    }

    public Student updateStudent(long studentId, StudentRequest studentRequest) {
        Student existingstudent = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student id not exist with id: " + studentId));

        existingstudent.setName(studentRequest.getName());
        existingstudent.setPhoneNumber(studentRequest.getPhoneNumber());
        existingstudent.setGender(studentRequest.getGender());

        return studentRepository.save(existingstudent);
    }

    public List<Student> findAllUsers() {
        return studentRepository.findAll();
    }
//
//    public int getStudentTotalMarks(Long studentId) {
//        List<Marks> marksList = marksRepository.findByStudentId(studentId);
//        return marksList.stream().mapToInt(Marks::getMarks).sum();
//    }
//
//    public List<Subject> getAllSubjects() {
//        return subjectRepository.findAll();
//    }

}
