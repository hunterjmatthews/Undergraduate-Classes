/*
    Assignment: Assignment #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   September 26th, 2022 at 11:59pm.
*/

// Importation:
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testBasicTime {
	
	// Get input from user:
	public static String askTime(String title, Scanner newObj) {
		System.out.println(title);
		
		String str;
		str = newObj.nextLine();
		
		while(!validateBasicTime(str)) {
			System.out.println("\nError - invalid time format entered. Please try again: ");
			str = newObj.nextLine();
		}
		
		return str;
	}
	
	// BasicTime Test:
	public static void basicTimeTest() {
		String str1, str2;
		str1 = askTime("Enter a time using the 12-hour format (HH:MMXX): ", new Scanner(System.in));
		str2 = askTime("Enter a time using the 12-hour format (HH:MMXX): ", new Scanner(System.in));
				
		BasicTime p1 = new BasicTime(str1);
		BasicTime p2 = new BasicTime(str2);
				
		System.out.println("\n########## Basic Time Test ##########");
		System.out.println("Time 1: " + p1.toString()); 
		System.out.println("Time 2: " + p2.toString());
				
		addTest(str1, str2);
		addToTest(str1, str2);
				
		subTest(str1, str2);
		subToTest(str1, str2);
	}
	
	// Method #1 - Method #4 Test
	// Method #1 Test:
	public static void addTest(String str1, String str2) {
		System.out.println("\n###### Addition Test ######");
		System.out.println("-- Test #1: non-static --");
			
		// Create new BasicTime object 'str3' and add str1 and str2 to str3.
		BasicTime str3 = new BasicTime();
		str3 = str3.add(new BasicTime(str1), new BasicTime(str2));
			
		// Display the results.
		System.out.println(str3.toString());
	}
		
	// Method #2 Test:
	public static void addToTest(String str1, String str2) {
		System.out.println("-- Test #2: static --");
		System.out.println(BasicTime.addTo(new BasicTime(str1), new BasicTime(str2)).toString());
	}
		
	// Method #3 Test:
	public static void subTest(String str1, String str2) {
		System.out.println("\n###### Subtraction Test ######");
		System.out.println("-- Test #1: non-static --");
			
		// Create new BasicTime object 'str3' and subtracts str1 and str2 to str3.
		BasicTime str3 = new BasicTime();
		str3 = str3.sub(new BasicTime(str1), new BasicTime(str2));
		System.out.println(str3.toString());
	}
		
	// Method #4 Test:
	public static void subToTest(String str1, String str2) {
		System.out.println("-- Test #2: non-static --");
		System.out.println(BasicTime.subTo(new BasicTime(str1), new BasicTime(str2)).toString());
	}
		
		// Regex Test:
		public static boolean validateBasicTime(String time) {
			// Uses Regex to check if time is in the valid 12-hour format.
			String regexPattern = "([01]?[0-9]):" + "[0-5][0-9](\\s)" + "?(?i)(am|pm)";
			
			// Compile
			Pattern newPattern = Pattern.compile(regexPattern);
			
			// Check if time is empty and if so, return false.
			if(time == null) {
				return false;
			}
			
			// Finds matching between given time and regex.
			Matcher m = newPattern.matcher(time.trim());
			
			return m.matches();
		 }
}
