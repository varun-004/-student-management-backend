package com.example.student_management_system.teacher.service;

import com.example.student_management_system.teacher.dto.CourseAnalyticsResponse;
import com.example.student_management_system.teacher.dto.CreateTeacherRequest;
import com.example.student_management_system.teacher.dto.TeacherResponse;
import com.example.student_management_system.teacher.dto.UpdateTeacherRequest;

import java.util.List;

public interface TeacherService {

    TeacherResponse createTeacher(CreateTeacherRequest request);

    List<TeacherResponse> getAllTeachers();

    TeacherResponse getTeacherById(Long id);

    TeacherResponse updateTeacher(Long id,
                                  UpdateTeacherRequest request);

    void deleteTeacher(Long id);

    CourseAnalyticsResponse
    getCourseAnalytics(Long courseId);

    TeacherResponse getTeacherByEmail(
            String email
    );

}