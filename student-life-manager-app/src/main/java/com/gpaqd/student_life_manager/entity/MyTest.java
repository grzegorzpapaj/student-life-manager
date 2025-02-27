package com.gpaqd.student_life_manager.entity;

import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tests")
public class MyTest {

    @EmbeddedId
    private MyTestId id;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "course_name", referencedColumnName = "course_name",
                    insertable = false, updatable = false),
            @JoinColumn(name = "owned_by_user", referencedColumnName = "owned_by_user",
                    insertable = false, updatable = false)
    })
    private Course course;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "min_points", nullable = false)
    private BigDecimal minPoints;

    @Column(name = "user_points")
    private BigDecimal userPoints;

    @Column(name = "max_points", nullable = false)
    private BigDecimal maxPoints;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "is_exam", nullable = false)
    private boolean exam;

    public MyTest() {
    }

    public MyTest(MyTestId id,
                  String description,
                  BigDecimal minPoints,
                  BigDecimal userPoints,
                  BigDecimal maxPoints,
                  LocalDate date,
                  boolean exam) {
        this.id = id;
        this.description = description;
        this.minPoints = minPoints;
        this.userPoints = userPoints;
        this.maxPoints = maxPoints;
        this.date = date;
        this.exam = exam;
    }

    public MyTestId getId() {
        return id;
    }

    public void setId(MyTestId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(BigDecimal minPoints) {
        this.minPoints = minPoints;
    }

    public BigDecimal getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(BigDecimal userPoints) {
        this.userPoints = userPoints;
    }

    public BigDecimal getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(BigDecimal maxPoints) {
        this.maxPoints = maxPoints;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isExam() {
        return exam;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }
}
