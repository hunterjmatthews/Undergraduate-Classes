/*
    Assignment: Assignment #1
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   September 14th, 2022 at 11:59pm.
*/

public class BankOperations {
	public static void main(String args[]) {
		// Create a test account.
		Account test = new Account(1122, 20000, 4.5);
		
		// Withdraw a test amount.
		test.withdraw(2500);
		
		// Deposit a test amount.
		test.deposit(3000);
		
		// Display the test account w/ information.
		System.out.println("Account: " + test.getID());
		System.out.println("Relationship Established: " + test.getDateCreated());
		System.out.println("Balance: " + test.getBalance());
		System.out.println("Your Monthly Intrest Rate: " + test.getMonthlyInterestRate() + " which generates for you " + (test.getMonthlyInterestRate() * test.getBalance()) / 100 + " per month");
	}
}
