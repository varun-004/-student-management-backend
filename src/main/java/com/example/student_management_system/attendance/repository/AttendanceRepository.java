        package com.example.student_management_system.attendance.repository;

import com.example.student_management_system.attendance.entity.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

        public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    @Query("""
        SELECT COUNT(a)
        FROM Attendance a
        WHERE a.student.id = :studentId
    """)
    Long getTotalAttendance(
            @Param("studentId")
            Long studentId
    );

    @Query("""
        SELECT COUNT(a)
        FROM Attendance a
        WHERE a.student.id = :studentId
        AND a.present = true
    """)
    Long getPresentAttendance(
            @Param("studentId")
            Long studentId
    );

    @Query("""
        SELECT AVG(
            CASE
                WHEN a.present = true THEN 100
                ELSE 0
            END
        )
        FROM Attendance a
    """)
    Double getAverageAttendance();

    @Query("""
        SELECT AVG(
            CASE
                WHEN a.present = true THEN 100
                ELSE 0
            END
        )
        FROM Attendance a
        WHERE a.student.id = :studentId
    """)
    Double getAverageAttendanceByStudent(
            @Param("studentId")
            Long studentId
    );

    List<Attendance> findByCourseId(Long courseId);

}
