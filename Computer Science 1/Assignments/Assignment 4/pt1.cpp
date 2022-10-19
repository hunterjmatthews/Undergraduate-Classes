/*
    Assignment: Homework 4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   July th, 2022 at 11:59pm.
*/

/*
Write a program that lets the user enter a charge account number. 
The program should determine if the number is valid by checking for it in the following list:

5658845 4520125 7895122 8777541 8451277 1302850
8080152 4562555 5552012 5050552 7825877 1250255
1005231 6545231 3852085 7576651 7881200 4581002

The list of numbers above should be initialized in a single-dimensional array. 
A simple linear search should be used to locate the number entered by the user. 
If the user enters a number that is in the array, the program should display a message 
saying that the number is valid. If the user enters a number that is not in the array, 
the program should display a message indicating that the number is invalid.

*/


#include <iostream>

using namespace std;

int linearSearch(int accountNumbers[], int userSpecifiedInt)
{
    for(int i = 0; i < 18; i++)
    {
        if (accountNumbers[i] == userSpecifiedInt) // If account number is found in the array, return the location
            return i;
    }
        return -1; // If account number is not found in the array, return -1 or 'false'.
}

int main()
{
    int accountNumbers[18] = {5658845, 4520125, 7895122, 8777541, 8451277, 1302850, 8080152, 4562555, 5552012, 5050552, 7825877, 1250255, 1005231, 6545231, 3852085, 7576651, 7881200, 4581002};
    int userSpecifiedInt = 0, results = 0;
    
    // Input - Ask user for a charge account to locate.
    cout << "Please enter a charge account number to search for: ";
    cin >> userSpecifiedInt;
    
    // Function Call
    results = linearSearch(accountNumbers, userSpecifiedInt);
    
    // Output
    if(results == -1)
        cout << "Charge account not found...\n";
    else
        cout << "Charge account found!\n";
    
    return 0;
}
