package com.gpaqd.student_life_manager.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MyTestId implements Serializable {

    @Column(name="course_name")
    private String courseName;

    @Column(name="owned_by_user")
    private String ownedByUser;

    @Column(name="test_number")
    private Integer myTestNumber;

    public MyTestId() {
    }

    public MyTestId(String courseName, String ownedByUser, Integer myTestNumber) {
        this.courseName = courseName;
        this.ownedByUser = ownedByUser;
        this.myTestNumber = myTestNumber;
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

    public Integer getMyTestNumber() {
        return myTestNumber;
    }

    public void setMyTestNumber(Integer testNumber) {
        this.myTestNumber = testNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestId)) return false;
        MyTestId myTestId = (MyTestId) o;
        return Objects.equals(courseName, myTestId.courseName) &&
                Objects.equals(ownedByUser, myTestId.ownedByUser) &&
                Objects.equals(myTestNumber, myTestId.myTestNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, ownedByUser, myTestNumber);
    }
}
