package com.example.student_management_system.course.service;

import com.example.student_management_system.course.dto.CreateCourseRequest;
import com.example.student_management_system.course.dto.CourseResponse;
import org.springframework.data.domain.Page;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);


    CourseResponse updateCourse(
            Long courseId,
            CreateCourseRequest request
    );

    void deleteCourse(Long courseId);

    Page<CourseResponse> getAllCourses(
            int page,
            int size,
            String search
    );
    CourseResponse assignStudentToCourse(
            Long studentId,
            Long courseId
    );

    CourseResponse removeStudentFromCourse(
            Long studentId,
            Long courseId
    );
}