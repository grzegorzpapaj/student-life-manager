package com.gpaqd.student_life_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProjectDTO {

    private Integer projectNumber;
    private String description;
    private BigDecimal minPoints;
    private BigDecimal userPoints;
    private BigDecimal maxPoints;
    private LocalDate deadline;

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
