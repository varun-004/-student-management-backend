package com.example.student_management_system.course.entity;
import com.example.student_management_system.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    private String description;

    @Column(nullable = false)
    private Integer credits;

    @ManyToMany(mappedBy = "courses")
    @Builder.Default
    private Set<Student> students = new HashSet<>();
}