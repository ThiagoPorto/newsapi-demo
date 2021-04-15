package com.handpicked.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handpicked.demo.domain.Category;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryControllerTest {

	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }
	
	@WithMockUser(username="any")
	@Test
	public void testGetAllCategories() throws Exception {
		MvcResult result = mockMvc.perform(get("/categories")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		List<Category> response = objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, Category.class));
		assertThat(response.size()).isEqualTo(7);
	}
}
