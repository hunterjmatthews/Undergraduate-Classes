/*
    Assignment: Homework 4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   July 5th, 2022 at 11:59pm.
*/

/*
Write a program that has an array of at least 20 integers. It should call a function that uses 
the linear search algorithm to locate one of the values. The function should keep a count of the number 
of comparisons it makes until it finds the value. 

The program then should call a function that uses the binary search algorithm to locate the same value. 
It should also keep count of the number of comparisons it makes. Display these values on the screen.

*/

#include <iostream>

using namespace std;

int linearSearch(int arr[], int userSpecifiedInt)
{
    // Declarations:
    int index = 0;
    bool found = false;
    
    // Loop: If userSpecifiedInt == arr[i] break and output the amt of comparisons that it took.
    for(int i = 0; i < 20; i++)
    {
        index++;
        
        if (arr[i] == userSpecifiedInt)
            break;
    }
        return index;
}

int binarySearch(int* arr, int size, int userSpecifiedInt)
{
    // Declarations:
    int f = 0, l = size-1, m = 0, p = -1, index = 0;
    bool found = false;
    
    while(!found && f <= l)
    {
        m = (f + l) / 2;
        
        if(arr[m] == userSpecifiedInt) 
        {
            found = true;
            p = m;
        }
        else if(arr[m] > userSpecifiedInt)
            l = m - 1;
        else
            f = m + 1;
        index++;
    }
    return index;
}

int main()
{
    // Declarations:
    int arr[20] = {21,38,61,27,29,32,23,9,38,100,78,27,6,15,13,77,76,35,8,34};
    int userSpecifiedInt = 0, results = 0, size = sizeof(arr) / sizeof(arr[0]), linearResults = 0, binaryResults = 0;

    // Input:
    cout << "Enter a number to find: ";
    cin >> userSpecifiedInt;
    
    // Function Calls:
    linearResults = linearSearch(arr, userSpecifiedInt);
    binaryResults = binarySearch(arr, size, userSpecifiedInt);
    
    // Output:
    cout << "Linear Comparisons: " << linearResults << " Binary Comparisons: " << binaryResults;
    
    return 0;
}
