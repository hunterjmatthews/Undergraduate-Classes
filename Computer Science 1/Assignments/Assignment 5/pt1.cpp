/*
    Assignment: Homework 6
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   July 11th, 2022 at 11:59pm.
*/

/*
Write a program that uses a structure named MovieData to store the following information about a movie:
title
director
yearReleased
runningTime (in minutes)
The program should create two MovieData variables, store values in their members, and pass each one, in turn, to a function that displays the information about the movie in a clearly formatted manner. Your function should have the prototype, 

void showMoveData(MovieData);

Note: Use the "Preformatted" paragraph" style and Courier New font for your code in the response.
*/

#include <string>
#include <iostream>

using namespace std;

// Define Structure:
struct MovieData {
    string title;
    string director;
    int yearReleased;
    int runningTime;
};

// Function Prototypes
void showMovieData(MovieData);

// Print Function
void showMovieData(MovieData m)
{
    cout << "-----------------------------------------" << endl;
    cout << "Movie Title: " << m.title << endl;
    cout << "Movie Director: " << m.director << endl;
    cout << "Release Year: " << m.yearReleased << endl;
    cout << "Running Time (minutes): " << m.runningTime << endl;
}

int main()
{
    MovieData Matrix = {"The Matrix", "Wachowski Brothers", 1999, 132};
    MovieData LOTR = {"The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", 2001, 178};
    showMovieData(Matrix);
    showMovieData(LOTR);
}
