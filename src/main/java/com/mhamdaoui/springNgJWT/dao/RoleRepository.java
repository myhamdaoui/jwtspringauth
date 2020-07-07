package com.mhamdaoui.springNgJWT.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mhamdaoui.springNgJWT.model.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByName(String roleName);
}
