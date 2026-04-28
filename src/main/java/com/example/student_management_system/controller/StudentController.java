package com.example.student_management_system.controller;
import com.example.student_management_system.DTO.StudentDTO;
import com.example.student_management_system.ResponseDTO.StudentResponseDTO;
import com.example.student_management_system.service.StudentService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public StudentResponseDTO create(@Valid @RequestBody StudentDTO dto) {
        return service.saveStudent(dto);
    }

    // GET ALL
    @GetMapping
    public List<StudentResponseDTO> getAll() {
        return service.getAllStudents();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public StudentResponseDTO getById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public StudentResponseDTO update(@PathVariable Long id,
                                     @Valid @RequestBody StudentDTO dto) {
        return service.updateStudent(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Deleted successfully";
    }

    @GetMapping("/page")
    public Page<StudentResponseDTO> getStudents(Pageable pageable) {
        return service.getStudents(pageable);

    }

    @GetMapping("/search")
    public Page<StudentResponseDTO> search(
            @RequestParam String name,
            Pageable pageable) {

        return service.searchStudents(name, pageable);
    }


}
