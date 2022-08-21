package com.example.MyRestProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String studentName;
    private Long studentTerm;
    private Double studentTotalCredit;
    private Double studentGpa;
    private Boolean studentIsActive;

    @ElementCollection
    private List<Long> studentEnrolledCourses;

    @ElementCollection
    private List<Long> studentSuccessfulCourses;

    public void studentAddToEnrolledCoursesList(Long id) {

        studentEnrolledCourses.add(id);
    }

    public void studentRemoveFromEnrolledCoursesList(Long id){
        studentEnrolledCourses.remove(id);
    }

    public Boolean studentDoesExistInEnrolledCoursesList(Long id){
        return studentEnrolledCourses.contains(id);
    }

    public void studentAddToSuccessfulCoursesList(Long id) {

        studentSuccessfulCourses.add(id);
    }

    public void studentRemoveFromSuccessfulCoursesList(Long id){

        studentSuccessfulCourses.remove(id);
    }

    public Boolean studentDoesExistInSuccessfulCoursesList(Long id){
        return
                studentSuccessfulCourses.contains(id);
    }
}
