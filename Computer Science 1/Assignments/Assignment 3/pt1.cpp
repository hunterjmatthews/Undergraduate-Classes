/*
    Assignment: Homework #3
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 12th, 2022 at 11:59pm.
*/

/*
Iterative Factorial:
Write an iterative version (using a while loop instead of recursion) of the factorial function shown in this chapter.
*/

#include <iostream>

using namespace std;

void factorial(int num)
{
    int i = 1, final = 1;
    
    while(i <= num)
    {
        final = final * i;
        i++;
    }
    
    cout << "The factorial of " << num << " is " << final;
}

int main()
{
    int num;
    
    cout << "----Iterative Factorial----\n";
    cout << "Enter a number to compute its factorial: ";
    cin >> num;
    
    factorial(num);
    
    return 0;
}
