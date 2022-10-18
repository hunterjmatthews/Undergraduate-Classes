/*
    Assignment: Assignment #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   September 26th, 2022 at 11:59pm.
*/

public class Main {
	
	// Part 1: BasicTime
	public static void testViaBasicTime() {
		testBasicTime.basicTimeTest();
		System.out.println();
	}
	
	
	// Part 2: PreciseTime
	public static void testViaPreciseTime() {
		testPreciseTime.preciseTimeTest();
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		// Test BasicTime:
		testViaBasicTime();
		
		// Test Part 2: PreciseTime
		testViaPreciseTime();
	}
}
