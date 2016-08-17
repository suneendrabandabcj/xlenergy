package com.utility.registration.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.utility.registration.dao.CustomerDAO;
import com.utility.registration.model.Customer;

public class MyMessageListener implements MessageListener {
	@Override
	public void onMessage(Message m) {
		TextMessage message = (TextMessage) m;
		try {

			System.out.println(message.getText());
			String[] arr = message.getText().split(";");
			long customerID = Long.parseLong(arr[0]);
			long servicePlanId = Long.parseLong(arr[1]);
			System.out.println("customerId: " + customerID + " servicePlanId: " + servicePlanId);
			CustomerDAO.updateServicePlan(customerID, servicePlanId);

			/*
			 * System.out.println("---------Object received---------");
			 * ObjectMessage msg = (ObjectMessage) m; Customer customer =
			 * (Customer) msg.getObject(); System.out.println("customerId: " +
			 * customer.getCustomerId()); System.out.println("customerName: " +
			 * customer.getFirstName()); System.out.println("customerEmail: " +
			 * customer.getEmail());
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}