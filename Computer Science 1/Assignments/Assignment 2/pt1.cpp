/*
    Assignment: Homework #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 12th, 2022 at 11:59pm.
*/

/*
File Head Program:
Write a program that asks the user for the name of a file. 
The program should display the first 10 lines of the file on the screen (the “head” of the file). 
If the file has fewer than 10 lines, the entire file should be displayed, 
with a message indicating the entire file has been displayed.
*/

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>

using namespace std;

void readFile(ifstream& input)
{
    // Declarations:
    int index = 0;
    string data = "";
    
    // While loop to read data from file and outputs the first 10 lines to console.
    while(input)
    {
        getline(input, data);
        index++;
        if(index <= 10)
            cout << data << endl;
    }
    if(index <= 10)
        cout << "The entire file has been displayed." << endl;
}

int main()
{
    // Ask user for file name:
    string userInput = "";
    cout << "Please enter the name of the file that you wish to open: ";
    cin >> userInput;
    
    // Declarations:
    ifstream input;
    
    // Open file:
    input.open(userInput.c_str());
    
    // Error checking:
    if(!input)
    {
        cout << "That is not a valid file... please try again";
        return 0;
    }
        
    // Function call:
    readFile(input);

    // Close da file(s):
    input.close();
    
    return 0;
}
