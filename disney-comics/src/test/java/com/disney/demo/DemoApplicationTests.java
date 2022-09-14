package com.disney.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.disney.demo.controller.query.DisneyQueryController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {DemoApplication.class, DisneyQueryController.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	void happyPath() throws Exception {

		String expectedRespose = "{\n"
				+ "  \"data\": {\n"
				+ "    \"creator\": {\n"
				+ "      \"id\": 1,\n"
				+ "      \"name\": \"Jason Aaron\",\n"
				+ "      \"heroes\": [\n"
				+ "        {\n"
				+ "          \"id\": 1,\n"
				+ "          \"name\": \"Thor\",\n"
				+ "          \"seriesList\": [\n"
				+ "            {\n"
				+ "              \"title\": \"The God of Butcher\"\n"
				+ "            },\n"
				+ "            {\n"
				+ "              \"title\": \"The Devour King\"\n"
				+ "            },\n"
				+ "            {\n"
				+ "              \"title\": \"Prey\"\n"
				+ "            }\n"
				+ "          ]\n"
				+ "        }\n"
				+ "      ]\n"
				+ "    }\n"
				+ "  }\n"
				+ "}";
		
		String query = "query {\n"
				+ "  creator(id: 1) {\n"
				+ "    id\n"
				+ "    name\n"
				+ "    heroes(heroFilterNames: [\"thor\"]) {\n"
				+ "      id\n"
				+ "      name\n"
				+ "      seriesList(seriesFilterTitles: [\"ALL\"]) {\n"
				+ "        title\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}";
		
		String jwt = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJsaW5kYSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJyZWFkIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJ3cml0ZSJ9XSwiaWF0IjoxNjYzMTMxNDk3LCJleHAiOjE2NjQzMzc2MDB9.-34pkbi1CL3ptfVXp_HF8_gws9W-KIDw9avHXcMWwETO2S_wy49_PDtla7PxzDEa";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/comics")
				.content(query)
				.contentType("application/graphql")
				.header("Authorization", jwt)
				.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(expectedRespose))
					.andReturn();
		
	}

}
