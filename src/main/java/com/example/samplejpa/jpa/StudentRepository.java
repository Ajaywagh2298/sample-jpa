package com.example.samplejpa.jpa;

import com.example.samplejpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
