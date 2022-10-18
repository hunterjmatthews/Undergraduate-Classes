/*
    Assignment: Assignment #3
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   October 3rd, 2022 at 11:59pm.
*/

import java.util.Scanner;

public class triangleTest {

	public static void main(String[] args) {
		// Create the Scanner object.
		Scanner myObj = new Scanner(System.in);
		
		// Prompts the user to enter double values for the three sides of the triangle.
		System.out.println("Enter three sides of the triangle (i.e. 5,4,3): ");
		double side1 = myObj.nextDouble(); 
		double side2 = myObj.nextDouble(); 
		double side3 = myObj.nextDouble();
		
		// Prompts the user to enter a color for the triangle.
		System.out.println("Enter a color for the triangle: ");
		String color = myObj.next();
		
		// Prompt the user to enter a boolean value to indicate if the triangle is filled or not filled.
		System.out.println("Do you want the triangle to be filled (i.e. true or false)? ");
		boolean fill = myObj.nextBoolean();
		
		// Creates the triangle with input from the user.
		Triangle triangle = new Triangle(side1, side2, side3);
		triangle.setColor(color);
		triangle.setFilled(fill);
		
		// Display the area, perimeter, color, and true or false to indicate the fill.
		System.out.println(triangle.toString());
		System.out.println("Area of the triangle: " + triangle.getArea());
		System.out.println("Perimeter of the triangle: " + triangle.getPerimeter());
		System.out.println("Color: " + triangle.getColor());
		System.out.println("The triangle is " + (triangle.isFilled() ? "" : "not ") + "filled");
	}
}
