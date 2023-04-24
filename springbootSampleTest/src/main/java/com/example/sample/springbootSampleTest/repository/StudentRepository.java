package com.example.sample.springbootSampleTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.springbootSampleTest.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
