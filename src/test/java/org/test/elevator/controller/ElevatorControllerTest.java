package org.test.elevator.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.web.util.NestedServletException;
import org.test.elevator.dto.Status;
import org.test.elevator.service.ElevatorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ElevatorController.class, secure = false)
public class ElevatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ElevatorService elevatorService;

	@Test
	public void testRequestLiftUp() throws Exception {
		Mockito.when(
				elevatorService.sendStatus(Mockito.anyString())).thenReturn(new Status());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/elevator/1/5").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"status\":\"OK\",\"message\":\"Request received\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void testRequestLiftDown() throws Exception {
		Mockito.when(
				elevatorService.sendStatus(Mockito.anyString())).thenReturn(new Status());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/elevator/1/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"status\":\"OK\",\"message\":\"Request received\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void testRequestLiftFloorFive() throws Exception {
		Mockito.when(
				elevatorService.sendStatus(Mockito.anyString())).thenReturn(new Status());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/elevator/1/5").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"status\":\"OK\",\"message\":\"Request received\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test(expected = NestedServletException.class)
	public void testRequestLiftFloorEight() throws Exception {
		Mockito.when(
				elevatorService.sendStatus(Mockito.anyString())).thenReturn(new Status());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/elevator/1/8").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"status\":\"OK\",\"message\":\"Request received\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void testStopElevator() throws Exception {
		Mockito.when(
				elevatorService.sendStatus(Mockito.anyString())).thenReturn(new Status());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/elevator/stop/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"status\":\"OK\",\"message\":\"Request received\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test(expected = NestedServletException.class)
	public void testStopElevatorException() throws Exception {
		Mockito.when(
				elevatorService.sendStatus(Mockito.anyString())).thenReturn(new Status());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/elevator/stop/14").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();


	}



}
