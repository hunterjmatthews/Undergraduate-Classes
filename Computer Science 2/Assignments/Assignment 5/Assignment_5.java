/*************************************************
 * Assignment: Assignment #5                     *
 * Author:     Hunter Matthews                   *
 * NetID:      hjm210004                         *
 * Course:     CS 2336.503 (Computer Science II) *
 * Instructor: Dr. Mohamed Belkoura              *
 * Date Due:   October 30th, 2022 at 11:59pm.    *
**************************************************/

import java.util.Scanner;

public class Assignment_5 {
	public static void main(String[] args) {
		// Create Scanner object 'usrInput'.
		Scanner usrInput = new Scanner(System.in);
		
		// Prompts the user to enter the 1st complex number.
		System.out.println("Enter 1st Complex Number: ");
		Complex c1 = new Complex(usrInput.nextDouble(), usrInput.nextDouble());
		
		// Prompts the user to enter the 2nd complex number.
		System.out.println("Enter 2nd Complex Number: ");
		Complex c2 = new Complex(usrInput.nextDouble(), usrInput.nextDouble());
		
		// Display the addition(), subtraction(), multiplication(), division(), and absoluteValue() methods.
		System.out.println(c1 + " + " + c2 + " = " + c1.addition(c2));
		System.out.println(c1 + " - " + c2 + " = " + c1.subtraction(c2));
		System.out.println(c1 + " * " + c2 + " = " + c1.multiplication(c2));
		System.out.println(c1 + " / " + c2 + " = " + c1.division(c2));
		System.out.println("|" + c1 + "| = " + c1.absoluteValue());

	}

}
