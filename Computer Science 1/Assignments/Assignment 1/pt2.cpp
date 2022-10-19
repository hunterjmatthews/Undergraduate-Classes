/*
    Assignment: Homework #1
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 5th, 2022 at 11:59pm.
*/

/*
  Sentence Capitalizer
  Write a function that accepts a pointer to a C-string as an argument and capitalizes 
  the first character of each sentence in the string. For instance, if the string argument 
  is “hello. my name is Joe. what is your name?” the function should manipulate the string so 
  it contains “Hello. My name is Joe. What is your name?”. Demonstrate the function in a program 
  that asks the user to input a string and then passes it to the function. The modified string should 
  be displayed on the screen.
*/

#include <iostream>
#include <string>
#include <fstream>
#include <cctype>
#include <cstring>

using namespace std;

char *capitalize(char *input) 
{
    int stringLength = strlen(input); // Get size/length of the string.
    
    input[0] = toupper(input[0]); // Capitalizes first letter of the string.
    
    // Loop to capitalize after if finds punct.
    for(int i = 0; i < stringLength; i++) 
    {
        if(input[i] == '.' || input[i] == '!' || input[i] == '?')
        {
            i++;
            input[i] = toupper(input[i]);
            if(input[i] == ' ')
            {
                i++;
                input[i] = toupper(input[i]);
            }
        }
    }
    return input;
}

int main()
{
    const int size = 100; // Statically set; Would want to make dynamic if out in the real world.
	char userInput[size]; // Set the user inputed text.

	cout << "Enter a string that you want to capitalize the first character of each sentence: " << endl;
	cin.getline(userInput, size); // Saves the string to userInput.
	cout << "Your new reformated sentence is below." << endl;
	cout << capitalize(userInput) << endl; // Displays the results of the capitalize function.
	
	return 0;
}
