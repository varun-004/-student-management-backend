package com.example.student_management_system.marks.service;

import com.example.student_management_system.marks.dto.AddMarksRequest;
import com.example.student_management_system.marks.dto.MarksResponse;

import java.util.List;

public interface MarksService {

    MarksResponse addMarks(
            AddMarksRequest request
    );

    List<MarksResponse> getStudentMarks(
            Long studentId
    );

    double calculateStudentAverage(
            Long studentId
    );

    double calculateGPA(
            Long studentId
    );
}