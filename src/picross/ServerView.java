package src.picross;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import view.ViewScene;

public class ServerView extends Application implements ViewInterface {
	private Server server;
	private ViewScene viewScene = new ViewScene();
	private TextArea textArea = viewScene.getTextArea();
	private TextArea userText = viewScene.getUserText();
	private static int PORT = 3000;

	private void startServer(String arg) {
		int portNumber;
		if (arg == null) {
			System.out.println("java <port number>");
			portNumber = PORT;
		} else {
			portNumber = Integer.parseInt(arg);
		}
		System.out.println("Starting Server Thread on port " + portNumber);
		Thread servDaemon = new Thread(new Server(this, portNumber));
		servDaemon.start();
	}

	void writeln() {
		server.writeln(userText.getText());
		userText.clear();
	}

	@Override
	public void start(Stage stage) throws Exception {
		viewScene.getSendButton().setOnAction((e) -> {
			writeln();
		});
		stage.setScene(viewScene.getScene());
		stage.sizeToScene();
		startServer("10001");
		stage.show();

	}

	@Override
	public void println(String text) {
		textArea.appendText(text + "\n");
	}

	/**
	 * Main method.
	 * 
	 * @param args Param arguments.
	 */
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void setServer(Server _server) {
		// TODO Auto-generated method stub
		server = _server;

	}

}
