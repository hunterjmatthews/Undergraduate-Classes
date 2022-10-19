/*
    Assignment: Homework #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 12th, 2022 at 11:59pm.
*/

/*
Line Numbers:

Write a program that asks the user for the name of a file. The program should display the contents of the file 
on the screen. Each line of screen output should be preceded with a line number, 
followed by a colon. The line numbering should start at 1. 
Here is an example:

1:George Rolland
2:127 Academy Street
3:Brasstown, NC 28706

If the file’s contents won’t fit on a single screen, the program should display 24 lines of output 
at a time, and then pause. Each time the program pauses, it should wait for the user to strike a key 
before the next 24 lines are displayed.

*/

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>

using namespace std;

int grabLine(ifstream& input)
{
    int lines = 0;
    string data = "";
    
    while(input)
    {
        getline(input, data);
        lines++;
    }
    
    return lines;
}

void appFile(ifstream& input, int& linesOfFile)
{
    string data = "";
    char test;
    int add = 1, index = 0;
    bool menu = false;
    
    while(!input.eof())
    {
        if(index < 24)
        {
            getline(input, data);
            index++;
            cout << add << ":" << data << endl;
            add++;
        }
        else
        {
            if(index == 24)
            {
                cout << "\nStrike the 'n' key to display next results: \n";
                cin >> test;
            }
            if(test == 'n')
            {
                getline(input, data);
                index++;
                cout << add << ":" << data << endl;
                add++;
            }
        }
    }
}

int main()
{
    // Ask user for file name:
    string userInput = "", data = "";
    cout << "Please enter the name of the file that you wish to open: ";
    cin >> userInput;
    
    // Declarations:
    ifstream input;
    int linesOfFile = 0;
    
    // Open file:
    input.open(userInput.c_str());
    
    // Error checking:
    if(!input)
    {
        cout << "That is not a valid file... please try again";
        return 0;
    }
    
    // Grab the # of lines in the file & close file:
    linesOfFile = grabLine(input);
    input.close();
    
    // Open the file & add some number "n" to the front of the string.:
    input.open(userInput.c_str());
    appFile(input, linesOfFile);
    
    // Close da file.
    input.close();

    return 0;
}
