/*
    Assignment: Homework #1
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 5th, 2022 at 11:59pm.
*/

#include <iostream>
#include <string>

using namespace std;

void stringToMorse(string input)
{
    // Declarations:
    char a;
    string morse = "";
    
    // Iterator loop to check each char:
    // Could have used a switch.
    for(int i = 0; i < input.length(); i++)
    {
        a = input[i];
        // Punctuation:
        if(a == ' ')
            morse += " ";
        if(a == ',')
            morse += "--..--";
        if(a == '.')
            morse += ".-.-.-";
        if(a == '?')
            morse += "..--..";
        // Numbers:
        if(a == '0')
            morse += "-----";
        if(a == '1')
            morse += ".----";
        if(a == '2')
            morse += "..---";
        if(a == '3')
            morse += "...--";
        if(a == '4')
            morse += "....-";
        if(a == '5')
            morse += ".....";
        if(a == '6')
            morse += "-....";
        if(a == '7')
            morse += "--...";
        if(a == '8')
            morse += "---..";
        if(a == '9')
            morse += "----.";
        // Letters:
        if(a == 'a' || a == 'A')
            morse += ".-";
        if(a == 'b' || a == 'B')
            morse += "-...";
        if(a == 'c' || a == 'C')
            morse += "-.-.";
        if(a == 'd' || a == 'D')
            morse += "-..";
        if(a == 'e' || a == 'E')
            morse += ".";
        if(a == 'f' || a == 'F')
            morse += "..-.";
        if(a == 'g' || a == 'G')
            morse += "--.";
        if(a == 'h' || a == 'H')
            morse += "....";
        if(a == 'i' || a == 'I')
            morse += "..";
        if(a == 'j' || a == 'J')
            morse += ".---";
        if(a == 'k' || a == 'K')
            morse += "-.-";
        if(a == 'l' || a == 'L')
            morse += ".-..";
        if(a == 'm' || a == 'M')
            morse += "--";
        if(a == 'n' || a == 'N')
            morse += "-.";
        if(a == 'o' || a == 'O')
            morse += "---";
        if(a == 'p' || a == 'P')
            morse += ".--.";
        if(a == 'q' || a == 'Q')
            morse += "--.-";
        if(a == 'r' || a == 'R')
            morse += ".-.";
        if(a == 's' || a == 'S')
            morse += "...";
        if(a == 't' || a == 'T')
            morse += "-";
        if(a == 'u' || a == 'U')
            morse += "..-";
        if(a == 'v' || a == 'V')
            morse += "...-";
        if(a == 'w' || a == 'W')
            morse += ".--";
        if(a == 'x' || a == 'X')
            morse += "-..-";
        if(a == 'y' || a == 'Y')
            morse += "-.--";
        if(a == 'z' || a == 'Z')
            morse += "--..";
    }
    // Print conversion
    cout << "Your input: " << input << endl;
    cout << "Your output: " << morse << endl;
}

int main()
{
    // Declarations:
    string userInput = "";
    char end;
    bool loop = true;
    
    // User Prompt:
    cout << "****Morse Code Converter****\n";
    cout << "Please enter a string you would like to convert to morse code: ";
    getline(cin, userInput);
    
    // Send to da function:
    stringToMorse(userInput);
    
    return 0;
}
