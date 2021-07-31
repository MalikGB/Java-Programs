import java.io.*; //
import java.util.Scanner; // Import the Scanner Utility.
import java.util.Random; // Import the Random

/* Robert Oliver, Malik Tifah, and Rafael Villalobos
 * CPSC-21000-002
 * Fall 2019
 * Programming Assignment 6
 */

// This program creates an object used to play a game of a mouse in a maze.
public class MouseRace2 {

    //initialize the variables, create a Scanner object, and create the array used for the maze.
    private int mouseRow = 1;
    private int mouseCol = 1;
    private int cheeseRow = 10;
    private int cheeseCol = 10;
    private int catRow;
    private int catCol;
   
    private int [] high = new int[10];
    private String fname = "topten.txt";
    private File f = new File(fname);
    MouseArrayStack mouse= new MouseArrayStack();
    MouseArrayStack cat = new MouseArrayStack();
    private int score = 0;
    private String move;
    private Random gen = new Random();
    private Scanner input = new Scanner(System.in);
    private char[][] maze = {
            {'#','#','#','#','#','#','#','#','#','#','#','#'},
            {'#',' ',' ','#','#','#','#','#','#','#',' ','#'},
            {'#','#',' ','#','#','#','#','#','#',' ',' ','#'},
            {'#','#',' ',' ',' ',' ',' ','#','#',' ','#','#'},
            {'#',' ',' ','#','#','#',' ','#',' ',' ','#','#'},
            {'#',' ','#','#','#','#',' ','#',' ',' ','#','#'},
            {'#',' ','#',' ',' ','#',' ','#','#',' ','#','#'},
            {'#',' ','#','#',' ','#',' ','#','#',' ','#','#'},
            {'#',' ',' ','#',' ','#',' ','#','#',' ','#','#'},
            {'#','#',' ','#',' ','#',' ',' ',' ',' ','#','#'},
            {'#',' ',' ',' ',' ','#','#','#','#',' ',' ','#'},
            {'#','#','#','#','#','#','#','#','#','#','#','#'},
    };

    // when called, this method will print out the header of the program.
    private void welcome(){
        System.out.println("CSPC 21000\nNAME: ROBERT OLIVER, MALIK TIFAH, AND RAFAEL VILLALOBOS\nPROGRAMMING ASSIGNMENT 6\n");
        System.out.println("**************************************");
        System.out.println("*** WELCOME TO THE MOUSE RACE GAME ***");
        System.out.println("**************************************");
    }

    // This method will congratulate the user when successfully clearing the maze and print out the total score.
    private void congratulations(){
        score += 100;
        printMaze();
        System.out.println("GAME OVER! MOUSE GOT THE CHEESE!");
        System.out.println("Your score was "+ score);
    }

    // This will let the user know they lost.
    private void loser(){
        score -= 100;
        printMaze();
        System.out.println("GAME OVER! YOU LOST! THE CAT GOT THE MOUSE!");
        System.out.println("Your score was "+ score);
    }

    //This will generate an initial location for the cat.
    private void catStart(){
        catRow = gen.nextInt(9)+1;
        catCol = gen.nextInt(9)+1;
        if(maze[catRow][catCol] == '#'){ // Will run again if the cat location is a wall.
            catStart();
        }
    }

    // This method will print out the maze to the console while attaching the place of the mouse,cat,and cheese.
    private void printMaze(){
        //This will set the symbols for cat, mouse, and cheese
        maze[cheeseRow][cheeseCol] = '$';
        maze[mouseRow][mouseCol] = '%';
        maze[catRow][catCol] = 'C';

        //The For loop prints the maze
        System.out.println();
        for(int i =0; i < maze.length; i++){
            for(int j =0; j<maze[i].length;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

    }

    // This will check if the game is won and return a false value to escape the while loop.
    private Boolean gameWon(){
        if(mouseRow == cheeseRow && mouseCol == cheeseCol) {
            return false;
        }
        else{
            return true;
        }

    }

    // Will test if the cat caught the mouse.
    private Boolean gameLost(){
        if(mouseRow == catRow && mouseCol == catCol) {
            maze[mouseRow][mouseCol]= ' ';
            return false;
        }
        else{
            return true;
        }

    }

    // This method will move the location of the mouse depending on the value given by adding or subtracting one
    // to the row or column.
    private void makeMove(int row, int col){
        if(maze[row][col] == '#'){
            System.out.println("You cannot move there!");
        }
        else{
            maze[mouseRow][mouseCol] = ' ';
            mouseRow = row;
            mouseCol =col;
            maze[mouseRow][mouseCol] = '%';
          
        }
    }

    //This will move the cat in a random direction.
    private void catMove(int row, int col){
        if(maze[row][col] != '#'){
            maze[catRow][catCol] = ' ';
            catRow = row;
            catCol =col;
            maze[catRow][catCol] = 'C';
          
        }
    }

    // This method will parse the move variable and send a new value to the makeMove() method depending on the choice.
    private void parseCmd(){
        String m = move.trim().toLowerCase(); // String variable with no whitespace and lower cased for comparisons.
        switch(m) {
            case("n"):
            	mouse.push(mouseRow,mouseCol);
        		cat.push(catRow, catCol);
                makeMove(mouseRow - 1, mouseCol);
                catDirection();
                score --;
                break;
            case("s"):
            	mouse.push(mouseRow,mouseCol);
        		cat.push(catRow, catCol);
                makeMove(mouseRow + 1, mouseCol);
                catDirection();
                score --;
                break;
            case("e"):
            	mouse.push(mouseRow,mouseCol);
    			cat.push(catRow, catCol);
                makeMove(mouseRow, mouseCol + 1);
                catDirection();
                score --;
                break;
            case("w"):
            	mouse.push(mouseRow,mouseCol);
    			cat.push(catRow, catCol);
                makeMove(mouseRow, mouseCol - 1);
                catDirection();
                score --;
                break;
            case("u"):
                undo();
                break;
            default: // This will print out in case they entered an invalid choice.
                System.out.println("That move is not allowed. Please select a different move.");
        }
    }

    // This will pick the direction of the cat at random.
    private void catDirection(){
    	int randMove = gen.nextInt(4);
    	switch(randMove) {
        	case(0):
        		catMove(catRow - 1, catCol);
                break;
            case(1):
            	catMove(catRow + 1, catCol);
                break;
            case(2):
            	catMove(catRow, catCol + 1);
                break;
            case(3):
            	catMove(catRow, catCol - 1);
                break;
        }
    }
	
	//Method to undo the previous move.
    private boolean undo() {

        // As long as there are previous positions that the mouse has been in
        if(mouse.topX >= 1 && mouse.topY>= 1) {
            maze[mouseRow][mouseCol]= ' '; // Putting a blank where the mouse is currently
            // Setting the values of the mouse's position to the values it had previously
            mouseRow = mouse.popX();
            mouseCol = mouse.popY();
            maze[catRow][catCol]= ' ';// putting a blank where the cat currently is
            // Setting the values of the cat's position to the values it had previously
            catRow= cat.popX();
            catCol= cat.popY();
            score+=1;// Resetting the score
            return true;
        }
        else {
            System.out.println("\n*No more undos left*\n");
            score = 0;updater();
            return false;
        }
    }

    // Method to sort the array top ten scores.
    private void sorter()
    {
        int s[]= new int[11];
        for(int a=0; a<high.length; a++)
        {
            s[a] = high[a];
        }
        s[10] = score;
        for (int b=1; b<s.length; b++)
        {
            int newScore = s[b];
            int c = b;
            while (c>0 && s[c-1]< newScore)
            {
                s[c] = s[c-1];
                c--;
            }
            s[c] = newScore;
        }
        for(int d=0; d<high.length; d++)
        {
            high[d] = s[d];
        }
    }

    // Looks for a the file and if it does not exist initializes the top ten scores.
    private void reader()
    {
        try
        {
            if(f.exists()) { // Will check if the file exists.
                String line = " ";
                Scanner fsc = new Scanner(f);
                while (fsc.hasNext()) {
                    line = fsc.nextLine();
                }
                String lister[] = line.split(",");
                for (int i = 0; i < high.length; i++) {
                    high[i] = Integer.parseInt(lister[i]);

                }
            }
            else
                for(int i = 0; i< high.length; i++ ){
                    high[i] = -10000;
                }


        } catch (IOException e)
        {

        }
    }
    // prints the top 10 scores to the screen
    private void printer()
    {
        System.out.println("TOP 10 SCORES");
        for (int score = 0; score <high.length; score++)
        {
            System.out.println(score+1 + ". " + high[score]);
        }
    }
    // Updates the file containing the scores.
    private void updater()
    {
        try
        {
            PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("topten.txt")));
            for ( int i = 0 ; i <high.length; i++)
            {
                p.print(high[i] + ",");
            }
            p.close();

        }catch (IOException e)
        {
            System.out.print("Error with reading the input file.");
        }
    }

    // The constructor method which will be used to create the object and play the game.
    private MouseRace2(){
        welcome();
        catStart();
        reader();
        printer();
        while (gameWon() && gameLost()) {
            printMaze();
            System.out.println("Your current score: " + score);
            System.out.print("Select a move direction (n/s/w/e) or press u to undo a move: ");
            move = input.next();
            parseCmd();
        }
        if(!gameWon()) { // If false, the mouse won.
            congratulations();
        }
        else // The cat won.
            loser();
        sorter();
        updater();
        printer();
    }

    // The main method will create a new object using the constructor method to begin the game.

    public static void main(String[] args){

        new MouseRace2();

    }
}