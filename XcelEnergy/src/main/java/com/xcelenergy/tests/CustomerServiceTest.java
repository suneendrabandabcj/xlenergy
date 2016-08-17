package com.xcelenergy.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.xcelenergy.registration.model.Customer;
import com.xcelenergy.registration.service.CustomerService;

public class CustomerServiceTest {
	
	private static CustomerService customerService;
	private static  Customer customer;
	
	@BeforeClass
	public static void setUp(){
		
		customerService = Mockito.mock(CustomerService.class);
		customer = new Customer();
		customer.setCustomerId(45);
		customer.setFirstName("dfg");
		customer.setLastName("fg");
		customer.setEmail("fd");
		customer.setPassword("fdfd");
		
		when(customerService.getCustomerDetailsById(45)).thenReturn(customer);
	
	}
	
	@Test
	public void testgetCustomerDetailsById(){
		
		Customer cust = customerService.getCustomerDetailsById(45);
		assertSame(customer, cust);
		
	}
	
}
