package com.utility.registration.ws.soap;

import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.utility.registration.dao.CustomerDAO;
import com.utility.registration.model.ServicePlan;

@WebService(endpointInterface = "com.utility.registration.ws.soap.UtilityService",
serviceName="UtilityService")
@Service
public class UtilityServiceImpl implements UtilityService {

	public List<ServicePlan> getAllServicePlans() throws Exception{
		return CustomerDAO.getAllServicePlans();
	}

}
