import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {
	
	GameManager manager;
	int currentPlayer;
	private boolean isGameOver;
	int line;
	protected int gameType;
	char winType;
	CPU cpu = new CPU();
	//Format: X1,Y1,X2,Y2
	int [][] arrayWinCoordinatesV ={{150,5,150,390}, {340,5,340,390}, {560,5,560,390}};
	int [][] arrayWinCoordinatesH = {{40,40,640,40}, {40,190,640,190}, {40,320,640,320}};
	int [][] arrayWinCoordinatesD = {{110,30,600,390}, {550,20,80,400}};
	
	public GamePanel(int gameType) {
		addMouseListener(this);
		manager = new GameManager();
		isGameOver=false;
		this.gameType= gameType;
	}
	
	/**
	 * Function that draws all of the elements for the JPanel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//TODO figure out how to make the position of the lines dependent on screensize
		
		// Vertical panel lines
		g.drawLine(250, 0, 250, 400);
		g.drawLine(450, 0, 450, 400);
		//Horizontal panel lines
		g.drawLine(50, 100, 650, 100);
		g.drawLine(50, 250, 650, 250);
		// Retrieves the current player's turn
		currentPlayer = manager.getPlayerTurn();
		if(currentPlayer==1) {
			g.setColor(Color.red);
		}
		else {
			g.setColor(Color.blue);
		}
		
		int check; // Checks if a player has already selected a panel before the turn starts
		// Goes through the panel array and draws objects in the Panels if a player has selected one of the 9 panels
		for(int i=0; i<3; i++) {
			for(int j=0; j<3;j++) {
				check = manager.getPanel(i, j);
			if(check>0) {
				// This is where we begin to draw the circles / x's
				switch(i) {
				case(0):
					switch(j){
					case(0):
						if(check ==1) {
							g.setColor(Color.red);
							g.drawOval(110, 5, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(110, 5, 190, 80);
							g.drawLine(190, 5, 110, 80);
						}
						break;
					case(1):
						if(check ==1) {
							g.setColor(Color.red);
							g.drawOval(110, 150, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(110, 150, 190, 230);
							g.drawLine(190, 150, 110, 230);
						}
						
						break;
					case(2):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(110, 300, 80, 80);
						}
						else {
							g.setColor(Color.BLUE);
							g.drawLine(110, 300, 190, 380);
							g.drawLine(190, 300, 110, 380);
						}
						break;
					}
				break;
				case(1):
					switch(j) {
					case(0):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(300, 5, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(300, 5, 380, 80);
							g.drawLine(380, 5, 300, 80);
						}
						break;
					case(1):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(300, 150, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(300, 150, 380, 230);
							g.drawLine(380, 150, 300, 230);
						}
						break;
					case(2):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(300, 300, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(300, 300, 380, 380);
							g.drawLine(380, 300, 300, 380);
						}
						break;
					}
				break;
				case(2):
					switch(j) {
					case(0):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(520, 5,  80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(520, 5, 600, 80);
							g.drawLine(600, 5, 520, 80);
						}
						break;
					case(1):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(520, 150, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(520, 150, 600, 230);
							g.drawLine(600, 150, 520, 230);
						}
						
						break;
					case(2):
						if(check==1) {
							g.setColor(Color.red);
							g.drawOval(520, 300, 80, 80);
						}
						else {
							g.setColor(Color.blue);
							g.drawLine(520, 300, 600, 380);
							g.drawLine(600, 300, 520, 380);
						}
						break;
					}
				break;
				}
			}
		}
	}
		// Triggers when a player gets a winning combination or a tie is declared.
		// Draws a line over the winning combination of objects to show the user who won.
		if(isGameOver) {
			if(currentPlayer==1) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLUE);
			}
			// WinType = row, column Diagonal
			//WinLine =  on which line did it occur
			line = manager.getWinLine();
			winType = manager.getWinType();
			if(winType=='c') {
				switch(line) {
				case(0):
					g.drawLine(arrayWinCoordinatesH[0][0], arrayWinCoordinatesH[0][1], arrayWinCoordinatesH[0][2], arrayWinCoordinatesH[0][3]);
					break;
				case(1):
					g.drawLine(arrayWinCoordinatesH[1][0], arrayWinCoordinatesH[1][1], arrayWinCoordinatesH[1][2], arrayWinCoordinatesH[1][3]);
				break;
				case(2):
					g.drawLine(arrayWinCoordinatesH[2][0], arrayWinCoordinatesH[2][1], arrayWinCoordinatesH[2][2], arrayWinCoordinatesH[2][3]);
				break;
			}
		}
			if(winType=='r') {
				switch(line) {
				case(0):
					g.drawLine(arrayWinCoordinatesV[0][0], arrayWinCoordinatesV[0][1], arrayWinCoordinatesV[0][2], arrayWinCoordinatesV[0][3]);
					break;
				case(1):
					g.drawLine(arrayWinCoordinatesV[1][0], arrayWinCoordinatesV[1][1], arrayWinCoordinatesV[1][2], arrayWinCoordinatesV[1][3]);
				break;
				case(2):
					g.drawLine(arrayWinCoordinatesV[2][0], arrayWinCoordinatesV[2][1], arrayWinCoordinatesV[2][2], arrayWinCoordinatesV[2][3]);
				break;
				}
			}
			if(winType=='d') {
				switch(line) {
				case(1):
					g.drawLine(arrayWinCoordinatesD[0][0], arrayWinCoordinatesD[0][1], arrayWinCoordinatesD[0][2], arrayWinCoordinatesD[0][3]);
					break;
				case(2):
					g.drawLine(arrayWinCoordinatesD[1][0], arrayWinCoordinatesD[1][1], arrayWinCoordinatesD[1][2], arrayWinCoordinatesD[1][3]);
				break;
				}
			}
	  }
	}

	 /**
	  * Function that is responsible for Checking which panel in the JPanel, the player selects.
	  */
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean validClick = false;
		currentPlayer= manager.getPlayerTurn();
		if(50<e.getX() && e.getX()<250) {
			if(e.getY()>0 && e.getY()<100) {
			//Panel 1 selected
			if(manager.getPanel(0, 0)<=0) {
				manager.setPanel(0, 0, currentPlayer); // (0,0) has the number one placed there
				validClick=true;
			}
		
			}
			if(e.getY()>100 && e.getY()<250) {
				if(manager.getPanel(0, 1)<=0) {
					manager.setPanel(0, 1, currentPlayer); // (0,0) has the number one placed there.
					validClick=true;
				}
				// Panel 4 selected
			}
			if(e.getY()>250 && e.getY()<400) {
			//panel 7 selected
			if(manager.getPanel(0, 2)<=0) {
				manager.setPanel(0, 2, currentPlayer); // (0,0) has the number one placed there.
				validClick=true;
				}
			}
		}
		else if(251<e.getX() && e.getX()<450) {
			if(e.getY()>0 && e.getY()<100) {
				//Panel 2 selected
				if(manager.getPanel(1, 0)<=0) {
					manager.setPanel(1, 0, currentPlayer); // (0,0) has the number one placed there.
					validClick=true;
				}
			}
		if(e.getY()>100 && e.getY()<250) {
			// Panel 5 selected
			if(manager.getPanel(1, 1)<=0) {
				manager.setPanel(1, 1, currentPlayer); // (0,0) has the number one placed there.
				validClick=true;
			}
		}
			if(e.getY()>250 && e.getY()<400) {
				//panel 8 selected
				if(manager.getPanel(1, 2)<=0) {
					manager.setPanel(1, 2, currentPlayer); // (0,0) has the number one placed there.
					validClick=true;
				}
			}
		}
			
		else if(450<e.getX() && e.getX()<650) {
			if(e.getY()>0 && e.getY()<100) {
				//Panel 3 selected
				if(manager.getPanel(2, 0)<=0) {
					manager.setPanel(2, 0, currentPlayer); // (0,0) has the number one placed there.
					validClick=true;
				}
			}
			if(e.getY()>100 && e.getY()<250) {
				// Panel 6 selected
				if(manager.getPanel(2, 1)<=0) {
					manager.setPanel(2, 1, currentPlayer); // (0,0) has the number one placed there.
					validClick=true;
				}
			}
			if(e.getY()>250 && e.getY()<400) {
				//panel 9 selected
				if(manager.getPanel(2, 2)<=0) {
					manager.setPanel(2, 2, currentPlayer); // (0,0) has the number one placed there.
					validClick=true;
				}
			}
		}
		repaint();
		//Checks if the last move resulted in a winning combinations. If it does, the game is over and the winning line is drawn.
		if(validClick) {
			if(manager.checkForMatches()) {
				drawWinCondition();
				isGameOver=true;
			}
			else {
			currentPlayer = manager.switchPlayerTurn(currentPlayer);
			}
			if(gameType==1) {
				if(!isGameOver) {
				CPUTurn();
				//System.out.println(currentPlayer);
				if(manager.checkForMatches()) {
					drawWinCondition();
					isGameOver=true;
				}
				else {
					currentPlayer = manager.switchPlayerTurn(currentPlayer);
				}
			}
		}
			
		}
		System.out.println(gameType);
		
		
		
	}
	/**
	 * Only used if the player selected the 1p vs CPU mode
	 * Uses a random number generator to select one of the panels
	 */
	public void CPUTurn() {
		while(true) {
			cpu.CPUGenerator();
			if(manager.getPanel(cpu.getCPUX(),cpu.getCPUY())<=0) {
				manager.setPanel(cpu.getCPUX(), cpu.getCPUY(), currentPlayer);
				repaint();
				break;
			}
			else {
				continue;
			}
		}
	}
	/**
	 * Disables the user's ability to keep clicking the panel, and declares a winner
	 */
	private void drawWinCondition() {
		removeMouseListener(this);
		manager.setWinner(currentPlayer);
		repaint();
		
	}
	/**
	 * Resets the board by clearing the array, re-adding the mouse listener, and reseting the game conditions
	 */
	public void resetGameBoard() {
		manager.clearArrayList();
		addMouseListener(this);
		currentPlayer=1;
		isGameOver=false;
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void createNewManager() {
		manager = new GameManager();
		
	}
	

}
