package application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
 * @description Class declaration of RuleScreen
 * @duedate		05/03/2023
 * @hwnumber	Final
 * @extends 	BorderPane
 */
public class RuleScreen extends BorderPane {
	/** The GridPane containing the text for the rules */
	private GridPane rules;
	
	/** The GridPane containing the title for the page */
	private GridPane titlePane;
	
	/** The text for the title */
	private Text mancalaTitle;
	
	/** The text containing the rules */
	private Text rulesText;
	
	/** The main menu button for returning to the main menu */
	private Button mainMenu;
	
	/**
	 * Adds the components needed for the Rules Screen
	 * @param root The BorderPane containing all of the buttons and other objects
	 * @throws IOException if File not found or byte's invalid
	 */
	public RuleScreen(Button menuButton) throws IOException {
		// Initialize variables
		mainMenu = menuButton;
		
		rules = new GridPane();
		titlePane = new GridPane();
		
		mancalaTitle = new Text("Mancala Rules");
		
		rulesText = new Text(readFile("Rules.txt", StandardCharsets.UTF_8));
		
		// Set the scene styles and add components
		setComponentStyles();
		addComponentsToPanes();
		addComponentsToScene();
	}
	
	/**
	 * Adds the components to the scene
	 */
	private void addComponentsToScene() {
		this.setTop(titlePane);
		this.setCenter(rules);
		this.setBottom(mainMenu);
	}
	
	/**
	 * Add the components to their panes
	 */
	private void addComponentsToPanes() {
		// Adding the MancalaTitle Text to the TopPane GridPane
		titlePane.add(mancalaTitle, 0, 0);

		// Add the rules text to the gridpane
		rules.add(rulesText, 0, 0);
	}
	
	/**
	 * Set the styles for all components in scene
	 */
	private void setComponentStyles() {
		setTitlePaneStyle();
		setMenuButtonStyle();
		setRulePaneStyle();
		setRuleTextStyle();
		setTitleTextStyle();
	}
	
	/**
	 * Set the title pane's style
	 */
	private void setTitlePaneStyle() {
		titlePane.setAlignment(Pos.CENTER);
		titlePane.setTranslateY(10);
	}
	
	/**
	 * Set the menu button style
	 */
	private void setMenuButtonStyle() {
		mainMenu.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		mainMenu.setStyle("-fx-text-fill: black;");
		mainMenu.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		mainMenu.setTranslateX(340);
		mainMenu.setTranslateY(-20);
	}
	
	/**
	 * Set the rule pane style
	 */
	private void setRulePaneStyle() {
		rules.setStyle("-fx-background-color: white");
		rules.setMaxWidth(740);
		rules.setVgap(40);
		rules.setMaxHeight(450);
	}
	
	/**
	 * Set the style for the rules text
	 */
	private void setRuleTextStyle() {
		rulesText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
		rulesText.setWrappingWidth(710);
		rulesText.setTranslateX(15);
	}
	
	/**
	 * Set the title style
	 */
	private void setTitleTextStyle() {
		mancalaTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
	}
	
	/**
	 * Reads in input from a file as a String
	 * @param path The path of the file
	 * @param encoding The type of encoding used for identifying text
	 * @return The String format of the read in text
	 * @throws IOException if File not found or byte's invalid
	 */
	private String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
