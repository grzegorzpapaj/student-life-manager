package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
