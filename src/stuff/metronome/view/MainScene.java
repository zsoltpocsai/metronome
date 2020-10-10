package stuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainScene extends Scene {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	
	private StartStopButton button;
	private Label label;
	
	public MainScene() {
		super(new AnchorPane(), WIDTH, HEIGHT);
		AnchorPane pane = (AnchorPane) this.getRoot();
		button = new StartStopButton();
		label = new Label();
		
		pane.getChildren().add(button);
	}
	
	public void setOnStartMetronome(EventHandler<ActionEvent> handler) {
		button.setOnStart(handler);
	}
	
	public void setOnStopMetronome(EventHandler<ActionEvent> handler) {
		button.setOnStop(handler);
	}
}
