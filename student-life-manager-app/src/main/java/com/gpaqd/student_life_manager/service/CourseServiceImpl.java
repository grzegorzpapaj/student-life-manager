package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.CourseRepository;
import com.gpaqd.student_life_manager.dto.CourseDetailsDTO;
import com.gpaqd.student_life_manager.dto.LabDTO;
import com.gpaqd.student_life_manager.dto.MyTestDTO;
import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.Threshold;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private ThresholdService thresholdService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ThresholdService thresholdService) {
        this.courseRepository = courseRepository;
        this.thresholdService = thresholdService;
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

    @Override
    public Course saveCourseWithDTO(CourseDetailsDTO dto, String username) {
        Threshold thresholdToUse = findOrCreateThresholdToUse(dto);
        Course courseToUse = createCourseObjectToUse(dto, username);

        courseToUse.setThreshold(thresholdToUse);
        Course savedCourse = courseRepository.save(courseToUse);

        Course courseWithLabs = addLabs(dto, savedCourse, username);
        Course courseWithTests = addTests(dto, courseWithLabs, username);

        return courseRepository.save(courseWithTests);
    }

    private Course addLabs(CourseDetailsDTO dto, Course savedCourse, String username) {
        if (dto.getLabs() != null) {
            for (LabDTO labDto : dto.getLabs()) {
                if (labDto.getLabNumber() == null) {
                    // pomijamy puste wiersze
                    continue;
                }
                LabId labId = new LabId(
                        dto.getCourseName(),
                        username,
                        labDto.getLabNumber()
                );
                Lab lab = new Lab(labId,
                        labDto.getDescription(),
                        labDto.getMinPoints() != null ? labDto.getMinPoints() : BigDecimal.ZERO,
                        labDto.getUserPoints(),
                        labDto.getMaxPoints() != null ? labDto.getMaxPoints() : BigDecimal.ZERO,
                        labDto.getDate(),
                        labDto.getDeadline());
                // powiązanie z course
                lab.setCourse(savedCourse);

                savedCourse.addLab(lab);
                // addLab() w encji Course ustawia lab.setCourse(this).
            }
        }
        return savedCourse;
    }

    private Course addTests(CourseDetailsDTO dto, Course savedCourse, String username) {
        if (dto.getMyTests() != null) {
            for (MyTestDTO testDto : dto.getMyTests()) {
                if (testDto.getTestNumber() == null) {
                    // pomijamy puste wiersze
                    continue;
                }

                MyTestId myTestId = new MyTestId(
                        savedCourse.getId().getCourseName(),
                        username,
                        testDto.getTestNumber()
                );

                MyTest test = new MyTest(
                        myTestId,
                        testDto.getDescription(),
                        testDto.getMinPoints() != null ? testDto.getMinPoints() : BigDecimal.ZERO,
                        testDto.getUserPoints(),
                        testDto.getMaxPoints() != null ? testDto.getMaxPoints() : BigDecimal.ZERO,
                        testDto.getDate() != null ? testDto.getDate() : LocalDate.now(),
                        testDto.isExam()
                );

                test.setCourse(savedCourse);
                savedCourse.addMyTest(test);
                // addMyTest() w encji Course ustawia test.setCourse(this).
            }
        }
        return savedCourse;
    }


    private Threshold findOrCreateThresholdToUse(CourseDetailsDTO dto) {
        Threshold existingThreshold = thresholdService.findByAllPoints(
                dto.getPoints3(), dto.getPoints3_5(),
                dto.getPoints4(), dto.getPoints4_5(),
                dto.getPoints5()
        );

        Threshold thresholdToUse;
        if (existingThreshold != null) {
            thresholdToUse = existingThreshold;
        } else {
            Threshold newThreshold = new Threshold(
                    dto.getPoints3(), dto.getPoints3_5(),
                    dto.getPoints4(), dto.getPoints4_5(),
                    dto.getPoints5()
            );
            thresholdToUse = thresholdService.save(newThreshold);
        }

        return thresholdToUse;
    }

    private Course createCourseObjectToUse(CourseDetailsDTO dto, String username) {
        CourseId courseId = new CourseId(dto.getCourseName(), username);
        Course course = new Course();
        course.setId(courseId);

        course.setMinPoints(dto.getPoints3() != null ? dto.getPoints3() : BigDecimal.ZERO);
        course.setCurrentPoints(dto.getCurrentPoints() != null ? dto.getCurrentPoints() : BigDecimal.ZERO);
        course.setMinLabsPoints(dto.getMinLabsPoints());
        course.setMinTestsPoints(dto.getMinTestsPoints());
        course.setMinProjectsPoints(dto.getMinProjectsPoints());
        course.setMinExamsPoints(dto.getMinExamsPoints());

        return course;
    }
}
