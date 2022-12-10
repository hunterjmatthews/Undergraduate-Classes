//Robert H Cameron
// ITSE Final Project
// 11-21-22
// ================================= Sport's Tournament Program=================================
//* Program should store and organize four teams, of two, into brackets and then show the results of the tournament and ultimately have the winner as the output.
//* The Program also includes relevant information regarding the Tournament including the following: In-Game Rules for Smash Bro Ultimate, Event Details, Student Guidelines, and Tournament Prizes. 
//* The Program also offers a Tournament Menu which creates the bracket after user inputs team names and then asks user which teams won and then program displays the winning team.
// ==============================================================================================

#include <iostream>
#include <iomanip>
#include <string>
#include <vector>

using namespace std;

// Function Prototypes:
void userMenu();
vector<string> teamNames(vector<string> team, int teamNumber);
void returnToMenu();
void studentEventGuidelines();
void eventRules();
void eventPrizes();
void tournamentMenu(vector<string> &teamOne, vector<string> &teamTwo, vector<string> &teamThree, vector<string> &teamFour);
vector<string> generateBrackets(vector<string> tempTeam1, vector<string> tempString2);

vector<string> generateBrackets(vector<string> tempTeam1, vector<string> tempTeam2, int i) {
    vector<string> bracket;
    
    if(i == 1) {
        for(int j = 0; j <= i; j++) {
            bracket.push_back(tempTeam1.at(j));
            bracket.push_back(tempTeam2.at(j));
        }
    }
    if(i == 3) {
        for(int j = 2; j <= i; j++) {
            bracket.push_back(tempTeam1.at(j));
            bracket.push_back(tempTeam2.at(j));
        }
    }
    return bracket;
}

void tournamentMenu(vector<string> &teamOne, vector<string> &teamTwo, vector<string> &teamThree, vector<string> &teamFour) {
    // Dclarations:
    int eventRound = 0, menuOptions = 0;
    vector<string> bracket1, bracket2, bracket3, bracket4;
    
    // Display Round # and # of players
    cout << "Current Round: " << eventRound << endl;
    cout << "Number of Players: " << (teamOne.size() + teamTwo.size() + teamThree.size() + teamFour.size()) << endl;
    
    // Tournament Menu
    cout << "_________" << "Tournament Menu" << "_________\n";
    cout << "Enter 2 for inputing winners. Enter 3 for displaying brackets.\n";
    cin >> menuOptions;
    
    // Generate Brackets
    bracket1 = generateBrackets(teamOne, teamThree, 1);
    bracket2 = generateBrackets(teamOne, teamThree, 3);
    bracket3 = generateBrackets(teamTwo, teamFour, 1);
    bracket4 = generateBrackets(teamTwo, teamFour, 3);
    
    if(menuOptions == 2) {
    }
    
    if(menuOptions == 3) {
        cout << bracket1.size() << endl;
        cout << bracket2.size() << endl;
        cout << bracket3.size() << endl;
        cout << bracket4.size() << endl;
    }
}

void eventPrizes() {
    cout << "_________" << "Prizes" << "_________\n";
    cout << left << setw(12) << "First Place: \n";
	cout << left << setw(12) << "Second Place: Giftcard\n";
	cout << left << setw(12) << "Third Place: TBD\n";
	returnToMenu();
}

void eventRules() {
    cout << "Format: 2v2\n";
	cout << "Stock: 4\n";
	cout << "Time Limit:6 minutes\n";
	cout << "Items: 0\n";
	cout << "Stage Selection: Random\n";
	cout << "Stage Hazard Toggle: Off\n";
	cout << "Final Smash Meter: Off\n";
	cout << "Spirits: Off\n";
	cout << "Damage Handicap: Off\n";
	cout << "First to: 1 Win\n";
	cout << "Launch Rate: 1.0x\n";
	cout << "Underdog Boost: Off\n";
	cout << "Score Display: Off\n";
	cout << "Show Damage: Yes\n";
	cout << "Custom Balance: Off\n";
	cout << "Echo Fighters: Separate\n";
	cout << "Radar: Big\n";
	cout << "Pausing: Off\n";
	cout << "If time runs out, the winner is first determined by how man stocks remain., and then by the percentage after time is up\n";
	cout << "If both stock and percentage are the same, or a game ends in both players losing their last stock at the same time,\nthen a tiebreaker is played, which consists of a 1 stock and 3 minute match with the same characters and the same stage.\n";
	returnToMenu();
}

void studentEventGuidelines() {
    cout << "_________" << "Student Guidelines" << "_________\n";
	cout << "Students must Remain Respectful of Others throughout the Tournament. Failure to do so will result in an automatic withdrawal from\nthe Tournament and may hinder your ability to apply for Future Tournaments hosted by IGDA Club at Tyler Junior College.\n";
	cout << "Also Reference the Student Handbook for more information regarding this issue and more.\n\n";
	cout << "_________" << "Event Details" << "_________\n";
	cout << "Time/Date: To Be Determined\n";
	cout << "Where: TJC Rodgers Student Center - APACHE 4\n";
	cout << "Thank you & We hope you Enjoy the Smash Bros Ultimate Tournament\n\n";
	returnToMenu();
}

void returnToMenu() {
    char returnMenu;
    cout << "Would you like to return to the menu? Enter Y/N: ";
	cin >> returnMenu;
	// If user enters N or n
	if(returnMenu == 'N' || returnMenu == 'n')
	    cout << "See you!";
	// If user enters Y or y
	if (returnMenu == 'Y' || returnMenu == 'y') {
	    // Make the formating pretty:
	    cout << "\n";
	    // Clean the console.
	    system("clear");
	    // Sends the user back to the menu.
	    userMenu();
	}
}

vector<string> teamNames(vector<string> team, int teamNumber) {
    // Declarations:
    int index = 0; 
    string strTeam = "";
    bool end = 1;
	
	// Explaining how this works.
	cout << "Please Enter in The Names of All Four Teams Below (at least 5 characters long, only letters can be used; no numbers, special characters, spaces, or symbols): \n";
	// Enter Team:
	cout << "Please enter in the players for Team " << teamNumber << "\n";
	do {
	    cin >> strTeam;
		team.push_back(strTeam);
	} while(team.size() < 4);
	
	// Clean the console.
	system("clear");
	
	// Return vector<string> of team
	return team;
}

void userMenu() {
    // Declarations
    vector<string> teamOne, teamTwo, teamThree, teamFour;
    int menuOptions = 0, pin = 0;
    
    while(menuOptions != 6) {
        // Main Menu:
	    cout << "Menu Options (Please Enter 1-6)\n";
	    cout << "1. Enter Players\n";
	    cout << "2. Student Guidelines & Event Details\n";
	    cout << "3. In-Game Rules\n";
	    cout << "4. Tournament Prizes\n";
	    cout << "5. Tournament Menu\n";
	    cout << "6. Quit\n";
	    cin >> menuOptions;
	    
	    if(menuOptions == 1) {
	        teamOne = teamNames(teamOne, 1);
	        cout << teamOne.size();
            teamTwo = teamNames(teamTwo, 2);
            teamThree = teamNames(teamThree, 3);
            teamFour = teamNames(teamFour, 4);
	    }
	    if(menuOptions == 2) {
	        // Function call to 'studentEventGuidelines' that tells the user about the event details.
	        studentEventGuidelines();
	    }
	    if(menuOptions == 3) {
	        // Function call to 'eventRules' that tells the user about the rules of the event.
	        eventRules();
	    }
	    if(menuOptions == 4) {
	        // Function call to 'eventPrizes' that tells the user about the possible event prizes.
	        eventPrizes();
	   }
	   if(menuOptions == 5) {
	        while(pin != 1124) {
	        cout << "You must be a tournament admin to enter. Please enter the pin.\n";
	        cin >> pin;
	        tournamentMenu(teamOne,teamTwo,teamThree,teamFour);
	        }
	   }
	   if(menuOptions == 6) {
	        // End da program.
	        cout << "Exiting Program...\n";
	        cout << "***Remember to read our event guidelines and student handbook references before competing in the tournament***\n";
	        cout << "***Dont forget to ask an IGDA Professor Club Sponser or Club Officer if you have any further questions***\n";
	        cout << "Goodluck  and we hope you enjoy the Smash Bros Ultimate 2v2 Tournament hosted by IGDA\n";
	    }
    }
}

int main()
{
    // Function call user menu.
    cout << "_________" << "Smash Bros Ultimate 2v2 Tournament" << "_________\n";
    userMenu();
    
    return 0;
}
