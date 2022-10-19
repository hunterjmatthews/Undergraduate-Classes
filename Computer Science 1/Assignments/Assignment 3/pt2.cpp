/*
    Assignment: Homework #3
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 12th, 2022 at 11:59pm.
*/

/*
Recursive Exponent Function:
Write a function named "expo" that uses recursion to raise a number to a power. The function should accept two arguments

The number to be raised, and
The exponent.
Assume that the exponent is a non-negative integer.

Hint: Remember that exponentiation is simply repeated multiplication: 3^4 = 3 * 3 * 3 * 3
*/

#include <iostream>

using namespace std;

int exponent(int n, int p)
{
    
    // Base Case - Stop once n is equal to 0.
    if(n == 0)
        return 1;
    else
        return p * exponent(n-1, p);
}

int main()
{
    int n = 0, p = 0;
    
    cout << "----Recursive Exponent Function----\n";
    cout << "Enter a natural number: ";
    cin >> n;
    cout << "Enter the power you want the number raised to: ";
    cin >> p;

    cout << n << "^" << p << " = " << exponent(p, n);
    
    return 0;
}
