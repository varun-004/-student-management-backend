package com.example.student_management_system.attendance.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseAttendanceResponse {

    private Long courseId;

    private String courseName;

    private double percentage;
}