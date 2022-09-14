package com.disney.demo.dto.response;

import java.util.List;

import com.disney.demo.dao.entity.Hero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HeroResponse {
	
	private int id;

	private String name;
	
	// TODO: create resolver
	private List<SeriesResponse> seriesList;
	
	private Hero hero;
	
	public HeroResponse(Hero hero) {
		
		this.hero = hero;
		
		this.id = hero.getId();
		
		this.name = hero.getName();
		
	}
	
}
