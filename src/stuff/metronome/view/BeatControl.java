package stuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class BeatControl extends HBox {
	
	private Label beatDisplay;
	private Button btnDecrease;
	private Button btnIncrease;
	private int beat;
	
	public BeatControl() {
		super(15.0);
		
		beatDisplay = new Label();
		btnDecrease = new Button("<");
		btnIncrease = new Button(">");
		
		btnDecrease.setOnAction((ActionEvent event) -> {
			beat -= 1;
			event.consume();
			fireBeatChangedEvent();
		});
		
		btnIncrease.setOnAction((ActionEvent event) -> {
			beat += 1;
			event.consume();
			fireBeatChangedEvent();
		});
		
		beatDisplay.setFont(new Font(18.0));
		btnDecrease.setFont(new Font(16.0));
		btnIncrease.setFont(new Font(16.0));
		btnDecrease.setPadding(new Insets(2.0, 6.0, 2.0, 6.0));
		btnIncrease.setPadding(new Insets(2.0, 6.0, 2.0, 6.0));
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(btnDecrease, beatDisplay, btnIncrease);
	}
	
	public void setBeat(int b) {
		beat = b;
		updateDisplay();
	}
	
	private void updateDisplay() {
		String beatText = String.valueOf(beat);
		beatDisplay.setText(beatText);
	}
	
	private void fireBeatChangedEvent() {
		MetronomeEvent event = new MetronomeEvent(MetronomeEvent.BEAT_CHANGED);
		event.setBeat(beat);
		this.fireEvent(event);
	}
}
