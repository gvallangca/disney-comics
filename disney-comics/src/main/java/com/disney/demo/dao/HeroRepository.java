package com.disney.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disney.demo.dao.entity.Hero;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
