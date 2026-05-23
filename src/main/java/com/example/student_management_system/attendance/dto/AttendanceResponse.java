package com.example.student_management_system.attendance.dto;

import com.example.student_management_system.attendance.entity.AttendanceStatus;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AttendanceResponse {

    private Long id;

    private LocalDate date;

    private AttendanceStatus status;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;
}