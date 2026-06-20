package com.example.student_management_system.course.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCourseRequest {

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    private String courseName;

    private String description;

    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits cannot exceed 10")
    private Integer credits;
    private Long teacherId;

}