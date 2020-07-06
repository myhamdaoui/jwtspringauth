package com.mhamdaoui.springNgJWT.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhamdaoui.springNgJWT.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>{

}
