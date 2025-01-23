package com.gpaqd.student_life_manager.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "thresholds")
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "threshold_id")
    private Long thresholdId;

    @Column(name = "points_3", nullable = false)
    private BigDecimal points3;

    @Column(name = "points_3_5", nullable = false)
    private BigDecimal points3_5;

    @Column(name = "points_4", nullable = false)
    private BigDecimal points4;

    @Column(name = "points_4_5", nullable = false)
    private BigDecimal points4_5;

    @Column(name = "points_5", nullable = false)
    private BigDecimal points5;

    @OneToMany(mappedBy = "threshold", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public Threshold() {
    }

    public Threshold(BigDecimal points3,
                     BigDecimal points3_5,
                     BigDecimal points4,
                     BigDecimal points4_5,
                     BigDecimal points5) {
        this.points3 = points3;
        this.points3_5 = points3_5;
        this.points4 = points4;
        this.points4_5 = points4_5;
        this.points5 = points5;
    }

    public Long getThresholdId() {
        return thresholdId;
    }

    public void setThresholdId(Long thresholdId) {
        this.thresholdId = thresholdId;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setThreshold(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setThreshold(null);
    }
}
