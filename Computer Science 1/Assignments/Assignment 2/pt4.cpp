/*
    Assignment: Homework #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   June 12th, 2022 at 11:59pm.
*/

/*
Average Number of Words:
If you have downloaded this book’s source code from the companion Web site, you will find a file named 
text.txt in the Chapter 12 folder. The text that is in the file is stored as one sentence per line. 
Write a program that reads the file’s contents and calculates the average number of words per sentence.
*/

#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

int findWords(const string& sentence)
{
    // Declarations:
    int index = 0;
    string word = "";
    istringstream iss (sentence);
    
    // Loop to stream a word until white space is found.
    while (iss >> word)
    {
        index++;
    }

    // Return the number of words. 
    return index;
}

void loop(fstream& input)
{
    // Declarations:
    int sentenceIndex = 0, wordIndex = 0;
    string line = "";
    const char eol = '.';
    
    // Read da file & send each line to the findWords function:
    while(getline(input, line, eol))
    {
        sentenceIndex++;
        wordIndex += findWords(line);
    }
    
    // Remove extra line:
    sentenceIndex--;
    
    // Print Results:
    cout << "Found: " << sentenceIndex << " sentences in the file." << endl;
    cout << "Found: " << wordIndex << " words in it." << endl;
    double averageWords = double (wordIndex) / double (sentenceIndex);
    cout << "The files has an average of " << averageWords << " words." << endl;
}

int main()
{
    // Open file:
    fstream input("test.txt", ios::in);
    
    // File error checking:
    if(!input)
        cout << "Invalid File.";
     
    // Call function that solves the problem:   
    loop(input);
    
    // Close file:
    input.close();
}
