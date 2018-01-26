package com.h2rd.refactoring.usermanagement.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2rd.refactoring.usermanagement.model.User;
import com.h2rd.refactoring.usermanagement.service.UserManagementService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagementControllerTest {
	MockMvc mockMvc;
	@Mock
	private UserManagementService userManagementService;
	@InjectMocks
	private UserManagementController userManagementController;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	User user = new User();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(userManagementController)
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

	@Test
	public void deleteUserByEmail() throws Exception {
		mockMvc.perform(
				delete("/users").param("email", user.getEmail())
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user))
				)
		.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/*	@Test
	public void getUser() throws Exception {
		mockMvc.perform(
				get("/users/user").param("email", user.getEmail())
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user))
				).andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk());

	}*/


	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}