package com.gpaqd.student_life_manager.entity.pk;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TestId implements Serializable {

    private String courseName;
    private String ownedByUser;
    private Integer testNumber;

    public TestId() {
    }

    public TestId(String courseName, String ownedByUser, Integer testNumber) {
        this.courseName = courseName;
        this.ownedByUser = ownedByUser;
        this.testNumber = testNumber;
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

    public Integer getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestId)) return false;
        TestId testId = (TestId) o;
        return Objects.equals(courseName, testId.courseName) &&
                Objects.equals(ownedByUser, testId.ownedByUser) &&
                Objects.equals(testNumber, testId.testNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, ownedByUser, testNumber);
    }
}
