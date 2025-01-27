package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.CourseRepository;
import com.gpaqd.student_life_manager.dto.CourseDetailsDTO;
import com.gpaqd.student_life_manager.dto.LabDTO;
import com.gpaqd.student_life_manager.dto.MyTestDTO;
import com.gpaqd.student_life_manager.dto.ProjectDTO;
import com.gpaqd.student_life_manager.entity.*;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Course courseWithProjects = addProjects(dto, courseWithTests, username);

        return courseRepository.save(courseWithProjects);
    }

    @Override
    public CourseDetailsDTO getCourseDetailsDTO(CourseId courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return null;
        }

        CourseDetailsDTO dto = new CourseDetailsDTO();
        CourseDetailsDTO dtoWithCourseInfo = setDtoInfoFromCourse(dto, course);

        List<LabDTO> labDTOs = course.getLabs().stream().map(this::convertLabToDTO).collect(Collectors.toList());
        List<MyTestDTO> myTestDTOs = course.getMyTests().stream().map(this::convertMyTestToDTO).collect(Collectors.toList());
        List<ProjectDTO> projectDTOs = course.getProjects().stream().map(this::convertProjectToDTO).collect(Collectors.toList());
        dtoWithCourseInfo.setLabs(labDTOs);
        dtoWithCourseInfo.setMyTests(myTestDTOs);
        dtoWithCourseInfo.setProjects(projectDTOs);

        return dtoWithCourseInfo;
    }

    @Override
    public CourseDetailsDTO getEditCourseDetailsDTO(String username, String courseName) {
        CourseId courseId = new CourseId(courseName, username);
        return getCourseDetailsDTO(courseId);
    }

    @Override
    public Course updateCourseWithDTO(CourseDetailsDTO dto, String username) {
        CourseId courseId = new CourseId(dto.getCourseName(), username);

        Course existingCourse = courseRepository.findById(courseId).orElse(null);

        // update fields
        Course updatedCourse = setCoursePoints(existingCourse, dto);

        // threshold
        Threshold thr = findOrCreateThresholdToUse(dto);

        updatedCourse.setThreshold(thr);

        // labs, tests, projects:
        // 1) Wczytaj bieżące z existingCourse
        // 2) Wyczyść / usuń
        existingCourse.getLabs().clear();
        existingCourse.getMyTests().clear();
        existingCourse.getProjects().clear();
        // 3) addLabs(dto, existingCourse, username) itp.
        addLabs(dto, existingCourse, username);
        addTests(dto, existingCourse, username);
        addProjects(dto, existingCourse, username);

        return courseRepository.save(existingCourse);
    }

     @Override
    public BigDecimal calculateTotalPoints(Course course) {
        // Sum up labs
        BigDecimal labsTotal = course.getLabs().stream()
            .map(lab -> lab.getUserPoints() != null ? lab.getUserPoints() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Sum up tests (including exams if isExam == true)
        BigDecimal testsTotal = course.getMyTests().stream()
            .map(test -> test.getUserPoints() != null ? test.getUserPoints() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Sum up projects
        BigDecimal projectsTotal = course.getProjects().stream()
            .map(project -> project.getUserPoints() != null ? project.getUserPoints() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return labsTotal.add(testsTotal).add(projectsTotal);
    }

    @Override
    public String determineGrade(Course course, BigDecimal totalPoints) {
        // If total points are below the course's min points => grade = "2"
        // if (totalPoints.compareTo(course.getMinPoints()) < 0) {
        //     return "2";
        // }

        Threshold threshold = course.getThreshold(); // e.g. threshold_id -> Threshold object
        if (threshold == null) {
            // If there's no threshold row, return something default
            return "N/A";
        }

        // Compare from highest to lowest
        if (totalPoints.compareTo(threshold.getPoints5()) >= 0) {
            return "5";
        } else if (totalPoints.compareTo(threshold.getPoints4_5()) >= 0) {
            return "4.5";
        } else if (totalPoints.compareTo(threshold.getPoints4()) >= 0) {
            return "4";
        } else if (totalPoints.compareTo(threshold.getPoints3_5()) >= 0) {
            return "3.5";
        } else if (totalPoints.compareTo(threshold.getPoints3()) >= 0) {
            return "3";
        } else {
            return "2"; // fallback
        }
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

    private Course addProjects(CourseDetailsDTO dto, Course savedCourse, String username) {
        if (dto.getProjects() != null) {
            for (ProjectDTO projectDto : dto.getProjects()) {
                if (projectDto.getProjectNumber() == null) {
                    continue;
                }
                ProjectId projectId = new ProjectId(
                        savedCourse.getId().getCourseName(),
                        username,
                        projectDto.getProjectNumber()
                );

                Project project = new Project(
                        projectId,
                        projectDto.getDescription(),
                        projectDto.getMinPoints() != null ? projectDto.getMinPoints() : BigDecimal.ZERO,
                        projectDto.getUserPoints(),
                        projectDto.getMaxPoints() != null ? projectDto.getMaxPoints() : BigDecimal.ZERO,
                        projectDto.getDeadline()
                );

                project.setCourse(savedCourse);
                savedCourse.addProject(project);
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

        // ---------------------------------------------
        // PRZENIESC TO DO INNEJ FUNKCJI

//        BigDecimal default3   = new BigDecimal("51");
//        BigDecimal default3_5 = new BigDecimal("61");
//        BigDecimal default4   = new BigDecimal("71");
//        BigDecimal default4_5 = new BigDecimal("81");
//        BigDecimal default5   = new BigDecimal("91");
//
//        // 2. Jeśli w DTO nie ma wartości, wstaw domyślną
//        BigDecimal p3   = (dto.getPoints3()   != null) ? dto.getPoints3()   : default3;
//        BigDecimal p3_5 = (dto.getPoints3_5() != null) ? dto.getPoints3_5() : default3_5;
//        BigDecimal p4   = (dto.getPoints4()   != null) ? dto.getPoints4()   : default4;
//        BigDecimal p4_5 = (dto.getPoints4_5() != null) ? dto.getPoints4_5() : default4_5;
//        BigDecimal p5   = (dto.getPoints5()   != null) ? dto.getPoints5()   : default5;

        // ----------------------------------------------

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

//        course.setMinPoints(dto.getPoints3() != null ? dto.getPoints3() : BigDecimal.ZERO);
//        course.setCurrentPoints(dto.getCurrentPoints() != null ? dto.getCurrentPoints() : BigDecimal.ZERO);
//        course.setMinLabsPoints(dto.getMinLabsPoints());
//        course.setMinTestsPoints(dto.getMinTestsPoints());
//        course.setMinProjectsPoints(dto.getMinProjectsPoints());
//        course.setMinExamsPoints(dto.getMinExamsPoints());

        return setCoursePoints(course, dto);
    }

    

    private LabDTO convertLabToDTO(Lab lab) {
        LabDTO labDTO = new LabDTO();
        labDTO.setLabNumber(lab.getId().getLabNumber());
        labDTO.setDescription(lab.getDescription());
        labDTO.setMinPoints(lab.getMinPoints());
        labDTO.setMaxPoints(lab.getMaxPoints());
        labDTO.setUserPoints(lab.getUserPoints());
        labDTO.setDate(lab.getDate());
        labDTO.setDeadline(lab.getDeadline());
        return labDTO;
    }

    private MyTestDTO convertMyTestToDTO(MyTest myTest) {
        MyTestDTO myTestDto = new MyTestDTO();
        myTestDto.setTestNumber(myTest.getId().getMyTestNumber());
        myTestDto.setDescription(myTest.getDescription());
        myTestDto.setMinPoints(myTest.getMinPoints());
        myTestDto.setMaxPoints(myTest.getMaxPoints());
        myTestDto.setUserPoints(myTest.getUserPoints());
        myTestDto.setDate(myTest.getDate());
        myTestDto.setExam(myTest.isExam());
        return myTestDto;
    }

    private ProjectDTO convertProjectToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectNumber(project.getId().getProjectNumber());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setMinPoints(project.getMinPoints());
        projectDTO.setMaxPoints(project.getMaxPoints());
        projectDTO.setUserPoints(project.getUserPoints());
        projectDTO.setDeadline(project.getDeadline());
        return projectDTO;
    }

    private Course setCoursePoints(Course course, CourseDetailsDTO dto) {
        course.setMinPoints(dto.getPoints3() != null ? dto.getPoints3() : BigDecimal.ZERO);
        course.setCurrentPoints(dto.getCurrentPoints() != null ? dto.getCurrentPoints() : BigDecimal.ZERO);
        course.setMinLabsPoints(dto.getMinLabsPoints());
        course.setMinTestsPoints(dto.getMinTestsPoints());
        course.setMinProjectsPoints(dto.getMinProjectsPoints());
        course.setMinExamsPoints(dto.getMinExamsPoints());

        return course;
    }

    private CourseDetailsDTO setDtoInfoFromCourse(CourseDetailsDTO dto, Course course) {
        dto.setCourseName(course.getId().getCourseName());
        dto.setCurrentPoints(course.getCurrentPoints());
        dto.setMinPoints(course.getMinPoints());
        dto.setMinLabsPoints(course.getMinLabsPoints());
        dto.setMinTestsPoints(course.getMinTestsPoints());
        dto.setMinProjectsPoints(course.getMinProjectsPoints());
        dto.setMinExamsPoints(course.getMinExamsPoints());

        Threshold thr = course.getThreshold();
        dto.setPoints3(thr.getPoints3());
        dto.setPoints3_5(thr.getPoints3_5());
        dto.setPoints4(thr.getPoints4());
        dto.setPoints4_5(thr.getPoints4_5());
        dto.setPoints5(thr.getPoints5());

        return dto;
    }

}
