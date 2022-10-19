#include <iostream>
#include <algorithm>
#include <cstring>
#include <ctime>

using namespace std;

// Creates a macro that checks if 3 items are the same, and make's sure they are not 0.
#define compare(box1, box2, box3) ((board[box1] == board[box2]) && (board[box2] == board[box3]) && (board[box1] != 0))

// Creates a macro that takes a user inputed number and changes it into the letter/space.
#define numToLet(x) ((x > 0) ? (x == 1) ? 'X' : 'O' : ' ')

// Function Prototypes:
int winCheck(int board[9]);
bool endGame(int board[9]);
int predictWinner(int board[9], int player);
int fillSpace(int board[9], int spaces[4]);
int displayIntro();
void displayBoard(int board[9]);
int clearBoard(int board[9]);

// displayIntro - A function that displays the intro message with how to play. Also ask how many games the user would like to play.
int displayIntro()
{
    int games = 0;
    cout << "Welcome to Tic-Tac-Toe!\n";
    cout << "You are X and will go first\n";
    cout << "Make a play by typing which square you want to enter your X\n";
    cout << "+---+---+---+\n";
    cout << "| " << "1" << " | " << "2" << " | " << "3" << " | " << endl;
    cout << "+---+---+---+\n";
    cout << "| " << "4" << " | " << "5" << " | " << "6" << " | " << endl;
    cout << "+---+---+---+\n";
    cout << "| " << "7" << " | " << "8" << " | " << "9" << " | " << endl;
    cout << "+---+---+---+\n";
    cout << "How many games would you like to play? Type an integer 1-10 and hit [Enter] ";
    cin >> games;
    
    return games;
}

// displayBoard - A function that displays the board to the screen everytime a move is made by the user/computer.
void displayBoard(int board[9])
{
    for(int i = 0; i < 3; i++)
    {
        for(int j = 0; j < 3; j++)
        {
            cout << numToLet(board[3*i+j]) << ((j < 2) ? '|' : '\n');
        }
        cout << ((i < 2) ? "+---+\n" : "\n");
    }
}

// clearBoard - A function that clears the board after every game.
int clearBoard(int board[9])
{
    for(int i = 0; i <= 9; i++)
        board[i] = 0;
    
    return board[9];
}

// winCheck - A function that checks for the winner of the game. If none is found, returns false or '0'.
int winCheck(int board[9])
{
    int winner = 0;
    for(int i = 0; i < 3; i++) 
    {
        // Row Check:
        if(compare(3*i, 3*i+1, 3*i+2)) 
        {
            winner = board[3*i];
            break;
        } 
        // Column Check:
        else if(compare(i, i+3, i+6)) 
        {
            winner = board[i];
            break;
        }
        // Diagonal Check:
        else if(compare(2*i, 4, 8-2*i) && (i < 2))
        {
            winner = board[4];
            break;
        }
    }
    return winner;
}

// endGame - A function that checks if the game is finished, and if so, shows who won, or if a tie has occured.
bool endGame(int board[9])
{
    int winner = winCheck(board);
    if(winner > 0)
    {
        cout << numToLet(winner) << " wins!!" << endl;
        return true;
    }
    for(int i = 0; i < 9; i++)
    {
        if(board[i] == 0)
            return false;
    }
    cout << "It's a tie!\n\n";
    return true;
}

// gameCounter - A function that counts the number of games a player has won/lost.
void gameCounter(int board[9], int games, int *xIndex, int *oIndex)
{
    int winner = winCheck(board), x = 0, o = 0, t = 0;
    for(int i = 0; i < games; i++)
    {
        if(numToLet(winner) == 'X')
            x++;
        if(numToLet(winner) == 'O')
            o++;
    }
    *xIndex = x;
    *oIndex = o;
}

// predictWinner - A function that checks if the player could win in the next play.
int predictWinner(int board[9], int player)
{
    for(int i = 0; i < 9; i++)
    {
        int tempboard[9];
        memcpy(tempboard, board, 36);
        if(board[i] > 0)
            continue;
        tempboard[i] = player;
        if(winCheck(tempboard) == player)
            return i;
    }
    return -1;
}

// fillSpace - checks if a random corner or side is not taken.
int fillSpace(int board[9], int spaces[4])
{
    bool checkForEmptySpace = false;
    int temp;
    for(int i = 0; i < 4; i++)
    {
        if(board[spaces[i]] == 0)
        {
            checkForEmptySpace = true;
            break;
        }
    }
    if(checkForEmptySpace)
    {
        do 
        {
            temp = rand() % 4;
        }
        while(board[spaces[temp]] != 0);
        return spaces[temp];
    }
    return -1;
}

int main()
{
    // Declarations:
    int board[9] = {0,0,0,0,0,0,0,0,0};
    int move, posWinner, games, xIndex = 0, oIndex = 0;
    bool invalidMove;
    string userMove;
    srand((int) time(0));
    int corners[4] = {0,2,6,8};
    int sides[4] = {1,3,5,7};
    
    // Display the introduction message.
    games = displayIntro();
    
    // While Loop - Ask the player what move they would like to do.
    for(int g = 1; g <= games; g++)
    {
        cout << "Game: " << g << endl;
        while(true)
        {
            do
            {
                cout << "Your move: ";
                getline(cin, userMove);
                move = userMove[0] - '1';
                if (move > 8 || move < 0 || board[move] != 0)
                {
				    cout << "Invalid move" << endl;
				    invalidMove = true;
			    } 
			    else 
			    {
				    board[move] = 1;
				    invalidMove = false;
				    cout << endl;
			    }
		    } while (invalidMove);
		
		    // Game continues?
		    if(endGame(board) > 0)
		    {
		        displayBoard(board);
		        break;
		    }
		
		    // Computer decides what move it will do with the intent to win.
		    bool good = false;
		    for(int i = 2; i > 0; i--) 
		    {
		        posWinner = predictWinner(board, i);
			    if (posWinner != -1) 
			    {
				    board[posWinner] = 2;
				    good = true;
				    break;
			    }
		    }
		    if(good);
		    // da Middle.
		    else if (board[4] == 0) board[4] = 2;
		    // da Corners.
		    else if (fillSpace(board, corners) != -1) board[fillSpace(board, corners)] = 2;
		    // da Sides.
		    else board[fillSpace(board, sides)] = 2;
		
		    // Display's the board to the screen after every move. 
		    displayBoard(board);

		    // If statment to check if game is over with.
		    if(endGame(board))
		    {
		        gameCounter(board, games, &xIndex, &oIndex);
		        break;
		    }
	    }
	    clearBoard(board);
    }
    cout << "X won: " << xIndex << " games!\n";
    cout << "O won: " << oIndex << " games!\n";
    cout << "...of the " << games << " game set";
	return 0;
}
