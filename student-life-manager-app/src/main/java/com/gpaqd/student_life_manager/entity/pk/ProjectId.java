package com.gpaqd.student_life_manager.entity.pk;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectId implements Serializable {

    private String courseName;
    private String ownedByUser;
    private Integer projectNumber;

    public ProjectId() {
    }

    public ProjectId(String courseName, String ownedByUser, Integer projectNumber) {
        this.courseName = courseName;
        this.ownedByUser = ownedByUser;
        this.projectNumber = projectNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOwnedByUser() {
        return ownedByUser;
    }

    public void setOwnedByUser(String ownedByUser) {
        this.ownedByUser = ownedByUser;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectId)) return false;
        ProjectId that = (ProjectId) o;
        return Objects.equals(courseName, that.courseName) &&
                Objects.equals(ownedByUser, that.ownedByUser) &&
                Objects.equals(projectNumber, that.projectNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, ownedByUser, projectNumber);
    }
}
