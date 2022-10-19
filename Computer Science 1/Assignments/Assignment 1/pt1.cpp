/*
    Assignment: Homework #1
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 5th, 2022 at 11:59pm.
*/

#include <iostream>
#include <cctype>
#include <cstring>

using namespace std;

void backwards(char * str)
{
    int strLength = strlen(str); // Stores the length of the string to the strLength int.
    
    // Loop to start at the end of the user entered string and work backwards.
    for(int i = strLength; i >= 0; i--)
        cout << str[i];
}

int main()
{
    const int size = 100; // Statically set; Would want to make dynamic if out in the real world.
	char userInput[size]; // Set the user inputed text.
	
	cout << "Enter a string that you want to display its contents backwards: ";
	cin.getline(userInput, size); // Saves the string to userInput.
	
	cout << "Your string backwards: ";
	backwards(userInput); // Calls the backwards function which will display user entered string backwards.
	
	return 0;
}
