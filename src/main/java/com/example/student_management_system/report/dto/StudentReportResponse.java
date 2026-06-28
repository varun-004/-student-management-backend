package com.example.student_management_system.report.dto;

import com.example.student_management_system.marks.dto.MarksResponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentReportResponse {

    private Long studentId;

    private String studentName;

    private String email;

    private Double attendancePercentage;

    private Double gpa;

    private List<MarksResponse> marks;
}