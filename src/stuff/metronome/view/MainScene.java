package stuff.metronome.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainScene extends Scene {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	
	private StartStopButton button;
	private TempoControl tempoControl;
	
	public MainScene() {
		super(new AnchorPane(), WIDTH, HEIGHT);

		AnchorPane pane = (AnchorPane) this.getRoot();
		button = new StartStopButton();
		tempoControl = new TempoControl();
		
		pane.getChildren().addAll(button, tempoControl);
	}
	
	public void setTempoControl(int min, int max, int value) {
		tempoControl.setLimits(min, max);
		tempoControl.setValue(value);
	}
	
	public void setElementsPosition() {
		AnchorPane.setTopAnchor(button, (double) HEIGHT * 2 / 3);
		AnchorPane.setTopAnchor(tempoControl, (double) HEIGHT / 2);
		AnchorPane.setLeftAnchor(button, (WIDTH - button.getWidth()) / 2);
		AnchorPane.setLeftAnchor(tempoControl, (WIDTH - tempoControl.getWidth()) / 2);
	}
}
