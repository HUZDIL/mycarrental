package com.mycarrental.repository;

import com.mycarrental.domain.Role;
import com.mycarrental.domain.enumaration.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository <Role , Long>{


    Optional<Role> findByName(UserRole name);


}
