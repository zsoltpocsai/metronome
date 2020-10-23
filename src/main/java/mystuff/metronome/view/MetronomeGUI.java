package mystuff.metronome.view;

import javafx.stage.WindowEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import mystuff.metronome.Metronome;

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
		
		primaryStage.setOnCloseRequest((WindowEvent event) -> {
			metronome.stop();
		});
		
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
				int newTempo = event.getTempo();
				if (newTempo != metronome.getTempo()) {
					metronome.setTempo(newTempo);
				}
			}
		);
		
		scene.addEventHandler(MetronomeEvent.BEAT_CHANGED,
			(MetronomeEvent event) -> {
				metronome.setBeatCount(event.getBeat());
				scene.setBeatControl(metronome.getBeatCount());
			}
		);
		
		scene.setBeatControl(Metronome.DEFAULT_BEAT);
	}
}
