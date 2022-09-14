package com.disney.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disney.demo.dao.entity.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {

}
