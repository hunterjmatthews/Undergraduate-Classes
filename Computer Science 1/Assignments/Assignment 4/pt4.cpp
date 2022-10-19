/*
    Assignment: Homework 4
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   July 5th, 2022 at 11:59pm.
*/

/*
Write a program that uses two identical arrays of at least 20 integers. 
It should call a function that uses the bubble sort algorithm to sort one of the arrays in ascending order. 
The function should keep a count of the number of exchanges it makes. 

The program then should call a function that uses the selection sort algorithm to sort the other array. 
It should also keep count of the number of exchanges it makes. Display these values on the screen.

*/

#include <iostream>

using namespace std;

int bubbleSort(int arr[], int size)
{
    int results = 0;
    for(int max = size-1; max > 0; max--) 
    {
        for(int i = 0; i < max; i++)
        {
            if(arr[i] > arr[i+1]) {
                swap(arr[i], arr[i+1]);
                results++;
            }
        }
    }
    return results;
}

int selectionSort(int arr[], int size)
{
    int minIndex = 0, minValue = 0, results = 0;
    
    for(int start = 0; start < (size - 1); start++)
    {
        minIndex = start;
        minValue = arr[start];
        for(int index = start + 1; index < size; index++)
        {
            if(arr[index] < minValue)
            {
                minValue = arr[index];
                minIndex = index;
                results++;
            }
        }
        swap(arr[minIndex], arr[start]);
    }
    return results;
}
 
void swap(int& a, int& b)
{
    int temp = a;
    a = b;
    b = temp;
}

int main()
{
    // Declarations:
    int arr1[20] = {21,38,61,27,29,32,23,9,38,100,78,27,6,15,13,77,76,35,8,34};
    int arr2[20] = {21,38,61,27,29,32,23,9,38,100,78,27,6,15,13,77,76,35,8,34};
    int size = sizeof(arr1) / sizeof(arr1[0]);
    int bResults = 0, sResults = 0;
    
    
    // Function Calls:
    bResults = bubbleSort(arr1, size);
    sResults = selectionSort(arr2, size);
    
    // Output - Bubble Sort + Selection Sort for loops
    cout << "Bubble Sort Array: ";
    for(int i = 0; i < size; i++)
        cout << arr1[i] << " ";
    cout << "\nSelection Sort Array: ";
    for(int i = 0; i < size; i++)
        cout << arr2[i] << " ";
    
    // Output - Results from the number of exchanges.
    cout << "\nBubble Sort Number of Exchanges: " << bResults << endl;
    cout << "Selection Sort Number of Exchanges: " << sResults;
    return 0;
}
