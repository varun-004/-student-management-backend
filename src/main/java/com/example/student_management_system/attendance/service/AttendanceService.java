        package com.example.student_management_system.attendance.service;

import com.example.student_management_system.attendance.dto.AttendanceResponse;
import com.example.student_management_system.attendance.dto.CourseAttendanceResponse;
import com.example.student_management_system.attendance.dto.MarkAttendanceRequest;
import com.example.student_management_system.attendance.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    public AttendanceResponse markAttendance(
            MarkAttendanceRequest request);

    List<AttendanceResponse>
    getAttendanceByCourse(
            Long courseId
    );

    List<AttendanceResponse>
    getAttendanceByStudent(
            Long studentId
    );

    double getAttendancePercentage(
            Long studentId
    );
    List<AttendanceResponse>
    getAttendanceByTeacher(
            Long teacherId
    );

    List<CourseAttendanceResponse>
    getCourseWiseAttendance(
            Long studentId
    );


}

