/*
    Assignment: Homework #1
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 5th, 2022 at 11:59pm.
*/

#include <iostream>
#include <cstring>
#include <string>

using namespace std;

void sumOf(string input)
{
    // Initializers:
    int sum = 0;
    string temp = "";
    char small = '9'; // Largest a # can be.
    char large = '0'; // Smallest a # can be.
    
    // Sum of all the values in the string. (i.e. "123" -> 1+2+3=5)
    for(int i = 0; i < input.length(); i++)
    {
        if(input[i] == '1' || input[i] == '2' || input[i] == '3' || input[i] == '4' || input[i] == '5' || input[i] == '6' || input[i] == '7' || input[i] == '8' || input[i] == '9')
        {
            temp = input[i]; // Saves current place in loop to a temp holding string.
            sum += stoi(temp); // Convert temp string to int value and adds it to sum.
        }
    }
    // Find the Largest Value:
    for(int j = 0; j < input.length(); j++)
    {
        if(input[j] > large)
            large = input[j];
    }

    // Find the Smallest Number
    for(int k = 0; k < input.length(); k++)
    {
        if(input[k] < small)
            small = input[k];
    }
    
    cout << "The sum of " << input << " is " << sum << endl;
    cout << "The largest integer is " << large << " and the smallest integer is " << small;
}

int main()
{
    string userInput = ""; // Define the string that the user will input.
    
    // Prompt user for multi-digit integer.
    cout << "Please enter a multi-digit integer: ";
    cin >> userInput;
    
    sumOf(userInput);
    
    return 0;
}
