/*
    Assignment: Homework #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 12th, 2022 at 11:59pm.
*/

/*
Tail Program
Write a program that asks the user for the name of a file. 
The program should display the last 10 lines of the file on the screen (the “tail” of the file). 
If the file has fewer than 10 lines, the entire file should be displayed, 
with a message indicating the entire file has been displayed.
*/

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>

using namespace std;

void readFile(ifstream& input, int& lines)
{
    // Declarations:
    string data = "";
    int index = 0, check = 0;
    check = lines - 11;
    bool lessThan = false, moreThan = false;
    
    // While the file is open loop:
    while(input)
    {
        getline(input, data);
        index++;
        
        if(lines <= 10 && index < 10)
        {
            cout << data << endl;
            lessThan = true;
        }
        if(lines > 10 && index < lines) 
        {
            if(index >= check)
            {
                cout << data << endl;
            }
        }
    }
    if(lessThan)
        cout << "The entire file was printed since it had fewer than 10 lines.\n";
}

int main()
{
    // Ask user for file name:
    string userInput = "", data = "";
    cout << "Please enter the name of the file that you wish to open: ";
    cin >> userInput;
    
    // Declarations:
    ifstream input;
    int lines = 0;
    
    // Open file:
    input.open(userInput.c_str());
    
    // Error checking:
    if(!input)
    {
        cout << "That is not a valid file... please try again";
        return 0;
    }
    
    // While loop to count the # of lines of the file.
    while(input)
    {
        getline(input, data);
        lines++;
    }
    
    input.close();
    
    input.open(userInput.c_str());
        
    // Function call:
    readFile(input, lines);
    
    // Close da file.
    input.close();
    
    return 0;
}
