package com.success.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.success.entities.Codes;
import com.success.entities.CodesPK;

@Repository
public interface CodesRepository extends JpaRepository<Codes, CodesPK> {}
