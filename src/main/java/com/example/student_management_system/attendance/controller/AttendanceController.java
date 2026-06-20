        package com.example.student_management_system.attendance.controller;

import com.example.student_management_system.attendance.dto.AttendanceResponse;
import com.example.student_management_system.attendance.dto.MarkAttendanceRequest;

import com.example.student_management_system.attendance.entity.Attendance;
import com.example.student_management_system.attendance.service.AttendanceService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/attendance")

@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService
            attendanceService;

    /*
    |--------------------------------------------------------------------------
    | MARK ATTENDANCE
    |--------------------------------------------------------------------------
    */

    @PostMapping
    public AttendanceResponse markAttendance(
            @RequestBody
            MarkAttendanceRequest request
    ) {

        return attendanceService
                .markAttendance(request);
    }

    /*
    |--------------------------------------------------------------------------
    | COURSE ATTENDANCE
    |--------------------------------------------------------------------------
    */

    @GetMapping("/course/{courseId}")
    public List<AttendanceResponse>
    getAttendanceByCourse(
            @PathVariable Long courseId
    ) {

        return attendanceService
                .getAttendanceByCourse(
                        courseId
                );
    }

    /*
    |--------------------------------------------------------------------------
    | STUDENT ATTENDANCE
    |--------------------------------------------------------------------------
    */

    @GetMapping("/student/{studentId}")
    public List<AttendanceResponse>
    getAttendanceByStudent(
            @PathVariable Long studentId
    ) {

        return attendanceService
                .getAttendanceByStudent(
                        studentId
                );
    }

    @GetMapping(
            "/percentage/{studentId}"
    )
    public Double getAttendancePercentage(
            @PathVariable Long studentId
    ) {

        return attendanceService
                .getAttendancePercentage(
                        studentId
                );
    }


}
