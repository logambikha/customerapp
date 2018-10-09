package com.capgemini.customer;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.controller.CustomerController;
import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest

public class CustomerControllerTest {

	
	@Mock
	private CustomerService customerService;
	
	
	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	

	@Test
	public void testAddCustomer() throws Exception {
		when(customerService.addCustomer(Mockito.isA(Customer.class))).thenReturn(new Customer(1234, "tejasree", "teja", "teja@gmail.com", "vizag" ));
		mockMvc.perform(post("/customer").
				contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" + 
						"	\"customerId\":1234,\n" + 
						"	\"customerName\":\"tejasree\",\n" + 
						"	\"customerPassword\":\"teja\",\n" + 
						"	\"customerEmail\":\"teja@gmail.com\",\n" + 
						"	\"customerAddress\":\"vizag\"\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	

	@Test
	public void testUpdateCustomer() throws Exception {
		Customer customer = new Customer(1234, "tejasree", "teja", "teja@gmail.com", "vizag");
		when(customerService.updateCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		when(customerService.findCustomerById(1234)).thenReturn(customer);
		mockMvc.perform(put("/customer").
				contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{ \"customerId\": 1234,\r\n" + 
						"    \"customerName\": \"anantha\",\r\n" + 
						"    \"customerPassword\": \"teja\",\r\n" + 
						"    \"customerEmail\": \"teja@gmail.com\",\r\n" + 
						"    \"customerAddress\": \"vizag\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andDo(print());
	}
	@Test
	public void testFindCustomerById() throws Exception {
		Customer customer = new Customer(1234, "tejasree", "teja", "teja@gmail.com", "vizag");
		when(customerService.findCustomerById(1234)).thenReturn(customer);
		mockMvc.perform(put("/customer/1234").
				accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.customerId").exists())
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testDelete() throws Exception {
		Customer customer = new Customer(1234, "tejasree", "teja", "teja@gmail.com", "vizag");
		when(customerService.findCustomerById(1234)).thenReturn(customer);
		mockMvc.perform(delete("/customer/1234").
				accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testAuthenticateWhichreturnsCustomer() throws Exception {
		Customer customer = new Customer(123, null, "12", null, null);
		Customer customer1 = new Customer(123, "ABC", "12", "XYZ", "m@m");
		when(customerService.authenticate(Mockito.isA(Customer.class))).thenReturn(customer1);
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\r\n" + "    \"customerId\": 1,\r\n" + "    \"customerPassword\": \"12\"\r\n" + "}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk())
		.andDo(print());
	}
}