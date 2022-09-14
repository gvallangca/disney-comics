package com.disney.demo.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.disney.demo.dao.entity.Creator;
import com.disney.demo.dto.response.CreatorResponse;
import com.disney.demo.dto.response.HeroResponse;

import graphql.kickstart.tools.GraphQLResolver;

@Service
public class CreatorResolver implements GraphQLResolver<CreatorResponse> {

	private static final String ALL_HEROES = "ALL";

	public List<HeroResponse> getHeroes(CreatorResponse creatorResponse, List<String> heroFilterNames) {

		List<HeroResponse> heroesList = new ArrayList<>();
		Creator creator = creatorResponse.getCreator();

		creator.getHeroes().forEach(hero -> {
			HeroResponse heroResponse = new HeroResponse(hero);

			boolean hasHeroNameMatch = heroFilterNames.stream()
					.anyMatch(filterName -> filterName.equalsIgnoreCase(hero.getName()));

			if (heroFilterNames.contains(ALL_HEROES) || hasHeroNameMatch) {
				heroesList.add(heroResponse);
			}

		});

		return heroesList;

	}

}
