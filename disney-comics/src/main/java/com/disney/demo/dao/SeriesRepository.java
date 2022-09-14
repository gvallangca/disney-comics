package com.disney.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disney.demo.dao.entity.Series;

public interface SeriesRepository extends JpaRepository<Series, Integer> {

}
