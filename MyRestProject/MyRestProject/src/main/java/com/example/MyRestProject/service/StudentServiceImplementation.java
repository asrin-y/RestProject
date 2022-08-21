package com.example.MyRestProject.service;

import com.example.MyRestProject.entity.Course;
import com.example.MyRestProject.entity.Student;
import com.example.MyRestProject.exceptions.*;
import com.example.MyRestProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImplementation implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public Student studentSave(Student student) throws StudentConstructorException {
        if(!Objects.nonNull(student.getStudentTotalCredit())
                || !Objects.nonNull(student.getStudentTerm())
                || !Objects.nonNull(student.getStudentGpa())
                || !Objects.nonNull(student.getStudentIsActive()))
            throw new StudentConstructorException("");
        return studentRepository.save(student);
    }

    @Override
    public Student studentFindById(Long studentId) throws NoSuchStudentException {
        Student returnStudent;
        if(studentRepository.findById(studentId).isEmpty())
            throw new NoSuchStudentException(studentId.toString());
        returnStudent = studentRepository.findById(studentId).get();
        return returnStudent;
    }

    @Override
    public List<Student> studentFindByName(String studentName) {
        return studentRepository.findByStudentName(studentName);
    }

    @Override
    public List<Student> studentFindAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> studentFindByGpaAfter(Double studentGpa) {
        return studentRepository.findByStudentGpaAfter(studentGpa);
    }

    @Override
    public Student studentUpdateById(Long studentId, Student student) throws NoSuchStudentException {
        Student studentDB = studentFindById(studentId);

        if(!Objects.equals(student.getStudentName(), "") &&
                Objects.nonNull(student.getStudentName())){
            studentDB.setStudentName(student.getStudentName());
        }
        if(Objects.nonNull(student.getStudentTerm())){
            studentDB.setStudentTerm(student.getStudentTerm());
        }
        if(Objects.nonNull(student.getStudentTotalCredit())){
            studentDB.setStudentTotalCredit(student.getStudentTotalCredit());
        }
        if(Objects.nonNull(student.getStudentGpa())){
            studentDB.setStudentGpa(student.getStudentGpa());
        }
        if(Objects.nonNull(student.getStudentIsActive())){
            studentDB.setStudentIsActive(student.getStudentIsActive());
        }
        if(Objects.nonNull(student.getStudentEnrolledCourses())){
            studentDB.setStudentEnrolledCourses(student.getStudentEnrolledCourses());
        }
        if(Objects.nonNull(student.getStudentSuccessfulCourses())){
            studentDB.setStudentSuccessfulCourses(student.getStudentSuccessfulCourses());
        }

        return studentRepository.save(studentDB);

    }

    @Override
    public Student studentEnrollCourse(Long studentId, Long courseId)
            throws NoSuchStudentException, 
            NoSuchCourseException, 
            CourseIsNotActiveException, 
            CourseMinTermRequiredException, 
            CoursePreRequisiteException {
        Student studentDB = studentFindById(studentId);
        Course courseDB = courseService.courseGetById(courseId);
        List<Long> missingPreRequisites = studentCheckPrerequisites(studentId,courseId);
        if(!courseDB.getCourseIsActive())
            throw new CourseIsNotActiveException(courseId.toString());
        if(courseDB.getCourseMinTermRequired() > studentDB.getStudentTerm())
            throw new CourseMinTermRequiredException(studentId,courseId);
        if(!missingPreRequisites.isEmpty())
           throw new CoursePreRequisiteException(missingPreRequisites,courseId,studentId);
        studentDB.studentAddToEnrolledCoursesList(courseId);
        courseService.courseEnroll(studentId,courseId);
        return studentRepository.save(studentDB);
    }

    @Override
    public Student studentDropCourse(Long studentId, Long courseId)
            throws NoSuchStudentException, NoSuchCourseException {
        Student studentDB = studentFindById(studentId);
        courseService.courseDrop(studentId,courseId);
        studentDB.studentRemoveFromEnrolledCoursesList(courseId);

        return studentRepository.save(studentDB);
    }

    @Override
    public List<Long> studentCheckPrerequisites(Long studentId, Long courseId)
            throws NoSuchStudentException, NoSuchCourseException {
        Student studentDB = studentFindById(studentId);
        Course courseDB = courseService.courseGetById(courseId);
        List<Long> notTakenPrerequisite = new java.util.ArrayList<>(Collections.emptyList());
        for(int i = 0; i < courseDB.getCoursePreRequisites().size(); i++){
            Long preRequisite = courseDB.getCoursePreRequisites().get(i);
            if(!studentDB.getStudentSuccessfulCourses().contains(preRequisite)){
                notTakenPrerequisite.add(preRequisite);
            }
        }
        
        return notTakenPrerequisite;
    }
}
