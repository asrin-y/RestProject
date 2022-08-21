package com.example.MyRestProject.repository;

import com.example.MyRestProject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    //Find course by name
    List<Course> findByCourseName(String courseName);
    
    //Find courses which has given min credit
    List<Course> findByCourseCreditAfter(Double courseCredit);
}
