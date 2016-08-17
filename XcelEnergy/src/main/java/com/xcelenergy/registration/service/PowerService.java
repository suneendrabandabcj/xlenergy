package com.xcelenergy.registration.service;

import java.util.ArrayList;
import java.util.List;

import com.xcelenergy.registration.wsdl.serviceplan.Exception_Exception;
import com.xcelenergy.registration.wsdl.serviceplan.ServicePlan;
import com.xcelenergy.registration.wsdl.serviceplan.UtilityService;
import com.xcelenergy.registration.wsdl.serviceplan.UtilityService_Service;

public class PowerService {

	/**
	 * Makes SOAP call and to get list of service plans
	 */
	public static List<ServicePlan> getServicePlans(){
		
		UtilityService_Service service = new UtilityService_Service();
		UtilityService ser = service.getUtilityServiceImplPort();
		List<ServicePlan> plans = new ArrayList<ServicePlan>();
		try {
			plans = ser.getAllServicePlans();
			for (ServicePlan servicePlan : plans) {
				System.out.print("\rplan Id: "+servicePlan.getPlanId());
				System.out.print("\tplan Details: "+servicePlan.getPlanDetails());
				System.out.print("\tCancellation Fee: "+servicePlan.getCancellationFee());
			}
			
		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plans;
	}
}
