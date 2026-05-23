package com.example.student_management_system.attendance.controller;

import com.example.student_management_system.attendance.dto.AttendanceResponse;
import com.example.student_management_system.attendance.dto.MarkAttendanceRequest;
import com.example.student_management_system.attendance.service.AttendanceService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // MARK ATTENDANCE

    @PostMapping
    public AttendanceResponse markAttendance(
            @Valid @RequestBody MarkAttendanceRequest request
    ) {

        return attendanceService.markAttendance(request);
    }

    // GET STUDENT ATTENDANCE HISTORY

    @GetMapping("/student/{studentId}")
    public List<AttendanceResponse> getStudentAttendance(
            @PathVariable Long studentId
    ) {

        return attendanceService
                .getStudentAttendance(studentId);
    }

    // GET ATTENDANCE PERCENTAGE

    @GetMapping("/percentage")
    public double getAttendancePercentage(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {

        return attendanceService
                .getAttendancePercentage(
                        studentId,
                        courseId
                );
    }
}