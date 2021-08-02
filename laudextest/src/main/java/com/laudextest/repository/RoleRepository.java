package com.laudextest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laudextest.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
