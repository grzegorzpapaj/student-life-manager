package com.gpaqd.student_life_manager.entity;

import com.gpaqd.student_life_manager.entity.pk.CourseId;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @EmbeddedId
    private CourseId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owned_by_user", referencedColumnName = "username",
            insertable = false, updatable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "threshold_id", referencedColumnName = "threshold_id")
    private Threshold threshold;

    @Column(name = "min_points", nullable = false)
    private BigDecimal minPoints;

    @Column(name = "current_points", nullable = false)
    private BigDecimal currentPoints;

    @Column(name = "min_labs_points")
    private BigDecimal minLabsPoints;

    @Column(name = "min_tests_points")
    private BigDecimal minMyTestsPoints;

    @Column(name = "min_projects_points")
    private BigDecimal minProjectsPoints;

    @Column(name = "min_exams_points")
    private BigDecimal minExamsPoints;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyTest> myTests = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lab> labs = new ArrayList<>();

    public Course() {
    }

    public Course(CourseId id,
                  BigDecimal minPoints,
                  BigDecimal currentPoints,
                  BigDecimal minLabsPoints,
                  BigDecimal minMyTestsPoints,
                  BigDecimal minProjectsPoints,
                  BigDecimal minExamsPoints) {
        this.id = id;
        this.minPoints = minPoints;
        this.currentPoints = currentPoints;
        this.minLabsPoints = minLabsPoints;
        this.minMyTestsPoints = minMyTestsPoints;
        this.minProjectsPoints = minProjectsPoints;
        this.minExamsPoints = minExamsPoints;
    }

    public CourseId getId() {
        return id;
    }

    public void setId(CourseId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(BigDecimal minPoints) {
        this.minPoints = minPoints;
    }

    public BigDecimal getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(BigDecimal currentPoints) {
        this.currentPoints = currentPoints;
    }

    public BigDecimal getMinLabsPoints() {
        return minLabsPoints;
    }

    public void setMinLabsPoints(BigDecimal minLabsPoints) {
        this.minLabsPoints = minLabsPoints;
    }

    public BigDecimal getMinTestsPoints() {
        return minMyTestsPoints;
    }

    public void setMinTestsPoints(BigDecimal minTestsPoints) {
        this.minMyTestsPoints = minTestsPoints;
    }

    public BigDecimal getMinProjectsPoints() {
        return minProjectsPoints;
    }

    public void setMinProjectsPoints(BigDecimal minProjectsPoints) {
        this.minProjectsPoints = minProjectsPoints;
    }

    public BigDecimal getMinExamsPoints() {
        return minExamsPoints;
    }

    public void setMinExamsPoints(BigDecimal minExamsPoints) {
        this.minExamsPoints = minExamsPoints;
    }

    public List<MyTest> getMyTests() {
        return myTests;
    }

    public void setMyTests(List<MyTest> myTests) {
        this.myTests = myTests;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Lab> getLabs() {
        return labs;
    }

    public void setLabs(List<Lab> labs) {
        this.labs = labs;
    }

    public void addMyTest(MyTest myTest) {
        myTests.add(myTest);
        myTest.setCourse(this);
    }

    public void removeMyTest(MyTest myTest) {
        myTests.remove(myTest);
        myTest.setCourse(null);
    }

    public void addProject(Project project) {
        projects.add(project);
        project.setCourse(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.setCourse(null);
    }

    public void addLab(Lab lab) {
        labs.add(lab);
        lab.setCourse(this);
    }

    public void removeLab(Lab lab) {
        labs.remove(lab);
        lab.setCourse(null);
    }

    @Transient
    private String grade;

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
