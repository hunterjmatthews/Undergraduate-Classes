/*
    Assignment: Homework 4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   July th, 2022 at 11:59pm.
*/

/*
Modify the selectionSort function presented in this chapter so it sorts an array of strings instead of 
an array of int's. Test the function with a driver program. Use Program 8-8 as a skeleton to complete.

*/

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main()
{
	// Declarations:
	const int NUM_NAMES = 20;
	string names[NUM_NAMES] = {"Collins, Bill", "Smith, Bart", "Allen, Jim",
                               "Griffin, Jim", "Stamey, Marty", "Rose, Geri",
                               "Taylor, Terri", "Johnson, Jill", "Allison, Jeff",
                               "Looney, Joe", "Wolfe, Bill", "James, Jean",
                               "Weaver, Jim", "Pore, Bob", "Rutherford, Greg",
                               "Javens, Renee", "Harrison, Rose", "Setzer, Cathy",
                               "Pike, Gordon", "Holland, Beth" };
    int size = sizeof(names)/sizeof(names[0]);

    // Sort Alg:
    sort(names,names+size);

    // Output:
    for(int i = 0; i < size; i++)
        cout << names[i] << endl;

	return 0;
}
