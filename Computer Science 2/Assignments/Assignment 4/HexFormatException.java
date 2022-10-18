/*
    Assignment: Assignment #4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   October 16th, 2022 at 11:59pm.
*/

/*
 * Creates a custom exception called "HexFormatException" that extends "NumberFormatException"
 * 
 */

public class HexFormatException extends NumberFormatException {
	public HexFormatException(String hex) {
		super("illicit Hex: " + hex);
	}
}
