package com.example.student_management_system.attendance.repository;

import com.example.student_management_system.attendance.entity.Attendance;
import com.example.student_management_system.course.entity.Course;
import com.example.student_management_system.student.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    boolean existsByStudentAndCourseAndDate(
            Student student,
            Course course,
            LocalDate date
    );

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByCourseId(Long courseId);
}