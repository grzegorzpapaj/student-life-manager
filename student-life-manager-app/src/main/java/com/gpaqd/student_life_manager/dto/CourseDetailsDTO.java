package com.gpaqd.student_life_manager.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailsDTO {

    // Course
    private String courseName;
    private BigDecimal minPoints;
    private BigDecimal currentPoints;
    private BigDecimal minLabsPoints;
    private BigDecimal minTestsPoints;
    private BigDecimal minProjectsPoints;
    private BigDecimal minExamsPoints;

    // Threshold

    private BigDecimal points3;
    private BigDecimal points3_5;
    private BigDecimal points4;
    private BigDecimal points4_5;
    private BigDecimal points5;

    // Labs

    private List<LabDTO> labs = new ArrayList<>();

    // Tests

    private List<MyTestDTO> myTests = new ArrayList<>();

    // Projects

    private List<ProjectDTO> projects = new ArrayList<>();

    // Getters and setters

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
        return minTestsPoints;
    }

    public void setMinTestsPoints(BigDecimal minTestsPoints) {
        this.minTestsPoints = minTestsPoints;
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

    public BigDecimal getPoints3() {
        return points3;
    }

    public void setPoints3(BigDecimal points3) {
        this.points3 = points3;
    }

    public BigDecimal getPoints3_5() {
        return points3_5;
    }

    public void setPoints3_5(BigDecimal points3_5) {
        this.points3_5 = points3_5;
    }

    public BigDecimal getPoints4() {
        return points4;
    }

    public void setPoints4(BigDecimal points4) {
        this.points4 = points4;
    }

    public BigDecimal getPoints4_5() {
        return points4_5;
    }

    public void setPoints4_5(BigDecimal points4_5) {
        this.points4_5 = points4_5;
    }

    public BigDecimal getPoints5() {
        return points5;
    }

    public void setPoints5(BigDecimal points5) {
        this.points5 = points5;
    }

    public List<LabDTO> getLabs() {
        return labs;
    }

    public void setLabs(List<LabDTO> labs) {
        this.labs = labs;
    }

    public List<MyTestDTO> getMyTests() {
        return myTests;
    }

    public void setMyTests(List<MyTestDTO> myTests) {
        this.myTests = myTests;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}
