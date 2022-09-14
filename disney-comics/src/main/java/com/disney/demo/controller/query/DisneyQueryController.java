package com.disney.demo.controller.query;

import org.springframework.stereotype.Controller;

import com.disney.demo.dto.response.CreatorResponse;
import com.disney.demo.service.CreatorService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DisneyQueryController implements GraphQLQueryResolver {

	private CreatorService creatorService;

	public CreatorResponse creator(int id) {
		return new CreatorResponse(creatorService.viewCreator(id));
	}

}
