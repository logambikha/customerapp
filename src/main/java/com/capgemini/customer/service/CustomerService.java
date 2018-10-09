package com.capgemini.customer.service;

import java.util.List;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exceptions.AuthenticationFailedException;
import com.capgemini.customer.exceptions.CustomerNotFoundException;

public interface CustomerService {

	public Customer authenticate(Customer customer) throws AuthenticationFailedException,CustomerNotFoundException;
	
	//public Customer updatePassword(Customer customer);
	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	
	public Customer findCustomerById(int customerId)throws CustomerNotFoundException;
	
	public void deleteCustomer(Customer customer)throws CustomerNotFoundException;

	public List<Customer> getAllCustomers();
	
}
