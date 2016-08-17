package com.utility.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.utility.registration.model.Customer;
import com.utility.registration.model.ServicePlan;
import com.utility.registration.util.DBSessionManagement;
import com.utility.registration.util.DatabaseConnection;

public class CustomerDAO {

	/**
	 * Get customer Details By ID
	 * 
	 * @param customerId
	 * @return
	 */
	public static Customer getCustomerById(long customerId) {

		Session session = DBSessionManagement.getSessionFactory().openSession();
		Customer customer = (Customer) session.load(Customer.class, customerId);
		return customer;

	}

	/**
	 * @return Return List of all service plans
	 * @throws Exception
	 */
	public static List<ServicePlan> getAllServicePlans() throws Exception {

		Session session = DBSessionManagement.getSessionFactory().openSession();
		List<ServicePlan> servicePlans = new ArrayList<ServicePlan>();
		try {

			servicePlans = session.createCriteria(ServicePlan.class).list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}

		return servicePlans;

	}

	/**
	 * Update customer table service plan column
	 * 
	 * @param customerId
	 * @param servicePlanId
	 */
	public static void updateServicePlan(long customerId, long servicePlanId) {

		// Session session =
		// DBSessionManagement.getSessionFactory().openSession();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String qry = "UPDATE customer set planId = ? where customerId = ? ";
		try {
			dbConnection = new DatabaseConnection().setConnection();
			preparedStatement = dbConnection.prepareStatement(qry);

			preparedStatement.setLong(1, servicePlanId);
			preparedStatement.setLong(2, customerId);

			// execute update SQL stetement
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//session.close();
		}

	}
}
