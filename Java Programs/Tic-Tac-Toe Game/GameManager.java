
public class GameManager {
	private static int[][] panels;
	private static int playerTurn;
	int winRow;
	int winColumn;
	int winDiagonal;
	private static GUI mainGame;
	int tieCheck;
	char winType;


	
	public GameManager() {
		// Could change this later to where its random who goes first
		playerTurn = 1;
		panels = new int[3][3];
		winRow=0;
		winColumn =0;
		winDiagonal =0;
		tieCheck=0;
	}

	/**
	 * 
	 * @param panel the Panel that either the player or the computer selected
	 * @param selection Determines weather the panel was selected by either the player 
	 * 0 = The panel is unselected
	 * 1 = the player selected the panel
	 * 2 = CPU selected the panel
	 */
	public void setPanel(int panelX, int panelY, int player) {
		panels[panelX][panelY] = player;
	}
	/**
	 * 
	 * @param i Horizontal panel position
	 * @param j Vertical Panel position
	 * @return An integer representing which player selected a certain panel
	 */
	public int getPanel(int i, int j) {
		return panels[i][j];
	}
	/**
	 * 
	 * @return which player currently has control
	 */
	public int getPlayerTurn() {
		return playerTurn;
	}
	/**
	 * Switches control from one player to another
	 * @param currentPlayerTurn player that currently has control
	 */
	public int switchPlayerTurn(int currentPlayerTurn) {
			if(currentPlayerTurn==1) {
				GameManager.playerTurn =2;
				return GameManager.playerTurn;
			}
			else {
				GameManager.playerTurn = 1;
				return GameManager.playerTurn;
			}
		}
	/**
	 * Begins the game
	 * @param args
	 */
	public static void main(String[] args) {
		
		mainGame = new GUI();
		mainGame.MenuGUI();
		//mainGame.MainGUI();
	}
	
	/**
	 * This method checks for different combinations where either the player or CPU has won the game. (3 in a row, column, or in a diagonal)
	 */
	// TODO Fix bug where you get "its a tie" when you click 9 spaces that have already been filled
	public boolean checkForMatches() {
		// Possible winning index combinations:
		//Horizontal
		/*(0,0) , (1,0), (2,0)
		 * (0,1), (1,1), (2,1)
		 * (0,2), (1,2), (2,2)
		 * Vertical
		 * (0,0), (0,1), (0,2)
		 * (1,0) (1,1), (1,2)
		 * (2,0), (2,1), (2,2)
		 * Diagonal
		 *(0,0) (1,1), (2,2)
		 *(2,0), (1,1),(0,2)
		 */
		// Checking for the rows
		for(int r=0; r<3; r++) {
			if(panels[r][0] == panels[r][1] && panels[r][2]==panels[r][1]&& panels[r][1]>0) {
				winRow=r;
				winType='r';
				return true;
			}
		}
		// Checking for columns
		for(int c =0; c<3; c++) {
			if(panels[0][c]== panels[1][c]&& panels[1][c] == panels[2][c] && panels[1][c]>0) {
				winColumn=c;
				winType= 'c';
				return true;
			}
		}
		// Checking for diagonal from the left column
		if(panels[0][0]== panels[1][1] && panels[2][2]==panels[1][1] && panels[1][1]>0) {
			winDiagonal++;
			winType='d';
			return true;
		}
		//Checking for diagonal from the right column
		if(panels[2][0]==panels[1][1]&& panels[1][1]==panels[0][2]&& panels[1][1]>0) {
			winDiagonal+=2;
			winType='d';
			return true;
			
		}
		tieCheck++;
		//System.out.println(tieCheck);
		if(tieCheck>=9) {
			return true;
		}

		
		winDiagonal=winRow=winColumn=0;
		return false;
	}
	/**
	 * Helps determine wether the winning combination was from a diagonal row, or column
	 * Mainly used for drawing the "win line" across the winning combination in the Jpanel
	 * @return integer representing the winning combination
	 */
	public int getWinLine() {
		if(winDiagonal>0) {
			return winDiagonal;
		}
		if(winRow>0) {
			return winRow;
		}
		if(winColumn>0) {
			return winColumn;
		}
		return 0;
	}
	/**
	 * 
	 * @return char representing the type of win the player got
	 */
	public char getWinType() {
		return winType;
		
	}
	/**
	 * Determines which player (or neither) has won the game
	 * If all spaces are filled in before a winning combination is made a tie is declared.
	 * @param currentPlayer player who made the winning move
	 */
	public void setWinner(int currentPlayer) {
		if(tieCheck<9) {
			mainGame.printWinScreen(currentPlayer);
		}
		else {
			mainGame.printWinScreen(0);
		}
		tieCheck=0;
	}

	/**
	 * This method deletes the old array by generating a new reference
	 */
	public void clearArrayList() {
		panels = new int[3][3];
		playerTurn=1;
		
	}
}
