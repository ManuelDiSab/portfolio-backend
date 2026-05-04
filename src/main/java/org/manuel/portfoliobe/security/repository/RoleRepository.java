package org.manuel.portfoliobe.security.repository;

import org.manuel.portfoliobe.security.entity.ERole;
import org.manuel.portfoliobe.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
