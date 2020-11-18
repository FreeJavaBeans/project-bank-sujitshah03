package com.revature.bank;

import java.util.ArrayList;

public class Bank {
     ArrayList<Customer> customers = new ArrayList<Customer>();
    		 
    		 
	 void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.add(customer);
	}


	Customer getCustomer(int account) {
		// TODO Auto-generated method stub
		return customers.get(account);
	}
	
	ArrayList<Customer> getCustomers(){
		return customers;
	}

}
