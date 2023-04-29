package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author 		austinnorgaard
 * @version 	04/28/2023
 * @description Class declaration of GameScreen
 * @duedate		05/03/2023
 * @hwnumber	Final
 * @extends 	BorderPane
 * @implements  EventHandler<ActionEvent>
 */
public class GameScreen extends BorderPane implements EventHandler<ActionEvent> {
	/** Restart button for a new game */
	private Button restart;
	
	/** Main menu button to go to main menu */
	private Button mainMenu;
	
	/** The game object for playing the game */
	private Game mancala;
	
	/** The top GridPane for the title and current player */
	private GridPane topPane;
	
	/** The GridPane containing the pockets */
	private GridPane boardPane;
	
	/** The GridPane containing Player 1's pool */
	private GridPane pool1Pane;
	
	/** The GridPane containing Player 2's pool */
	private GridPane pool2Pane;
	
	/** The GridPane containing the button for a new game and main menu */
	private GridPane bottomPane;
	
	/** The text for player 1's pool */
	private Text p1text;
	
	/** The text for player 2's pool */
	private Text p2text;
	
	/** The Array of Buttons for the pockets of the game */
	private Button[] buttonArray;
			
	/** Mancala Title Text */
	private Text mancalaTitle;

	/**
	 * The default constructor for the GameScreen
	 * @param menuButton The button for returning to the main menu
	 * @throws FileNotFoundException if file for the background is not found
	 */
	public GameScreen(Button menuButton) throws FileNotFoundException {
		// Initialize variables
		mancala = new Game();
		
		restart = new Button("New Game");
		mainMenu = menuButton;
		
		topPane = new GridPane();
		boardPane = new GridPane();
		pool1Pane = new GridPane();
		pool2Pane = new GridPane();
		bottomPane = new GridPane();
		
		buttonArray = new Button[14];
		
		p1text = new Text("0");
		p2text = new Text("0");
		
		mancalaTitle = new Text("Mancala");
		
		// Initializing the buttons for the Board's pockets
		for (int i = 0; i < Game.NUM_POCKETS/2 - 1; i++) {
			buttonArray[i] = new Button("" + Game.STONES_PER_POCKET);
			buttonArray[i + 7] = new Button("" + Game.STONES_PER_POCKET);
		}
		
		// Set the scene styles, add actions to buttons, and add components
		setSceneStyles();
		setAllButtonActions();
		addComponentsToPanes();
		addComponentsToScene();
	}
	
	/**
	 * Sets the scene styles
	 * @throws FileNotFoundException if file for background is not found
	 */
	private void setSceneStyles() throws FileNotFoundException {
		setBackground();
		setButtonStyle(restart);
		setButtonStyle(mainMenu);
		setBottomPaneStyle();
		setPoolPaneStyle(pool1Pane, 73, 223, 106, 50);
		setPoolPaneStyle(pool2Pane, 74, 223, 106, -54);
		setPoolTextStyle(p1text, Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20), 29, 90);
		setPoolTextStyle(p2text, Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20), 29, 90);
		organizeBoardPane();
		organizeBoardButtons();
		organizeTopPane();
	}
	
	/**
	 * Sets all the actions for the buttons in the scene
	 */
	private void setAllButtonActions() {
		setRestartButtonAction();
		setBoardButtonActions();
	}
	
	/**
	 * Adds the components such as buttons and text to their panes
	 */
	private void addComponentsToPanes() {
		pool1Pane.add(p1text, 0, 0);
		pool2Pane.add(p2text, 0, 0);
		
		addBoardButtons();
		topPane.add(mancalaTitle, 0, 0);
		
		setCurrentPlayer();
		
		bottomPane.add(mainMenu, 0, 0);
		bottomPane.add(restart, 0, 1);
	}
	
	/**
	 * Adds the components to the scene
	 */
	private void addComponentsToScene() {
		this.setTop(topPane);
		this.setLeft(pool1Pane);
		this.setCenter(boardPane);
		this.setRight(pool2Pane);
		this.setBottom(bottomPane);
	}
	
	/**
	 * Sets the background for this scene
	 * @throws FileNotFoundException if file for background is not found
	 */
	private void setBackground() throws FileNotFoundException {
		// Background for the board
		Image bgImg = new Image(new FileInputStream("MancalaBoard.png"));
		BackgroundImage backgroundImage = new BackgroundImage(bgImg, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT, 
                   BackgroundSize.DEFAULT);
		Background bg = new Background(backgroundImage);
		this.setBackground(bg);
	}
	
	/**
	 * Sets the buttons style
	 * @param buttonToStyle The button for styling
	 */
	private void setButtonStyle(Button buttonToStyle) {
		buttonToStyle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		buttonToStyle.setStyle("-fx-text-fill: black;");
		buttonToStyle.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
	}
	
	/**
	 * Organizes all of the pocket buttons
	 */
	private void organizeBoardButtons() {
		// Making the BoardButtons prettier
		for (int i = 0; i < buttonArray.length/2 - 1; i++) {
			buttonArray[i].setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			buttonArray[i + 7].setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			
			// Formatting the BoardButtons specific to this application
			buttonArray[i].setMinSize(64, 90);
			buttonArray[i + 7].setMinSize(64, 90);
			
			// Setting the background to null so they appear as not buttons
			buttonArray[i].setBackground(null);
			buttonArray[i + 7].setBackground(null);
		}
	}
	
	/**
	 * Organizes the topPane
	 */
	private void organizeTopPane() {
		topPane.setAlignment(Pos.TOP_CENTER);
		topPane.setTranslateY(40);
		mancalaTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
	}
	
	/**
	 * Organizes the boardPane containing the pocket buttons
	 */
	private void organizeBoardPane() {
		// Formatting the GridPane specific to this application
		boardPane.setMaxWidth(560);
		boardPane.setMinWidth(560);
		boardPane.setMinHeight(223);
		boardPane.setMaxHeight(223);
		boardPane.setTranslateY(-5);
		// Setting gaps/formatting boardPane GridPane specific to this application
		boardPane.setHgap(35);
		boardPane.setVgap(41);
	}
	
	/**
	 * Sets the current player based on current player in the game
	 * @param game The game in which the player is playing
	 * @param topPane The pane to add the new Text to
	 */
	private void setCurrentPlayer() {
		// If previous text is in the space, remove it
		if (topPane.getChildren().size() > 1) {
			topPane.getChildren().remove(1);
		}
		
		// String for the current player
		String playerString = "Current Turn:\n"
							+ "     Player ";
		
		// Get the player from the game [0,1] + 1 to normalize
		playerString += (mancala.getCurrentPlayer() + 1);
		
		// Text for the current player
		Text playerText = new Text(playerString);
		
		// Make the text prettier
		playerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		playerText.setTranslateX(15);

		// Add the text to the pane
		topPane.add(playerText, 0, 1);
	}
	
	/**
	 * Adds the pocket buttons to the boardPane
	 */
	private void addBoardButtons() {
		// Add the BoardButtons to the MancalaHoles GridPane in correct order
		for (int i = 0; i < buttonArray.length; i++) {
			// First player is backwards (6 - 0)
			if (i < 6) {
				boardPane.add(buttonArray[5 - i], i, 0);
			}
			// Second player is forwards (0 - 6)
			else if (i > 6 && i < 13) {
				boardPane.add(buttonArray[i], i - 7, 1);
			}
		}
	}
	
	/**
	 * Sets the pocket button actions
	 */
	private void setBoardButtonActions() {
		// Walking through the BoardButtons and setting their actions
		for (int i = 0; i < buttonArray.length/2 - 1; i++) {
			// A click will move the marbles and update the board's count in every pocket
			buttonArray[i].setOnAction(this);
			buttonArray[i + 7].setOnAction(this);
		}
	}
	
	/**
	 * Sets the restart buttons action
	 */
	private void setRestartButtonAction() {
		restart.setOnAction(this);
	}
	
	/**
	 * The handle method for handling all button events
	 * @param event The ActionEvent listener
	 */
	public void handle(ActionEvent event) {
		// Check which pocket is selected
		for (int i = 0; i < buttonArray.length/2 - 1; i++) {
			if (event.getSource() == buttonArray[i]) {
				// If game isn't over make move and update values
				if (!mancala.gameOver()) {
					moveMarbles(i, 0);
					setCurrentPlayer();
					updateBoard();
					// If this move ended game, update components and values
					if (mancala.gameOver()) {
						addEndGameComponents();
						updateBoard();
					}
				}
			}
			if (event.getSource() == buttonArray[i + 7]) {
				// If game isn't over make move and update values
				if (!mancala.gameOver()) {
					moveMarbles(i, 1);
					setCurrentPlayer();
					updateBoard();
					// If this move ended game, update components and values
					if (mancala.gameOver()) {
						addEndGameComponents();
						updateBoard();
					}
				}
			}
		}
		// If restart button is pressed, create a new game and update components/values
		if (event.getSource() == restart) {
			mancala = new Game();
			setCurrentPlayer();
			updateBoard();
			try {
				setSceneStyles();
			} catch (FileNotFoundException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("File System Error: ");
				alert.setContentText("" + e);
				alert.showAndWait();
			}
		}
	}
	
	/**
	 * Method for adding components for the end of the game
	 */
	private void addEndGameComponents() {
		// If previous text exists in the space, remove it
		if (topPane.getChildren().size() > 1) {
			topPane.getChildren().remove(1);
		}
		
		// Creating the string for the winner
		Text winnerText = new Text();
		
		// If not tied game, adjust how it is presented
		if (mancala.getWinner() != -1) {
			winnerText.setText("Winner: " + mancala.getWinnerString(mancala.getWinner()));
		}
		else {
			winnerText.setText(mancala.getWinnerString(mancala.getWinner()));
		}
		
		setWinnerTextStyle(winnerText);
		
		// Add text to pane
		topPane.add(winnerText, 0, 1);
		
		// Adjust spacing for panes
		boardPane.setTranslateY(8);
		pool1Pane.setTranslateY(126);
		pool2Pane.setTranslateY(126);
		
		// Update the top for root
		this.setTop(topPane);
	}
	
	/**
	 * Sets the style of the WinnerText
	 * @param winnerText The text for who won the game
	 */
	private void setWinnerTextStyle(Text winnerText) {
		// Make the text prettier
		winnerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		// Adjust styling if a tied game
		if (mancala.getWinner() == -1) {
			winnerText.setTranslateX(25);
		}
	}
	
	/**
	 * Updates all of the values for each changed pocket on the board
	 */
	private void updateBoard() {
	 	for (int i = 0; i < buttonArray.length/2 - 1; i++) {
	 		// Update stone counts for player 1
	 		buttonArray[i].setText("" + mancala.getPocket(0, i).getStones());
	 		
	 		// Update stone counts for player 2
			buttonArray[i + 7].setText("" + mancala.getPocket(1, i).getStones());
			
			// Update stone count for player 1's pool
			p1text.setText("" + mancala.getPocket(0, Game.POOL_INDEX).getStones());
			
			// Update stone count for player 2's pool
			p2text.setText("" + mancala.getPocket(1, Game.POOL_INDEX).getStones());
	 	};
	}
	
	/**
	 * Moves the marbles by calling Game's makeMove method and first checks if move is valid
	 * @param index The pocket to check validity and make a move from
	 * @param playerID The current player to check validity and make a move for
	 */
	private void moveMarbles(int index, int playerID) {
		if (mancala.getCurrentPlayer() == playerID && mancala.getPocket(mancala.getCurrentPlayer(), index).getStones() > 0) {
			  mancala.makeMove(index);
		  }
	}
	
	/**
	 * Sets the bottom pane's style
	 */
	private void setBottomPaneStyle() {
		// Formatting the GridPane specific to this application
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setTranslateY(-20);
		// Add spacing between 2 bottomPane buttons
		bottomPane.setVgap(10);
	}
	
	/**
	 * Sets the pool panes style based on size (width/height) and position (translate X/Y)
	 * @param poolPane The pane for adjustment
	 * @param minMaxWidth The absolute width of the pane
	 * @param minMaxHeight The absolute height of the pane
	 * @param transY The Y position
	 * @param transX The X position
	 */
	private void setPoolPaneStyle(GridPane poolPane, double minMaxWidth, double minMaxHeight, double transY, double transX) {
		// Formatting the GridPane specific to this application
		poolPane.setMinWidth(minMaxWidth);
		poolPane.setMaxWidth(minMaxWidth);
		poolPane.setMinHeight(minMaxHeight);
		poolPane.setMaxHeight(minMaxHeight);
		poolPane.setTranslateY(transY);
		poolPane.setTranslateX(transX);
	}
	
	/**
	 * Sets the text of the pool's style
	 * @param poolText The text for the pool
	 * @param font The font for the text
	 * @param transX The X position
	 * @param transY The Y position
	 */
	private void setPoolTextStyle(Text poolText, Font font, double transX, double transY) {
		// Making the pool Text prettier and formatting them to be center in their GridPanes
		poolText.setFont(font);
		poolText.setTranslateX(transX);
		poolText.setTranslateY(transY);
	}
}
