package test.com.h2rd.refactoring.integration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2rd.refactoring.usermanagement.controller.UserManagementController;
import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.repository.UserManagementDao;
import com.h2rd.refactoring.usermanagement.service.UserManagementService;


import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserIntegrationTest{

	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	UserManagementService userManagementService;

	UserManagementDao userManagementDao;
	
	UserManagementController userManagementController;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	User user = new User();

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.webAppContextSetup(this.wac)
				.build();


		user.setEmail("test@gmail.com");
		user.setName("Test");
		user.setRoles(Arrays.asList("superuser"));
	}


	@Test
	public void createUser() throws Exception {

		mockMvc.perform(
				post("/users")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user))
				)
		.andExpect(MockMvcResultMatchers.status().isCreated());


	}
	
	@Test
	public void getAllUsers() throws Exception {
		mockMvc.perform(
				get("/users")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user))
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	public void updateUser() throws Exception {
		mockMvc.perform(
				put("/users")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user)))
		.andExpect(MockMvcResultMatchers.status().isOk());

	}

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
	

}
