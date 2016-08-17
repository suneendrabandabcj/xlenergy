package com.utility.registration.ws.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.utility.registration.dao.CustomerDAO;
import com.utility.registration.model.Customer;

@ApplicationPath("CustomerService")
@Path("/CustomerService")
public class CustomerService {

	/**
	 * Return Customer for given ID
	 * 
	 * @param customerId
	 * @return
	 */
	@GET
	@Path("/getCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerById(@PathParam("customerId") long customerId) {
		
		Customer customer = CustomerDAO.getCustomerById(customerId);
		
		return Response.status(201).entity(customer).build();
	}

}
