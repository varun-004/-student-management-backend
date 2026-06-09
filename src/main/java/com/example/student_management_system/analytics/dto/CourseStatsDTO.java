package com.example.student_management_system.analytics.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStatsDTO {

    private String courseName;

    private long totalStudents;
}   