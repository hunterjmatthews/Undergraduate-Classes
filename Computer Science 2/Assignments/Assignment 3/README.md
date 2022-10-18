# The Triangle Class:
Design a class named Triangle that extends GeometricObject. The class contains:
- Three double data fields named side1, side2, and side3 with default values 1.0 to denote three sides of a triangle.
- A no-arg contructor that creates a default triangle.
- A constructor that creates a triangle with the specified side1,side2, and side3.
- The accessor methods for all three data fields.
- A method named getArea() that returns the area of this triangle.
- A method names getPerimeter() that returns the permiter of this triangle.
- A method named toString() that returns a string description for the triangle.

The toString() method is implemented as follows:
return "Triangle: side1 = " + side1 + " side 2 = " + side2 + " side 3 = " + side3;
 
Draw the UML diagrams for the classes Triangle and GeometricObject and implement the classes. Write a test program that prompts the user to enter three sides of the triangle, a color, and a Boolean value to indicate whether the triangle is filled. The program should create a Triangle object with these sides and set the color and filled properties using the input. The program should display the area, perimeter, color, and true or false to indicate whether it is filled or not.
