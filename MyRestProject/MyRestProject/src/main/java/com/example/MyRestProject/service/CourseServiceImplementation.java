package com.example.MyRestProject.service;

import com.example.MyRestProject.entity.Course;
import com.example.MyRestProject.exceptions.CourseConstructorException;
import com.example.MyRestProject.exceptions.NoSuchCourseException;
import com.example.MyRestProject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImplementation implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course courseSave(Course course) throws CourseConstructorException {
        if(!Objects.nonNull(course.getCourseMinTermRequired())
                || !Objects.nonNull(course.getCourseIsActive())
                || !Objects.nonNull(course.getCourseCredit())){
            throw new CourseConstructorException("");
        }
        return courseRepository.save(course);
    }

    @Override
    public void courseEnroll(Long studentId, Long courseId)
            throws NoSuchCourseException {
        Course courseDB = courseGetById(courseId);
        courseDB.courseAddToEnrolledStudentsList(studentId);
        courseRepository.save(courseDB);
    }

    @Override
    public void courseDrop(Long studentId, Long courseId) throws NoSuchCourseException {
        Course courseDB = courseGetById(courseId);
        courseDB.courseRemoveFromEnrolledStudentsList(studentId);
        courseRepository.save(courseDB);
    }

    @Override
    public Course courseGetById(Long courseId) throws NoSuchCourseException {
        Course returnCourse;
        if(courseRepository.findById(courseId).isEmpty())
            throw new NoSuchCourseException(courseId.toString());
        returnCourse = courseRepository.findById(courseId).get();
        return  returnCourse;
    }

    @Override
    public List<Course> courseGetByName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public List<Course> courseGetAll() {
        return courseRepository.findAll();
    }

    @Override
    public String courseDelete(Long courseId) throws NoSuchCourseException {
        String deletedCourseName = courseGetById(courseId).getCourseName();
        Long deletedCourseId = courseGetById(courseId).getCourseId();
        courseRepository.delete(courseGetById(courseId));
        return "Course name: \"" + deletedCourseName + "\" with id: " +
                deletedCourseId.toString() + " is deleted successfully";
    }

    @Override
    public Course courseAddPreRequisite(Long courseId, Long preRequisiteId)
            throws NoSuchCourseException {
        Course courseDB = courseGetById(courseId);
        courseGetById(preRequisiteId);
        courseDB.courseAddToPreRequisitesList(preRequisiteId);

        return courseRepository.save(courseDB);
    }
}
