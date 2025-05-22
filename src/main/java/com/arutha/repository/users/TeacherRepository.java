package com.arutha.repository.users;

import com.arutha.model.users.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Teacher Repository.
 */
public interface TeacherRepository extends JpaRepository<Teachers, Integer> {

    Teachers findByUserId(Integer userId);

}
