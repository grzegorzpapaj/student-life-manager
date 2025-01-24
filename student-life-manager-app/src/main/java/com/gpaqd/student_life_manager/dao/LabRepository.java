package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, LabId> {

}
