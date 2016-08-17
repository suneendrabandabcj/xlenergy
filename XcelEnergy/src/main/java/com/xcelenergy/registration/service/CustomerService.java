package com.xcelenergy.registration.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.xcelenergy.registration.dao.CustomerDAO;
import com.xcelenergy.registration.jms.MyMessageSender;
import com.xcelenergy.registration.model.Customer;
import com.xcelenergy.registration.wsdl.serviceplan.ServicePlan;

/**
 * provide methods for customer registration and to register for service plan.
 * 
 * @author Bootcamp User 000
 *
 */
public class CustomerService {

	final static Logger logger = Logger.getLogger(CustomerService.class);
	
	/**
	 * register customer
	 * 
	 * @param customer
	 * @throws Exception
	 */
	public String registerCustomer(Customer customer) throws Exception {

		long customerId = CustomerDAO.saveCustomer(customer);
		
		// Display Customer details
		Customer customerDetails = getCustomerDetailsById(customerId);
		System.out.println("*****************Customer Details******************");
		System.out.println("CustomerID: " + customerDetails.getCustomerId());
		System.out.println("CustomerName: " + customerDetails.getFirstName() + ", " + customerDetails.getLastName());
		System.out.println("CustomerEmail: " + customerDetails.getEmail());
		
		// Display Service plans
		List<ServicePlan> plans = PowerService.getServicePlans();
		long serviceId = plans.get(0).getPlanId();

		// send customerId and service plan Id as string with delimiter ;
		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/beans/applicationContext.xml");
		MyMessageSender sender = (MyMessageSender) appContext.getBean("messageSender");
		sender.sendMessage(customerId + ";"+serviceId);
		// sender.sendMessage(customer);
		System.out.println("Message was send....***************....");
		logger.info("Message was send vis JMS.");
		
		Customer customerServicePlan = CustomerDAO.getCustomerInformationById(customerId);
		
		String msg = customerServicePlan.getFirstName()+", "+customerServicePlan.getLastName()+" your service Id "+ serviceId;
		
		return msg;
	}

	/**
	 * Get Customer details by ID by using REST call
	 * 
	 * @param customerId
	 */
	public Customer getCustomerDetailsById(long customerId) {

		Client client = Client.create();
		Customer customerDetails;
		WebResource webResource = client
				.resource("http://localhost:8080/XcelCustomerWS/rest/CustomerService/getCustomer/" + customerId);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		} else {
			customerDetails = response.getEntity(Customer.class);
		}
		return customerDetails;
	}
}
