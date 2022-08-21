package com.example.MyRestProject.service;

import com.example.MyRestProject.entity.Student;
import com.example.MyRestProject.exceptions.*;

import java.util.List;

public interface StudentService {

    //save student
    Student studentSave(Student student) throws StudentConstructorException, StudentConstructorException;

    //find student by id
    Student studentFindById(Long studentId) throws NoSuchStudentException;

    //find students by name
    List<Student> studentFindByName(String studentName);

    //find all students
    List<Student> studentFindAll();

    //find students which has higher gpa than x
    List<Student> studentFindByGpaAfter(Double studentGpa);

    //update student info by id
    Student studentUpdateById(Long studentId, Student student) throws NoSuchStudentException;

    //enroll course
    Student studentEnrollCourse(Long studentId, Long courseId) throws NoSuchStudentException, NoSuchCourseException, CourseIsNotActiveException, CourseMinTermRequiredException, CoursePreRequisiteException;

    //drop course
    Student studentDropCourse(Long studentId, Long courseId) throws NoSuchStudentException, NoSuchCourseException;

    //check prerequisites
    List<Long> studentCheckPrerequisites(Long studentId, Long courseId) throws NoSuchStudentException, NoSuchCourseException;
}
