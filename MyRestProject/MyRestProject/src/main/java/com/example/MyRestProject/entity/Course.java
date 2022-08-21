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
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private Long courseMinTermRequired;
    private Double courseCredit;
    private Boolean courseIsActive;

    @ElementCollection
    private List<Long> courseEnrolledStudents;

    @ElementCollection
    private List<Long> coursePreRequisites;

    public void courseAddToEnrolledStudentsList(Long id) {
        courseEnrolledStudents.add(id);
    }

    public void courseRemoveFromEnrolledStudentsList(Long id){
        courseEnrolledStudents.remove(id);
    }

    public Boolean courseDoesExistInEnrolledStudentsList(Long id){
        return courseEnrolledStudents.contains(id);
    }

    public void courseAddToPreRequisitesList(Long id) {
        coursePreRequisites.add(id);
    }

    public void courseRemoveFromPreRequisitesList(Long id){
        coursePreRequisites.remove(id);
    }

    public Boolean courseDoesExistInPreRequisitesList(Long id){
        return coursePreRequisites.contains(id);
    }
}

