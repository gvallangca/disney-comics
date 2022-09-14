package com.disney.demo.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.disney.demo.dao.entity.Hero;
import com.disney.demo.dto.response.HeroResponse;
import com.disney.demo.dto.response.SeriesResponse;

import graphql.kickstart.tools.GraphQLResolver;

@Service
public class HeroResolver implements GraphQLResolver<HeroResponse> {

	private static final String ALL_TITLES = "ALL";

	public List<SeriesResponse> getSeriesList(HeroResponse heroResponse, List<String> seriesFilterTitles) {

		List<SeriesResponse> seriesList = new ArrayList<>();
		Hero heroes = heroResponse.getHero();

		heroes.getSeries().forEach(series -> {

			boolean hasTitleMatch = seriesFilterTitles.stream()
					.anyMatch(seriesTitle -> seriesTitle.equalsIgnoreCase(series.getTitle()));

			SeriesResponse seriesResponse = new SeriesResponse(series);

			if (seriesFilterTitles.contains(ALL_TITLES) || hasTitleMatch) {
				seriesList.add(seriesResponse);
			}

		});

		return seriesList;

	}

}
