package com.example.student_management_system.repository;

import com.example.student_management_system.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Student> findByNameContainingIgnoreCase(String name);
}
