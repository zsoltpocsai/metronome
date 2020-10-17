package mystuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BeatControl extends VBox {
	
	private Label beatDisplay;
	private Button btnDecrease;
	private Button btnIncrease;
	private int beat;
	private Label label;
	
	public BeatControl() {
		super();
		HBox hbox = new HBox();
		
		beatDisplay = new Label();
		btnDecrease = new Button("<");
		btnIncrease = new Button(">");
		label = new Label();
		
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
		
		label.setText("beat count");
		
		beatDisplay.setFont(new Font(18.0));
		btnDecrease.setFont(new Font(16.0));
		btnIncrease.setFont(new Font(16.0));
		label.setFont(new Font(14.0));
		btnDecrease.setPadding(new Insets(2.0, 6.0, 2.0, 6.0));
		btnIncrease.setPadding(new Insets(2.0, 6.0, 2.0, 6.0));
		
		hbox.getChildren().addAll(btnDecrease, beatDisplay, btnIncrease);
		hbox.setSpacing(10.0);
		hbox.setAlignment(Pos.CENTER);
		
		this.setSpacing(10.0);
		this.setAlignment(Pos.BOTTOM_LEFT);
		this.getChildren().addAll(label, hbox);
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
