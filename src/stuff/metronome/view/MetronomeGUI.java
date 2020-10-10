package stuff.metronome.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import stuff.metronome.Metronome;

public class MetronomeGUI extends Application {
	
	private Metronome metronome;
	private MainScene scene;
	
	public static void main(String[] args) {
		MetronomeGUI.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		metronome = new Metronome();
		scene = new MainScene();

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Metronome");
		primaryStage.show();
		
		setMetronomeControls();
	}
	
	private void setMetronomeControls() {
		
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
		
		scene.setTempoControl(Metronome.MIN_TEMPO, Metronome.MAX_TEMPO, Metronome.DEFAULT_TEMPO);
		scene.addObserver(new Observer() {
			public void update(Observable o) {
				MainScene scene = (MainScene) o;
				metronome.setTempo(scene.getTempoValue());
			}
		});
	}
}
