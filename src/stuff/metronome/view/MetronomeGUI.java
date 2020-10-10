package stuff.metronome.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class MetronomeGUI extends Application {
	
	public static void main(String[] args) {
		MetronomeGUI.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		MainScene scene = new MainScene();
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Metronome");
		primaryStage.show();
	}
}
