package com.gpaqd.student_life_manager.entity.pk;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LabId implements Serializable {

    private String courseName;
    private String ownedByUser;
    private Integer labNumber;

    public LabId() {
    }

    public LabId(String courseName, String ownedByUser, Integer labNumber) {
        this.courseName = courseName;
        this.ownedByUser = ownedByUser;
        this.labNumber = labNumber;
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

    public Integer getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(Integer labNumber) {
        this.labNumber = labNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabId)) return false;
        LabId labId = (LabId) o;
        return Objects.equals(courseName, labId.courseName) &&
                Objects.equals(ownedByUser, labId.ownedByUser) &&
                Objects.equals(labNumber, labId.labNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, ownedByUser, labNumber);
    }
}
