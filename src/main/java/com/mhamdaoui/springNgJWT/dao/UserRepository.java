package com.mhamdaoui.springNgJWT.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import com.mhamdaoui.springNgJWT.model.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

}
