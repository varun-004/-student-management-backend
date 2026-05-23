package com.example.student_management_system.attendance.service;

import com.example.student_management_system.attendance.dto.AttendanceResponse;
import com.example.student_management_system.attendance.dto.MarkAttendanceRequest;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse markAttendance(
            MarkAttendanceRequest request
    );

    List<AttendanceResponse> getStudentAttendance(
            Long studentId
    );

    double getAttendancePercentage(
            Long studentId,
            Long courseId
    );
}