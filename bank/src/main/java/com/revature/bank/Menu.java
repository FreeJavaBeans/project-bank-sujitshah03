package com.revature.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
// instance variables
	Scanner Keyboard = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;
	
	
	public static void main(String[]args) {
		Menu menu = new Menu();
		menu.runMenu();
	}
	
	public void runMenu () {
		  printHeader();
		  while(!exit) {
			  printMenu();
			  int choice = getInput();
			  performAction(choice);
			  
			  
		  }
	 }
	
	private void printHeader() {
	
		System.out.println("+-------------------------------+");
		System.out.println("|    Welcome to Mr. $$ujit      |");
		System.out.println("|         Money Bank            |");
		System.out.println("+-------------------------------+");
	}
	
	private void printMenu() {
		
		System.out.println("Please make a selection");
		System.out.println("1) Create a new account");
		System.out.println("2) Make a deposit");
		System.out.println("3) Make a withdrawl");
		System.out.println("4) List account balance");
		System.out.println("0) Exit");
	}
	
	private int getInput() {
	
		int choice = -1;
		do {
			System.out.println("Enter your choice:");
		  try {
			choice = Integer.parseInt(Keyboard.nextLine());
		  }
		  catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println("Invalid Selection. Numbers only please");
		  }
		  if (choice < 0 || choice > 4) {
			System.out.println("choice outside of range. Please choose again");
		  }
		}while(choice < 0 || choice > 4);
		return choice;
	
	}
	
	private void performAction(int choice) {
		
		switch (choice) {
		case 0:
			System.out.println("Thank you for using our application");
			System.exit(0);
			break;
		case 1:
			 createAnAccount();
			break;
		case 2:
			makeADeposit();
			break;
		case 3:
			makeAWithdrawl();
			break;
		case 4:
			listBalance();
			break;

		default:
			System.out.println("Unknown error has occured");
		
		}
	
		
	}

	private void createAnAccount() {
		String firstName, lastName, ssn, accountType = "";
		double initialDeposit = 0;
		boolean valid = false;
		while(!valid) {
			System.out.println("Please enter an account type (checking/savings): ");
			accountType = Keyboard.nextLine();
			if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
				valid = true;
			}
			else {
				System.out.println("Invalid account type selected. Please enter checking or savings.");
			}
		}
		System.out.println("Please enter your first name: ");
		firstName = Keyboard.nextLine();
		System.out.println("Please enter your last name: ");
		lastName = Keyboard.nextLine();
		System.out.println("Please enter your social security number");
		ssn = Keyboard.nextLine();
		valid = false;
		while(!valid) {
			System.out.println("Please enter an initial deposit");
			try {
				initialDeposit = Double.parseDouble(Keyboard.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Deposit must be a number.");
			}
			if(accountType.equalsIgnoreCase("checking")) {
				if (initialDeposit < 100) {
					System.out.println("Checking accounts require a minimum of $100 dollars to open.");
				} else {
					valid = true;
				}
			} else if(accountType.equalsIgnoreCase("savings")) {
				if (initialDeposit < 50) {
					System.out.println("Savings accounts require a minimum of $50 dollars to open.");
				} else {
					valid = true;
				}
			}	
		}
		//we can create an account now
		Account account;
		if (accountType.equalsIgnoreCase("checking")) {
			account = new Checking (initialDeposit);
		} else {
			account = new Savings (initialDeposit);
		}
		Customer customer = new Customer (firstName, lastName, ssn, account);
		bank.addCustomer(customer);
	}

	private void makeADeposit() {
		int account = selectAccount();
		if ( account >= 0) {
		System.out.println("How much would you like to deposit?: ");
		double amount = 0;
		try {
			amount = Double.parseDouble(Keyboard.nextLine());
		}
		catch (NumberFormatException e) {
			amount = 0;
		}
		
		bank.getCustomer(account).getAccount().deposit(amount);
		}
	}

	private void makeAWithdrawl() {
		int account = selectAccount();
		if ( account >= 0) {
		System.out.println("How much would you like to withdraw?: ");
		double amount = 0;
		try {
			amount = Double.parseDouble(Keyboard.nextLine());
		}
		catch (NumberFormatException e) {
			amount = 0;
		}
		
		bank.getCustomer(account).getAccount().withdraw(amount);
		}
		
	}
	
	private void listBalance() {
		int account = selectAccount();
		if ( account >= 0) {
		
		System.out.println(bank.getCustomer(account).getAccount());
		} else {
			System.out.println("Invalid account selection.");
		}
		
		
	}
	private int selectAccount() {
		// TODO Auto-generated method stub
		ArrayList<Customer> customers = bank.getCustomers();
		if(customers.size() <=0) {
			System.out.println("No Account Found!! Create a new account");
			return -1;
		}
		System.out.println("Select an account:");
		for (int i = 0; i < customers.size(); i++ ) {
			System.out.println((i+1) + ") " + customers.get(i).basicInfo());
		}
		int account = 0;
		System.out.println("Please enter your selection: ");
		try {
			account = Integer.parseInt(Keyboard.nextLine()) - 1;
		}
		catch (NumberFormatException e) {
			account = -1;
		}
		if ( account < 0 || account > customers.size()) {
			System.out.println("Invalid account selected.");
			account = -1;
		}
		return account;
	}
}
	
	
	
	
	

