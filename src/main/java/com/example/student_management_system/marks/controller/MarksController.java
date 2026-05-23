package com.example.student_management_system.marks.controller;

import com.example.student_management_system.marks.dto.AddMarksRequest;
import com.example.student_management_system.marks.dto.MarksResponse;
import com.example.student_management_system.marks.service.MarksService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
public class MarksController {

    private final MarksService marksService;

    // ADD MARKS

    @PostMapping
    public MarksResponse addMarks(
            @Valid @RequestBody AddMarksRequest request
    ) {

        return marksService.addMarks(request);
    }

    // GET STUDENT MARKS

    @GetMapping("/student/{studentId}")
    public List<MarksResponse> getStudentMarks(
            @PathVariable Long studentId
    ) {

        return marksService.getStudentMarks(studentId);
    }

    // GET STUDENT AVERAGE

    @GetMapping("/average/{studentId}")
    public double getAverage(
            @PathVariable Long studentId
    ) {

        return marksService
                .calculateStudentAverage(studentId);
    }

    // GET GPA

    @GetMapping("/gpa/{studentId}")
    public double getGPA(
            @PathVariable Long studentId
    ) {

        return marksService.calculateGPA(studentId);
    }
}