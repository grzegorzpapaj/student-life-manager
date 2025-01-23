package com.gpaqd.student_life_manager.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseId implements Serializable {

    @Column(name="course_name")
    private String courseName;

    @Column(name="owned_by_user")
    private String ownedByUser;

    public CourseId() {
    }

    public CourseId(String courseName, String ownedByUser) {
        this.courseName = courseName;
        this.ownedByUser = ownedByUser;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseId)) return false;
        CourseId courseId = (CourseId) o;
        return Objects.equals(courseName, courseId.courseName) &&
                Objects.equals(ownedByUser, courseId.ownedByUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, ownedByUser);
    }
}
