package com.web.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.api.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long>{}
