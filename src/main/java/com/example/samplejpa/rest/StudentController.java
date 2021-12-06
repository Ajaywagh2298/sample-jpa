package com.example.samplejpa.rest;

import com.example.samplejpa.jpa.StudentRepository;
import com.example.samplejpa.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/student")
    public Student create(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/student/{id}")
    public Student get(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/student")
    public List<Student> list() {
        return repository.findAll();
    }

    @PutMapping("/student/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return repository.findById(id)
                .map(s -> {
                    if (student.getFirstName() != null) s.setFirstName(student.getFirstName());
                    if (student.getLastName() != null) s.setLastName(student.getLastName());
                    if (student.getEmailId() != null) s.setEmailId(student.getEmailId());
                    if (student.getPhone() != null) s.setPhone(student.getPhone());
                    return repository.save(s);
                })
                .orElseGet(() -> {
                    student.setId(id);
                    return repository.save(student);
                });
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/student")
    public void deleteAll() {
        repository.deleteAll();
    }

}
