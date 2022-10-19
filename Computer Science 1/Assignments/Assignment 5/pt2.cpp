/*
    Assignment: Homework 6
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 1337.5U1 (Computer Science I)
    Instructor: Chris Davis
    Date Due:   July 11th, 2022 at 11:59pm.
*/

/*
Write a program that stores the following data about a soccer player in a structure named Player:
pName (Player’s Name)
pNum (Player’s Number)
pPts (Points Scored by Player)
The program should keep an array of 12 of these structures. That is, something like Player playerArray[12];

Each element of the array is for a different player on a team. When the program runs it should

Auto-populate information for the 12 players (you are free to make up your own player data).
Display a table that lists each player’s number, name, and points scored. Use <iomanip> to format data in regular rows and columns.
Finally, the program should also calculate and display
the total overall points earned by the team.
the number and name of the player who has earned the most points.
Input Validation: Do not accept negative values for players’ numbers or points scored.

Note: Use the "Preformatted" paragraph" style and Courier New font for your code in the response.
*/

/*
    **NOTE** The prompt is kinda unclear. I am assuming what "you are free to make up your own player data" 
             means is that I can ask the user to input player data.
*/

#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

// Structure - That defines player data.
struct Player {
    string pName;
    int pNum, pPts;
};

// Void Function - Ask user for player information.
void setPlayerInfo(Player& p)
{
	cout << "Player name: ";
	cin >> p.pName;
	cout << "Player's number: ";
	cin >> p.pNum;
	
	// Input Validation While Loop - Player Number
	while (p.pNum < 0)
	{
		cout << "Error - Player's number cannot be negative. Please try again..." << endl;
		cout << "Player's number: ";
		cin >> p.pNum;
	}
	cout << "Points scored: ";
	cin >> p.pPts;
	
	//Input Validation While Loop - Player Points
	while (p.pPts < 0)
	{
		cout << "Error - Player's points cannot be negative. Please Try again." << endl;
		cout << "Player's points: ";
		cin >> p.pPts;
	}
	cout << endl;
	//cin.ignore();
}

// Void Function - That prints information about the team.
void printInfo(Player p)
{
    cout << setw(23) << p.pName << setw(9) << left << p.pNum << setw(13) << left << p.pPts << endl;
}

int main()
{
    // Declarations: 
    const int SIZE = 12;
    Player team[SIZE];
    
    // Loop - Formating/Function call that user inputs team data.
    cout << "********** UTD Soccer Team: **********" << endl;
    for (int index = 0; index < SIZE; index++)
	{
		cout << "---------" << endl;
		cout << "PLAYER #" << index + 1 << endl;
		cout << "---------" << endl;
		setPlayerInfo(team[index]);
	}
	
	// Loop - Formating/Function call that prints information about the team.
	cout << setw(20) << left << "Player Name: " << setw(8) << left << "Player #: " << setw(8) << left << "Points Scored: " << endl;
	for (int index = 0; index < SIZE; index++)
	{
		printInfo(team[index]);
	}
}
