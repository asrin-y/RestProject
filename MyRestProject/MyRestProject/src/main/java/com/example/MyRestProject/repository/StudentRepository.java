package com.example.MyRestProject.repository;

import com.example.MyRestProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //Find student by name
    List<Student> findByStudentName(String studentName);

    //Find students which has higher gpa after certain value
    List<Student> findByStudentGpaAfter(Double studentGpa);
}
