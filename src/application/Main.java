package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer; 
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 		austinnorgaard
 * @version 	04/28/2023
 * @description Class declaration of Main
 * @duedate		05/03/2023
 * @hwnumber	Final
 * @extends  	Application
 */
public class Main extends Application implements EventHandler<ActionEvent> {
	/** The root BorderPane object for the application/menu screen */
	private BorderPane root;
	
	/** The menu button used to navigate the application */
	private Button menuButton;
	
	/** The play button to start the game and navigate to the Game screen */
	private Button playGame;
	
	/** The rule button to navigate to the rules screen */
	private Button ruleButton;
	
	/** The quit button to exit the application */
	private Button quitButton;
	
	/** The GridPane containing the MancalaTitle */
	private GridPane topPane;
	
	/** The GridPane containing the Two Buttons on the menu */
	private GridPane menuOptions;
	
	/** Mancala Title Text */
	private Text MancalaTitle;
	
	/** Media for the backgroundMusic */
	private Media bgMusicMedia;
	
	/** Background music for the mancala game */
	private MediaPlayer backgroundMusic;
	
	/** Background music option */
	private CheckBox bgMusicOption;
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		// Initialize variables
		root = new BorderPane();
		
		playGame = new Button("Play Game");
		ruleButton = new Button("Rules");
		quitButton = new Button("Quit");
		
		MancalaTitle = new Text("Mancala");
		
		bgMusicOption = new CheckBox("Background Music");
		
		bgMusicMedia = new Media(new File("BackgroundMusic.wav").toURI().toString());
		
		backgroundMusic = new MediaPlayer(bgMusicMedia);
		
		try {
			Scene scene = new Scene(root,832,612);
			primaryStage.setResizable(false);
			scene.getStylesheets().add(new File("CheckBox.css").toURI().toString());
			primaryStage.setTitle("Final project: Austin Norgaard");
			addSplashScreenComponents();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("" + e);
			alert.showAndWait();
		}
	}
	
	/**
	 * The main menu splash screen
	 * @throws FileNotFoundException if the file used for the background is not found
	 */
	public void addSplashScreenComponents() throws FileNotFoundException {
		// Remove any root components
		root.getChildren().clear();
		
		// Reinitialize some variables each time the menu loads, to reset formatting
		menuButton = new Button("Main Menu");
		
		topPane = new GridPane();
		menuOptions = new GridPane();
		
		addStylesToComponents();
		setButtonActions();
		addComponentsToPanes();
		addComponentsToScene();
	}
	
	/**
	 * Adds the components to the scene
	 */
	private void addComponentsToScene() {
		root.setTop(topPane);
		root.setCenter(menuOptions);
	}
	
	/**
	 * Starts the background music
	 */
	private void startBackgroundMusic() {
		backgroundMusic.setVolume(0.05);
		backgroundMusic.play();
		backgroundMusic.setCycleCount(Integer.MAX_VALUE);
	}
	
	/**
	 * Adds the styles to all of the components and scene
	 * @throws FileNotFoundException if file for background is not found
	 */
	private void addStylesToComponents() throws FileNotFoundException {
		setBackground();
		setTopPaneStyle();
		setTitleStyle();
		setBackgroundMusicOptionStyle();
		setGameButtonStyle(playGame, Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40), "-fx-text-fill: black;", new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		setGameButtonStyle(ruleButton, Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40), "-fx-text-fill: black;", new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)), 50);
		setGameButtonStyle(quitButton, Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40), "-fx-text-fill: black;", new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)), 65);
		setMenuStyle();
	}
	
	/**
	 * Adds the buttons and title to panes
	 */
	private void addComponentsToPanes() {
		topPane.add(MancalaTitle, 0, 0);
		menuOptions.add(playGame, 0, 0);
		menuOptions.add(ruleButton, 0, 1);
		menuOptions.add(quitButton, 0, 2);
		menuOptions.add(bgMusicOption, 0, 3);
	}
	
	/**
	 * Set the style for background music option
	 */
	private void setBackgroundMusicOptionStyle() {
		bgMusicOption.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		bgMusicOption.setTranslateX(20);
		bgMusicOption.setTranslateY(40);
		bgMusicOption.setStyle("");
	}
	
	/**
	 * Sets the actions for the background music checkbox
	 */
	private void setBackgroundMusicOptionAction() {
		if (backgroundMusic.getStatus() == MediaPlayer.Status.UNKNOWN || 
			backgroundMusic.getStatus() == MediaPlayer.Status.STOPPED) {
			bgMusicOption.setSelected(false);
		}
		else {
			bgMusicOption.setSelected(true);
		}
		bgMusicOption.setOnAction(this);
	}
	
	/**
	 * Sets the style for the top pane
	 */
	private void setTopPaneStyle() {
		topPane.setAlignment(Pos.TOP_CENTER);
		topPane.setTranslateY(40);
	}
	
	/**
	 * Sets the title's style
	 */
	private void setTitleStyle() {
		MancalaTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
	}
	
	/**
	 * Set the game button styles based on their font, style, and background
	 * @param buttonToStyle The button chosen to style
	 * @param font The font for the button text
	 * @param style The style for the button
	 * @param bg The background for the button
	 */
	private void setGameButtonStyle(Button buttonToStyle, Font font, String style, Background bg) {
		buttonToStyle.setFont(font);
		buttonToStyle.setStyle(style);
		buttonToStyle.setBackground(bg);
	}
	
	/**
	 * Set the game button styles based on their font, style, background, and position (transX)
	 * @param buttonToStyle The button chosen to style
	 * @param font The font for the button text
	 * @param style The style for the button
	 * @param bg The background for the button
	 * @param transX The X position for the button
	 */
	private void setGameButtonStyle(Button buttonToStyle, Font font, String style, Background bg, double transX) {
		buttonToStyle.setFont(font);
		buttonToStyle.setStyle(style);
		buttonToStyle.setBackground(bg);
		buttonToStyle.setTranslateX(transX);
	}
	
	/**
	 * Set the actions for the buttons in this scene
	 */
	private void setButtonActions() {
		menuButton.setOnAction(this);
		playGame.setOnAction(this);
		ruleButton.setOnAction(this);
		quitButton.setOnAction(this);
		setBackgroundMusicOptionAction();
	}
	
	/**
	 * Set the menu options styles
	 */
	private void setMenuStyle() {
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setVgap(10);
	}
	
	/**
	 * Sets the background for the main menu scene
	 * @throws FileNotFoundException if file for the background is not found
	 */
	private void setBackground() throws FileNotFoundException {
		// Background for the board
		Image bgImg = new Image(new FileInputStream("BackgroundColor.png"));
		BackgroundImage backgroundImage = new BackgroundImage(bgImg, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT, 
                   BackgroundSize.DEFAULT);
		Background bg = new Background(backgroundImage);
		root.setBackground(bg);
	}
	
	/**
	 * The handle method for handling all button events
	 * @param event The ActionEvent listener
	 */
	public void handle(ActionEvent event) {
		// If the menu button is pressed, return to the menu
		if (event.getSource() == menuButton) {
			try {
				// Clear the BorderPane
				root.getChildren().clear();
				addSplashScreenComponents();
			} catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("" + e);
				alert.showAndWait();
			}
		}
		// If the play game button is pressed, start a new game
		if (event.getSource() == playGame) {
			try {
				// Clear the BorderPane
				root.getChildren().clear();
				root.setCenter(new GameScreen(menuButton));
			} catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("" + e);
				alert.showAndWait();
			}
		}
		// If the rule button is pressed, open the rules page
		if (event.getSource() == ruleButton) {
			try {
				// Clear the BorderPane
				root.getChildren().clear();
				root.setCenter(new RuleScreen(menuButton));
			} catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("" + e);
				alert.showAndWait();
			}
		}
		// If the quit button is pressed, exit the application
		if (event.getSource() == quitButton) {
			System.exit(1);
		}
		// If the background music option is unchecked, stop music, else start it
		if (event.getSource() == bgMusicOption) {
			if (bgMusicOption.isSelected() == false) {
				backgroundMusic.stop();
			}
			else {
				startBackgroundMusic();
			}
		}
	}
	
	/**
	 * Main method for Main class and Mancala application
	 * @param args The arguments for the initialization of main
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
