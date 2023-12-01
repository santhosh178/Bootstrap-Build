package com.example.firstproject.controller;

import com.example.firstproject.dto.MarksRequest;
import com.example.firstproject.entity.Marks;
import com.example.firstproject.entity.Student;
import com.example.firstproject.entity.Subject;
import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.dto.StudentRequest;
import com.example.firstproject.dto.SubjectRequest;
import com.example.firstproject.service.MarksService;
import com.example.firstproject.service.StudentService;
import com.example.firstproject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MarksService marksService;


    //    -------------student--------------      //

    @PostMapping("/add_student")
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
        Student createdStudent = studentService.addStudent(studentRequest);
        return ResponseEntity.ok(new ApiResponse(true,"add student success"));
    }

    @GetMapping("/get_student_details")
    public Optional<Student> getStudentDetails(@RequestParam long studentId) {
        return studentService.getStudentDetails(studentId);
    }

    @GetMapping("/delete_student")
    public ResponseEntity<?> deleteStudent(@RequestParam long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(new ApiResponse(true,"delete student details success"));
    }

    @PostMapping("/update_student")
    public ResponseEntity<?> updateStudent(@RequestParam long studentId, @RequestBody StudentRequest studentRequest) {
        Student updated = studentService.updateStudent(studentId, studentRequest);
        return ResponseEntity.ok(new ApiResponse(true,"student details updated success"));
    }

    @GetMapping("/get_all_student_details")
    public List<Student> getAllUsers() {
        return studentService.findAllUsers();
    }

    //     ------------subject-----------     //

    @PostMapping("/add_subject")
    public ResponseEntity<?> addSubject(@RequestBody SubjectRequest subjectRequest) {
        Subject addSubject = subjectService.addSubject(subjectRequest);
        return ResponseEntity.ok(new ApiResponse(true,"add subject success"));
    }

    @GetMapping("/get_subject_details")
    public Optional<Subject> getSubjectDetails(@RequestParam long subject_id) {
        return subjectService.getStudentDetails(subject_id);
    }

    @GetMapping("/delete_subject")
    public ResponseEntity<?> deleteSubject(@RequestParam long subjectId) {
        subjectService.deleteStudent(subjectId);
        return ResponseEntity.ok(new ApiResponse(true,"subject deleted success"));
    }

    @PostMapping("/update_subject")
    public ResponseEntity<?> updateSubject(@RequestParam long subjectId, @RequestBody SubjectRequest subjectRequest) {
        Subject updated = subjectService.updateSubject(subjectId, subjectRequest);
        return ResponseEntity.ok(new ApiResponse(true,"subject details updated success"));
    }

    @GetMapping("/get_all_subject_details")
    public List<Subject> getAllSubject() {
        return subjectService.findAllSubject();
    }

    //        -------------marks-----------     //

    @PostMapping("/add_marks")
    public ResponseEntity<?> addMarks(@RequestBody MarksRequest marksRequest) {
        Marks addmarks = marksService.addMarks(marksRequest);
        return ResponseEntity.ok(new ApiResponse(true,"marks added success"));
    }

    @GetMapping("/get_student_marks")
    public List<Marks> getStudentMarks(@RequestParam long studentId) {
        return marksService.getMarks(studentId);
    }

    @GetMapping("/delete_marks")
    public ResponseEntity<?> deleteStudentMarks(@RequestParam long marksId) {
        marksService.deleteStudent(marksId);
        return ResponseEntity.ok(new ApiResponse(true,"delete student marks details"));
    }

    @PostMapping("/update_marks")
    public ResponseEntity<?> updateStudentMarks(@RequestParam long marksId, @RequestBody MarksRequest marksRequest) {
        Marks updated = marksService.updateStudent(marksId, marksRequest);
        return ResponseEntity.ok(new ApiResponse(true,"marks details updated success"));
    }

    @GetMapping("/get_all_marks_details")
    public List<Marks> getAllMarks() {
        return marksService.findAllMarks();
    }

    @GetMapping("/total_marks_one_student")
    public ResponseEntity<Map<String, Object>> getStudentDetailsAndTotalMarks(@RequestParam long studentId) {

        Map<String, Object> response = new HashMap<>();

        int totalMarks = marksService.getStudentTotalMarks(studentId);
        Student student = marksService.getStudentDetails(studentId);
        List<Subject> allSubjects = marksService.getAllSubjects();

        response.put("totalMarks", totalMarks);
        response.put("student", student);
        response.put("allSubjects", allSubjects);

        return ResponseEntity.ok(response);
    }

}