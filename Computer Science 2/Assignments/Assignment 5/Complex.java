/*************************************************
 * Assignment: Assignment #5                     *
 * Author:     Hunter Matthews                   *
 * NetID:      hjm210004                         *
 * Course:     CS 2336.503 (Computer Science II) *
 * Instructor: Dr. Mohamed Belkoura              *
 * Date Due:   October 30th, 2022 at 11:59pm.    *
**************************************************/

/*********************************************************************************
* (Math: The Complex class) A complex number is a number in the form a + bi,     *
* where a and b are real numbers and i is 2-1. The numbers a and b are known as  *
* the real part and imaginary part of the complex number, respectively. You can  *
* perform addition, subtraction, multiplication, and division for complex        *
* numbers using the following formulas:                                          *
*                                                                                *
*                    a + bi + c + di = (a + c) + (b + d)i                        *
*                   a + bi - (c + di) = (a - c) + (b - d)i                       *
*                 (a + bi)*(c + di) = (ac - bd) + (bc + ad)i                     *
*         (a + bi)/(c + di) = (ac + bd)/(c2 + d2) + (bc - ad)i/(c2 + d2)         *
*                                                                                *
* You can also obtain the absolute value for a complex number using the          *
* following formula:                                                             *
*                                                                                *
*                            |a + bi| = √(a^2 + b^2)                             *
*                                                                                *
* (A complex number can be interpreted as a point on a plane by identifying the  *
* (a,b) values as the coordinates of the point. The absolute value of the        *
* complex number corresponds to the distance of the point to the origin, as      *
* shown in Figure 13.10)                                                         *
*                           													 *
* Design a class named Complex for representing complex numbers and the methods  *
* add, subtract, multiply, divide, and abs for performing complex number         *
* operations, and override toString method for returning a string representation *
* for a complex number.The toString method returns (a + bi) as a string. If b    *
* is 0, it simply returns a. Your Complex class should also implement Cloneable  *
* and Comparable. Compare two complex numbers using their absolute values.       *
* Provide three constructors Complex(a, b), Complex(a), and Complex().           *
* Complex() creates a Complex object for number 0, and Complex(a) creates a      *
* Complex object with 0 for b. Also provide the getRealPart() and                *
* getImaginaryPart() methods for returning the real part and the imaginary part  *
* of the complex number, respectively.   										 *
* 																				 *
* Draw the UML class diagram and implement the class. Write a test               *
* program that prompts the user to enter two complex numbers and displays the    *
* result of their addition, subtraction, multiplication, division, and 			 *
* absolute value.                                                     			 *
**********************************************************************************/

public class Complex implements Cloneable, Comparable<Complex> {
	// Declarations:
	private double a, b;
	
	// Constructor #1
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	// Constructor #2 - creates a Complex object with 0 for b
	public Complex(double a) {
		this(a, 0);
	}
	
	// Constructor #3 - creates a Complex object for number 0.
	public Complex() {
		this(0,0);
	}
	
	// Method #1 - returns the real part of the complex number.
	public double getRealPart() {
		return a;
	}
	
	// Method #2 - returns the imaginary part of the complex number.
	public double getImaginaryPart() {
		return b;
	}
	
	// Method #3 - Addition
	public Complex addition(Complex second) {
		return new Complex(a + second.a, b + second.b);
	}
	
	// Method #4 - Subtraction
	public Complex subtraction(Complex second) {
		return new Complex(a - second.a, b - second.b);
	}
	
	// Method #5 - Multiplication
	public Complex multiplication(Complex second) {
		return new Complex(a * second.a - b * second.b, b * second.a + a * second.b);
	}
	
	// Method #6 - Division
	public Complex division(Complex second) {
		return new Complex((a * second.a + b * second.b) / (Math.pow(second.a, 2) + Math.pow(second.b, 2)),
				(b * second.a - a * second.b) / (Math.pow(second.a, 2) + Math.pow(second.b, 2)));
	}
	
	// Method #7 - Absolute Value
	public double absoluteValue() {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	@Override
	public String toString() {
		return "(" + getRealPart() + " + " + getImaginaryPart() + "i)";
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException ex) {
			return null;
		}
	}
	
	@Override
	public int compareTo(Complex o) {
		double difference = absoluteValue() - o.absoluteValue();
		if (difference == 0)
			return 0;
		if (difference < 0)
			return -1;
		return 1;
	}
}
