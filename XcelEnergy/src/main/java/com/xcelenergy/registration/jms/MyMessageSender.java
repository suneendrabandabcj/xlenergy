package com.xcelenergy.registration.jms;

import javax.jms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.xcelenergy.registration.model.Customer;

@Component("messageSender")
public class MyMessageSender {
	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String message) {
		jmsTemplate.send(new MessageCreator() {

			  public Message createMessage(Session session) throws JMSException {  
		            return session.createTextMessage(message);  
		        } 
			/*public ObjectMessage createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage();
				message.setObject(customer);
				return message;
			}*/
		});
	}
}