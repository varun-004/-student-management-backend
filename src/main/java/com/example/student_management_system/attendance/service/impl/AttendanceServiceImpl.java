package com.example.student_management_system.attendance.service.impl;


import com.example.student_management_system.attendance.dto.AttendanceResponse;
import com.example.student_management_system.attendance.dto.MarkAttendanceRequest;
import com.example.student_management_system.attendance.entity.Attendance;
import com.example.student_management_system.attendance.repository.AttendanceRepository;
import com.example.student_management_system.attendance.service.AttendanceService;

import com.example.student_management_system.course.entity.Course;
import com.example.student_management_system.course.repository.CourseRepository;

import com.example.student_management_system.student.entity.Student;
import com.example.student_management_system.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.example.student_management_system.attendance.entity.AttendanceStatus;

import java.util.List;
import com.example.student_management_system.attendance.entity.AttendanceStatus;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    public AttendanceResponse markAttendance(
            MarkAttendanceRequest request
    ) {

        Student student = studentRepository.findById(
                request.getStudentId()
        ).orElseThrow(() ->
                new RuntimeException("Student not found"));

        Course course = courseRepository.findById(
                request.getCourseId()
        ).orElseThrow(() ->
                new RuntimeException("Course not found"));

        // VALIDATE ENROLLMENT

        if (!student.getCourses().contains(course)) {

            throw new RuntimeException(
                    "Student is not enrolled in this course"
            );
        }

        // PREVENT DUPLICATE ATTENDANCE

        boolean alreadyExists =
                attendanceRepository
                        .existsByStudentAndCourseAndDate(
                                student,
                                course,
                                request.getDate()
                        );

        if (alreadyExists) {

            throw new RuntimeException(
                    "Attendance already marked"
            );
        }

        Attendance attendance = Attendance.builder()
                .date(request.getDate())
                .status(request.getStatus())
                .student(student)
                .course(course)
                .build();

        attendanceRepository.save(attendance);

        return mapToResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getStudentAttendance(
            Long studentId
    ) {

        List<Attendance> attendances =
                attendanceRepository.findByStudentId(studentId);

        return attendances.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public double getAttendancePercentage(
            Long studentId,
            Long courseId
    ) {

        List<Attendance> attendances =
                attendanceRepository.findByStudentId(studentId);

        long totalClasses = attendances.stream()
                .filter(a ->
                        a.getCourse().getId().equals(courseId))
                .count();

        long presentCount = attendances.stream()
                .filter(a ->
                        a.getCourse().getId().equals(courseId))
                .filter(a ->
                        a.getStatus() == AttendanceStatus.PRESENT)
                .count();

        if (totalClasses == 0) {
            return 0;
        }

        return ((double) presentCount / totalClasses) * 100;
    }

    private AttendanceResponse mapToResponse(
            Attendance attendance
    ) {

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .date(attendance.getDate())
                .status(attendance.getStatus())
                .studentId(attendance.getStudent().getId())
                .studentName(attendance.getStudent().getName())
                .courseId(attendance.getCourse().getId())
                .courseName(attendance.getCourse().getCourseName())
                .build();
    }
}