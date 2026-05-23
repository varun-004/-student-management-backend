package com.example.student_management_system.course.controller;

import com.example.student_management_system.course.dto.CreateCourseRequest;
import com.example.student_management_system.course.dto.CourseResponse;
import com.example.student_management_system.course.service.CourseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(
            @Valid @RequestBody CreateCourseRequest request
    ) {
        return courseService.createCourse(request);
    }

    @PutMapping("/{courseId}")
    public CourseResponse updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody CreateCourseRequest request
    ) {
        return courseService.updateCourse(courseId, request);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(
            @PathVariable Long courseId
    ) {
        courseService.deleteCourse(courseId);
    }

    @GetMapping
    public Page<CourseResponse> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return courseService.getAllCourses(
                page,
                size,
                search
        );
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public CourseResponse assignStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {
        return courseService.assignStudentToCourse(
                studentId,
                courseId
        );
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public CourseResponse removeStudentFromCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {
        return courseService.removeStudentFromCourse(
                studentId,
                courseId
        );
    }
}