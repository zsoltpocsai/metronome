package mystuff.metronome.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainScene extends Scene {
	
	private static final int WIDTH = 450;
	private static final int HEIGHT = 250;
	
	private StartStopButton button;
	private TempoControl tempoControl;
	private BeatControl beatControl;
	private TempoDisplay tempoDisplay;
	
	public MainScene() {
		super(new AnchorPane(), WIDTH, HEIGHT);
		
		AnchorPane pane = (AnchorPane) this.getRoot();
		button = new StartStopButton();
		tempoControl = new TempoControl();
		beatControl = new BeatControl();
		tempoDisplay = new TempoDisplay();
		
		tempoControl.setWidth(WIDTH * 3 / 4);
		
		pane.getChildren().addAll(button, tempoControl, beatControl, tempoDisplay);
		pane.setPadding(new Insets(10.0));
		
		setListeners();
	}
	
	public void setTempoControl(int min, int max, int value) {
		tempoControl.setLimits(min, max);
		tempoControl.setValue(value);
		tempoDisplay.setValue(value);
	}
	
	public void setBeatControl(int beat) {
		beatControl.setBeat(beat);
	}
	
	public void setElementsPosition() {
		AnchorPane.setTopAnchor(button, (double) HEIGHT * 2 / 5);
		AnchorPane.setLeftAnchor(button, (WIDTH - button.getWidth()) / 2);
		
		AnchorPane.setTopAnchor(tempoControl, (double) HEIGHT * 3 / 4);
		AnchorPane.setLeftAnchor(tempoControl, (WIDTH - tempoControl.getWidth()) / 2);
		
		AnchorPane.setTopAnchor(beatControl, 0.0);
		AnchorPane.setLeftAnchor(beatControl, (double) WIDTH / 12);
		
		AnchorPane.setTopAnchor(tempoDisplay, 0.0);
		AnchorPane.setLeftAnchor(tempoDisplay, (WIDTH - tempoDisplay.getWidth()) / 2);
	}
	
	private void setListeners() {
		this.addEventHandler(MetronomeEvent.TEMPO_CHANGED,
			(MetronomeEvent event) -> {
				tempoDisplay.setValue(event.getTempo());
			}
		);
	}
}
