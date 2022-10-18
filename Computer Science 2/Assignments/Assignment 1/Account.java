import java.util.*;

public class Account {
	
	// Account id - default id is 0.
	private int id = 0;
	
	// Balance for account - default balance is 0.
	private double balance = 0;
	
	// Annual interest rate for the account - Default is 0%.
	private double annualInterestRate = 0;
	
	// Date when the account was created.
	private Date dateCreated;
	
	// Creates the default account
	public Account()
	{
		this.id = 0;
		this.balance = 0;
		this.annualInterestRate = 0;
		this.dateCreated = new Date();
	}
	
	// Constructor that creates an account with the specified id and initial balance.
	public Account(int id, double balance, double annualInterestRate)
	{
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
		this.dateCreated = new Date();
	}
	
	// Accessor - balance
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	// Mutator - balance
	public double getBalance() {
		return this.balance;
	}
	
	// Accessor - id
	public void setID(int id) {
		this.id = id;
	}
	
	// Mutator - id
	public int getID() {
		return this.id;
	}
	
	// Accessor - annualInterestRate
	public void getAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	
	// Mutator - annualInterestRate
	public double setAnnualInterestRate() {
		return this.annualInterestRate;
	}
	
	// Accessor - dateCreated
	public Date getDateCreated() {
		return this.dateCreated;
	}
	
	// Method - returns monthly interest rate.
	public double getMonthlyInterestRate() {
		return this.annualInterestRate / 12;
	}
	
	// Method - withdraws a specified amount from the account.
	public void withdraw(double amount) {
		// Ensure that customer has enough money to withdraw.
		if (this.balance < amount) {
			System.out.println("Error - You're Broke! You can't withdraw our money.");
		}
		else {
			this.balance -= amount;
			System.out.println("Withdrawing: " + amount);
			System.out.println("You now have: " + this.balance);
		}
	}
	
	// Method - deposits a specified amount in the account.
	public void deposit(double amount) {
		this.balance += amount;
		System.out.println("You are depositing:" + amount);
		System.out.println("You now have: " + this.balance);
	}
}
