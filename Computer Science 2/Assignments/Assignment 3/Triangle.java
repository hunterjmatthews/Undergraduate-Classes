/*
    Assignment: Assignment #3
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   October 3rd, 2022 at 11:59pm.
*/

public class Triangle extends GeometricObject {
	/* Three double data fields named side1,side2,side3 with default values '1.0' 
	 * representing three sides of a triangle.
	*/ 
	private double side1 = 1.0, side2 = 1.0, side3 = 1.0;
	
	/**
	 * Constructor 1: Default Constructor that creates a default triangle.
	 */
	public Triangle(){}
	
	/**
	 * Constructor 2: Creates a triangle with the specified side1,side2, and side3
	 */
	public Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	public double setSide1() {return side1;}
	public double setSide2() {return side2;}
	public double setSide3() {return side3;}
	public void setSide1(double side1) {this.side1 = side1;}
	public void setSide2(double side2) {this.side2 = side2;}
	
	/**
	 * Method 1: returns the area of the triangle.
	 */
	public double getArea() {return (side1+side2+side3)/2;}
	
	/**
	 * Method 2: returns the perimeter of the triangle.
	 */
	public double getPerimeter() {return (side1+side2+side3);}
	
	/**
	 * Method 3: returns a string description for the triangle.
	 */
	public String toString() {return "Side 1 = " + side1 + " Side 2 = " + side2 + " Side 3 = " + side3;}
}
