package src.picross;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class ViewScene {
	private Scene mainScene;
	private BorderPane centerPane = new BorderPane();
	private BorderPane bottomPane = new BorderPane();
	private TextArea textArea = new TextArea();
	private TextArea userText = new TextArea();
	private Button sendButton = new Button("Send");

	private void configure() {
		bottomPane.setCenter(userText);
		bottomPane.setBottom(sendButton);
		centerPane.setCenter(textArea);
		centerPane.setBottom(bottomPane);
		mainScene = new Scene(centerPane);
	}

	public ViewScene() {
		configure();
	}

	public Scene getScene() {
		return mainScene;
	}

	public Button getSendButton() {
		return sendButton;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public TextArea getUserText() {
		return userText;
	}
}
