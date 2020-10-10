package stuff.metronome.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import stuff.metronome.Metronome;

public class MetronomeGUI extends Application {
	
	private Metronome metronome;
	
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
		
		setMetronome(new Metronome());
		
		scene.setOnStartMetronome(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				metronome.start();
			}
		});
		
		scene.setOnStopMetronome(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				metronome.stop();
			}
		});
	}
	
	private void setMetronome(Metronome m) {
		metronome = m;
	}
}
