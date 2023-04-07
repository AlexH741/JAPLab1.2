package src.picross;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import view.ViewScene;

public class ClientView extends Application {

	private Client client;
	private ViewScene viewScene = new ViewScene();
	private TextArea textArea = viewScene.getTextArea();
	private TextArea userText = viewScene.getUserText();
	private static int PORT = 3000;

	private void startClient(String arg) {
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

}
