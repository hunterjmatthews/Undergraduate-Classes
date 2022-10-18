
/*
    Assignment: Assignment #4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   October 16th, 2022 at 11:59pm.
*/

public class HexDec {
	
	static boolean isCharHex(char ch) {
		return ch >= '0' && ch <= 'F';
	}
	
	public static int hex2Dec(String hex) {
		int dec = 0;
		
		for (int i = 0; i < hex.length(); i++) {
			char charHex = hex.charAt(i);
			if (isCharHex(charHex))
				dec = dec * 16 + hexCharToDecimal(charHex);
			else
				throw new HexFormatException(hex);
		}
		return dec;
	}
	
	public static int hexCharToDecimal(char ch) {
		if(ch >= 'A' && ch <= 'F')
			return 10 + ch - 'A';
		else
			return ch - '0';
	}

}
