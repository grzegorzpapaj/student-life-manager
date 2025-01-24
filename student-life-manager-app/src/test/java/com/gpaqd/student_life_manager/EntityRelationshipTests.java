package com.gpaqd.student_life_manager;

import com.gpaqd.student_life_manager.entity.*;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;
import com.gpaqd.student_life_manager.dao.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EntityRelationshipTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private MyTestRepository myTestRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Test
    @DisplayName("Test stworzony dla User, Course, Test oraz Lab")
    void testUserCourseRelationships() {

        User user = new User("testUser", "password123");
        userRepository.save(user);

        CourseId courseId = new CourseId("PAINT", user.getUsername());
        Course course = new Course(
            courseId,
            new BigDecimal("50.00"),  // minPoints
            new BigDecimal("0.00"),  // currentPoints
            new BigDecimal("5.00"),   // minLabsPoints
            new BigDecimal("5.00"),   // minTestsPoints
            new BigDecimal("5.00"),   // minProjectsPoints
            new BigDecimal("5.00")    // minExamsPoints
        );

        user.addCourse(course);

        Threshold threshold = new Threshold(
            new BigDecimal("51.00"),
            new BigDecimal("61.00"),
            new BigDecimal("71.00"),
            new BigDecimal("81.00"),
            new BigDecimal("91.00")
        );

        course.setThreshold(threshold);




        LabId labId = new LabId("PAINT", user.getUsername(), 1);
        Lab lab = new Lab(
            labId,
            "Budowanie Strony Internetowej",
            new BigDecimal("2.00"), // minPoints
            new BigDecimal("0.00"),    // userPoints
            new BigDecimal("5.00"), // maxPoints
            LocalDate.now(),        // date
            LocalDate.now().plusDays(7) // deadline
        );
        course.addLab(lab);

        MyTestId myTestId = new MyTestId("PAINT", user.getUsername(), 1);
        MyTest myTestEntity = new MyTest(
            myTestId,
            "Chapter 1-3 Test",
            new BigDecimal("11.00"), // minPoints
            new BigDecimal("0.00"),    // userPoints
            new BigDecimal("20.00"),// maxPoints
            LocalDate.now().plusDays(1), // date
            false   // isExam
        );
        course.addMyTest(myTestEntity);


        ProjectId projectId = new ProjectId("PAINT", user.getUsername(), 1);
        Project project = new Project(
            projectId,
            "Piekny projekt na PAINT", //description
            new BigDecimal("3.00"), //minPoints
            new BigDecimal("0.00"), //userPoints
            new BigDecimal("50.00"), //maxPoints
            LocalDate.now().plusDays(30) //deadline
        );
        course.addProject(project);

        userRepository.save(user); 

        Optional<User> fetchedOptUser = userRepository.findById("testUser");
        assertTrue(fetchedOptUser.isPresent(), "User should be present in the database");

        User fetchedUser = fetchedOptUser.get();
        assertEquals(1, fetchedUser.getCourses().size(), "User should have 1 course");

        Course fetchedCourse = fetchedUser.getCourses().get(0);
        assertEquals("PAINT", fetchedCourse.getId().getCourseName(), "Course name should match");
        assertEquals("testUser", fetchedCourse.getId().getOwnedByUser(), "Username should match");
        assertEquals(1, fetchedCourse.getLabs().size(), "Course should have 1 lab");
        assertEquals(1, fetchedCourse.getMyTests().size(), "Course should have 1 test");
        assertEquals(1, fetchedCourse.getProjects().size(), "Course should have 1 project");


        Lab fetchedLab = fetchedCourse.getLabs().get(0);
        assertEquals(1, fetchedLab.getId().getLabNumber(), "Lab number should match");
        assertEquals("PAINT", fetchedLab.getId().getCourseName(), "Course name in Lab ID should match");
        assertEquals("Budowanie Strony Internetowej", fetchedLab.getDescription(), "Lab description should match");
        assertEquals(new BigDecimal("2.00"), fetchedLab.getMinPoints(), "Lab minPoints should match");


        MyTest fetchedTest = fetchedCourse.getMyTests().get(0);
        assertEquals(1, fetchedTest.getId().getMyTestNumber(), "Test number should match");
        assertEquals("Chapter 1-3 Test", fetchedTest.getDescription(), "Test description should match");
        assertEquals(new BigDecimal("11.00"), fetchedTest.getMinPoints(), "Test minPoints should match");
        assertFalse(fetchedTest.isExam(), "Test should not be an exam");


        Project fetchedProject = fetchedCourse.getProjects().get(0);
        assertEquals(1, fetchedProject.getId().getProjectNumber(), "Project Number should match");
        assertEquals("Piekny projekt na PAINT", fetchedProject.getDescription(), "Project description should match");
        assertEquals(new BigDecimal("3.00"), fetchedProject.getMinPoints(), "Project minPoints should match");
        assertEquals(new BigDecimal("0.00"), fetchedProject.getUserPoints(), "Project userPoints should match");

    }
}
