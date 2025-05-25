package com.arutha.repository.users;

import com.arutha.model.users.Child;
import com.arutha.projection.childdetailsprojection.ChildDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Child Repository.
 */
public interface ChildRepository extends JpaRepository<Child, Integer> {

    @Query(
            value = "SELECT CONCAT(users.first_name, ' ', users.last_name) AS studentName, " +
                    "       users.id AS userId, " +
                    "       child.id AS studentId " +
                    "FROM users " +
                    "INNER JOIN child ON users.id = child.child_id " +
            "WHERE child.teacher_id = :teacherId " +
            "ORDER BY studentName", nativeQuery = true)
    List<ChildDetailsProjection> getChildDetailsByTeacherId(@Param("teacherId") Integer teacherId);

}
