package com.example.MyRestProject.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class CoursePreRequisiteException extends Exception{

    private List<Long> missingPrerequisites;
    private Long courseId;
    private Long studentId;

    public CoursePreRequisiteException(List<Long> missingPrerequisites, Long courseId, Long studentId) {
        this.missingPrerequisites = missingPrerequisites;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public List<Long> getMissingPrerequisites() {
        return missingPrerequisites;
    }

    public void setMissingPrerequisites(List<Long> missingPrerequisites) {
        this.missingPrerequisites = missingPrerequisites;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
