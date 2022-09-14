package com.disney.demo.dto.response;

import java.util.List;

import com.disney.demo.dao.entity.Creator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorResponse {

	private int id;

	private String name;

	// TODO: create a resolver
	private List<HeroResponse> heroes;

	/* Don't expose to query. Internal use only */
	private Creator creator;

	public CreatorResponse(Creator creator) {

		this.creator = creator;

		this.id = creator.getId();

		this.name = creator.getName();

	}

}
