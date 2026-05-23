package com.example.student_management_system.course.service;

import com.example.student_management_system.course.dto.CreateCourseRequest;
import com.example.student_management_system.course.dto.CourseResponse;
import com.example.student_management_system.course.entity.Course;
import com.example.student_management_system.course.repository.CourseRepository;
import com.example.student_management_system.course.service.CourseService;
import com.example.student_management_system.student.entity.Student;
import com.example.student_management_system.student.repository.StudentRepository;
import com.example.student_management_system.student.repository.StudentRepository;
import com.example.student_management_system.student.entity.Student;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;


    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        if (courseRepository.existsByCourseCode(
                request.getCourseCode())) {

            throw new RuntimeException(
                    "Course code already exists"
            );
        }

        Course course = Course.builder()
                .courseCode(request.getCourseCode())
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .credits(request.getCredits())
                .build();

        courseRepository.save(course);

        return mapToResponse(course);
    }

    @Override
    public CourseResponse updateCourse(
            Long courseId,
            CreateCourseRequest request
    ) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setCredits(request.getCredits());

        courseRepository.save(course);

        return mapToResponse(course);
    }

    @Override
    public void deleteCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        courseRepository.delete(course);
    }

    @Override
    public Page<CourseResponse> getAllCourses(
            int page,
            int size,
            String search
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("courseName").ascending()
        );

        Page<Course> courses;

        if (search != null && !search.isBlank()) {

            courses = courseRepository
                    .findByCourseNameContainingIgnoreCase(
                            search,
                            pageable
                    );

        } else {

            courses = courseRepository.findAll(pageable);
        }

        return courses.map(this::mapToResponse);
    }

    private CourseResponse mapToResponse(Course course) {

        return CourseResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .credits(course.getCredits())
                .build();
    }

    @Override
    public CourseResponse assignStudentToCourse(
            Long studentId,
            Long courseId
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (student.getCourses().contains(course)) {
            throw new RuntimeException(
                    "Student already enrolled in course"
            );
        }

        student.getCourses().add(course);

        studentRepository.save(student);

        return mapToResponse(course);
    }

    @Override
    public CourseResponse removeStudentFromCourse(
            Long studentId,
            Long courseId
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        student.getCourses().remove(course);

        studentRepository.save(student);

        return mapToResponse(course);
    }



}