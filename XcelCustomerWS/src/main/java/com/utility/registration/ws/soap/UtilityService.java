package com.utility.registration.ws.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import com.utility.registration.model.ServicePlan;

@WebService
public interface UtilityService {

	@WebMethod 
	public List<ServicePlan> getAllServicePlans() throws Exception;
}
