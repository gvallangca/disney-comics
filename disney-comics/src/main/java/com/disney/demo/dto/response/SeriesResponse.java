package com.disney.demo.dto.response;

import com.disney.demo.dao.entity.Series;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeriesResponse {

	private String title;

	public SeriesResponse(Series series) {
		this.title = series.getTitle();
	}
	
}
