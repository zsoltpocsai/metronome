package stuff.metronome.view;

import javafx.application.Application;
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
		
		scene.setElementsPosition();
		this.setMetronomeControls();
	}
	
	private void setMetronomeControls() {
		
		scene.addEventHandler(MetronomeEvent.METRONOME_START, 
				(MetronomeEvent event) -> { metronome.start(); });
		
		scene.addEventHandler(MetronomeEvent.METRONOME_STOP, 
				(MetronomeEvent event) -> { metronome.stop(); });
		
		scene.setTempoControl(Metronome.MIN_TEMPO, Metronome.MAX_TEMPO, 
				Metronome.DEFAULT_TEMPO);
		
		scene.addEventHandler(MetronomeEvent.TEMPO_CHANGED, 
			(MetronomeEvent event) -> { 
				metronome.setTempo(event.getTempo()); 
			}
		);
	}
}
