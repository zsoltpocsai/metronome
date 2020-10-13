package stuff.metronome.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainScene extends Scene {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 250;
	
	private StartStopButton button;
	private TempoControl tempoControl;
	private BeatControl beatControl;
	
	public MainScene() {
		super(new AnchorPane(), WIDTH, HEIGHT);

		AnchorPane pane = (AnchorPane) this.getRoot();
		button = new StartStopButton();
		tempoControl = new TempoControl();
		beatControl = new BeatControl();
		
		tempoControl.setWidth(WIDTH * 2 / 3);
		
		pane.getChildren().addAll(button, tempoControl, beatControl);
	}
	
	public void setTempoControl(int min, int max, int value) {
		tempoControl.setLimits(min, max);
		tempoControl.setValue(value);
	}
	
	public void setBeatControl(int beat) {
		beatControl.setBeat(beat);
	}
	
	public void setElementsPosition() {
		AnchorPane.setTopAnchor(button, (double) HEIGHT * 2 / 3);
		AnchorPane.setTopAnchor(tempoControl, (double) HEIGHT / 3);
		AnchorPane.setLeftAnchor(button, (WIDTH - button.getWidth()) / 2);
		AnchorPane.setLeftAnchor(tempoControl, (WIDTH - tempoControl.getWidth()) / 2);
		AnchorPane.setTopAnchor(beatControl, (double) HEIGHT / 8);
		AnchorPane.setLeftAnchor(beatControl, (WIDTH - beatControl.getWidth()) / 2);
	}
}