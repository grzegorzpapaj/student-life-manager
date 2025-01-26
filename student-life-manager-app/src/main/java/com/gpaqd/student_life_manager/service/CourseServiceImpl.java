package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.CourseRepository;
import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllByUser(String username) {
        return courseRepository.findByIdOwnedByUser(username);
    }

    @Override
    public Course findById(CourseId id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(CourseId id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course createNewEmptyCourseForUser(String username) {
        Course newCourse = new Course();
        newCourse.setId(new CourseId("", username));
        return newCourse;
    }
}
