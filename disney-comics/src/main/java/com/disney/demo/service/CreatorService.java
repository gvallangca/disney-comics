package com.disney.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.disney.demo.dao.entity.Creator;

@Service
public interface CreatorService {

	Creator viewCreator(int id);
	
	List<Creator> allCreators();
	
}
