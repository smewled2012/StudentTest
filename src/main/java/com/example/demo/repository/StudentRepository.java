package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
   // findStudent
   // Iterable<Student> findallByStudentFirstName(String name);
    Iterable<Student> findAllStudentByFirstName(String name);
}
