package com.disney.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.disney.demo.dao.CreatorRepository;
import com.disney.demo.dao.entity.Creator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreatorServiceImpl implements CreatorService {

	private CreatorRepository creatorRepository;

	@Override
	public Creator viewCreator(int id) {

		Optional<Creator> creatorDb = creatorRepository.findById(id);
		return creatorDb.orElseThrow(() -> new IllegalStateException(String.format("Unable to find creator %s", id)));

	}

	@Override
	public List<Creator> allCreators() {
		// TODO Auto-generated method stub
		return null;
	}

}
