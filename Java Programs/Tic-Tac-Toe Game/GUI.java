import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	GamePanel panCenter;
	private int screenWidth;
	private int screenHeight;
	private int top;
	private int left;
	Toolkit tk;
	Dimension dim;
	int gameType;
	JFrame mainGame;
	private boolean goAhead;
	JFrame menu;
	public GUI(){
		tk = Toolkit.getDefaultToolkit();
		dim = tk.getScreenSize();
		screenWidth = (int)dim.getWidth();
		screenHeight = (int) dim.getHeight();
		gameType=0;
		mainGame = new JFrame();
		menu = new JFrame();
	}
	/**
	 * Generates the GUI that contains the main game in the center of the screen.
	 */
	public void MenuGUI() {
		JFrame menu = new JFrame();
		int frameWidth = 800;
		int  frameHeight = 480;
		int left = (screenWidth-frameWidth)/2;
		int top = (screenHeight-frameHeight)/2;
		menu.setTitle("Tic-Tac-Toe Game");
		menu.setBounds(left,top,frameWidth,frameHeight);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = menu.getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout());
		JButton pvp = new JButton("1P vs 2P");
		pvp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = 0;
				menu.setVisible(false);
				MainGUI(gameType);
			}
			
		});
		JButton pvc = new JButton("1P vs CPU");
		pvc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = 1;
				panCenter= new GamePanel(gameType);
				menu.setVisible(false);
				MainGUI(gameType);
			}
			
		});
		JPanel Panel1 = new JPanel();
		Panel1.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(pvp);
		buttonPanel.add(pvc);
		
		Panel1.add(buttonPanel, BorderLayout.CENTER);
		c.add(Panel1, BorderLayout.SOUTH);
		menu.setVisible(true);
		
	}
	/**
	 * 
	 * @param gameType the game type the user selected on the menu
	 */
	public void setGameType(int gameType) {
		this.gameType = gameType;
		
	}
	/**
	 * 
	 * @return The game type the user selected
	 */
	public int getGameType() {
		return gameType;
	}
	/**
	 * The main game GUI that includes the game board
	 * @param gameType
	 */
	public void MainGUI(int gameType) {
		int frameWidth = 800;
		int  frameHeight = 480;
		int left = (screenWidth-frameWidth)/2;
		int top = (screenHeight-frameHeight)/2;
		
		mainGame.setTitle("Tic-Tac-Toe Game");
		mainGame.setBounds(left,top,frameWidth,frameHeight);
		mainGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = mainGame.getContentPane();
		panCenter= new GamePanel(gameType);
		c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout());
		c.add(panCenter,BorderLayout.CENTER);
		mainGame.setVisible(true);
	}
	/**
	 * Prints a new JFrame that tells the users who won the game, and prompts them to reset the board
	 * @param currentPlayer The player who won the game
	 */
	public void printWinScreen(int currentPlayer) {
		  JFrame frm = new JFrame();
		  int frameWidth = 400;
		  int frameHeight = 200;
		  int left = (screenWidth-frameWidth)/2;
		  int top = (screenHeight-frameHeight)/4;
		  frm.setBounds(left,top,frameWidth,frameHeight);
		  JLabel p1 = new JLabel("Player 1 wins");
		  p1.setHorizontalAlignment(JLabel.CENTER);
		  JLabel p2 = new JLabel ("Player 2 wins");
		  p2.setHorizontalAlignment(JLabel.CENTER);
		  JLabel tie = new JLabel("Its a tie");
		  tie.setHorizontalAlignment(JLabel.CENTER);
		  // could add cpu version later
		  Container v = frm.getContentPane();
		  if(currentPlayer ==1) {
			  v.add(p1, BorderLayout.CENTER);
		  }
		  else if(currentPlayer==2) {
			  v.add(p2, BorderLayout.CENTER);
		  }
		  else {
			  v.add(tie, BorderLayout.CENTER);
		  }
		  
		  JButton reset = new JButton("Reset Board");
		  reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panCenter.resetGameBoard();
				frm.dispose();
			}
		  });
		  JButton mainmenu  = new JButton ("Main Menu");
		  mainmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frm.dispose();
				mainGame.remove(panCenter);
				mainGame.dispose();
				MenuGUI();
			}
			  
		  });
		  JPanel mainPanel = new JPanel();
		  JPanel buttonPanel = new JPanel();
		  buttonPanel.add(reset);
		  buttonPanel.add(mainmenu);
		  mainPanel.add(buttonPanel, BorderLayout.CENTER);
		  v.add(mainPanel,BorderLayout.SOUTH);
		  frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		  frm.setVisible(true);
	}

}
