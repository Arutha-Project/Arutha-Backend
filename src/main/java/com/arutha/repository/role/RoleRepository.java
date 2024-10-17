package com.arutha.repository.role;

import com.arutha.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Role Repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
