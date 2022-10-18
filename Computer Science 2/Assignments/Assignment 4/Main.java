
/*
    Assignment: Assignment #4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   October 16th, 2022 at 11:59pm.
*/

public class Main {

	public static void main(String[] args) {
		// Declare Test Strings:
		String testString1 = "C5C8";
		String testString2 = "D5DP";
		
		// Print to screen
		System.out.println("Converting Hex String to Decimal...");
		System.out.println("Hex String -> Decimal: " + HexDec.hex2Dec(testString1));
		System.out.println("Hex String -> Decimal: " + HexDec.hex2Dec(testString2));

	}

}
