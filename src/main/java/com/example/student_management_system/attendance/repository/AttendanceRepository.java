        package com.example.student_management_system.attendance.repository;

import com.example.student_management_system.attendance.entity.Attendance;
import com.example.student_management_system.attendance.entity.AttendanceStatus;

import com.example.student_management_system.student.entity.Student;
import com.example.student_management_system.course.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    // Analytics
    @Query("""
        SELECT AVG(
            CASE
                WHEN a.status = com.example.student_management_system.attendance.entity.AttendanceStatus.PRESENT
                THEN 100
                ELSE 0
            END
        )
        FROM Attendance a
    """)
    Double getAverageAttendance();

    // Analytics
    @Query("""
        SELECT AVG(
            CASE
                WHEN a.status = com.example.student_management_system.attendance.entity.AttendanceStatus.PRESENT
                THEN 100
                ELSE 0
            END
        )
        FROM Attendance a
        WHERE a.student.id = :studentId
    """)
    Double getAverageAttendanceByStudent(
            Long studentId
    );

    // REQUIRED FOR AttendanceServiceImpl
    boolean existsByStudentAndCourseAndDate(
            Student student,
            Course course,
            LocalDate date
    );

    // REQUIRED FOR AttendanceServiceImpl
    List<Attendance> findByStudentId(
            Long studentId
    );
}
