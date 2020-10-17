package mystuff.metronome.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TempoDisplay extends VBox {

	private Label tempoLabel;
	private Label unitLabel;
	private Label label;
	private int value;
	
	public TempoDisplay() {
		super();
		HBox hbox = new HBox();
		
		tempoLabel = new Label();
		unitLabel = new Label();
		label = new Label();
		
		hbox.getChildren().addAll(tempoLabel, unitLabel);
		hbox.setSpacing(5.0);
		hbox.setAlignment(Pos.BASELINE_LEFT);
		
		this.getChildren().addAll(label, hbox);
		this.setSpacing(2.0);
		this.setAlignment(Pos.BOTTOM_LEFT);
		this.setPrefWidth(100.0);
		
		tempoLabel.setFont(new Font(32.0));
		unitLabel.setFont(new Font(14.0));
		label.setFont(new Font(14.0));
		
		label.setText("tempo");
		unitLabel.setText("bpm");
		this.setValue(0);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		update();
	}
	
	private void update() {
		tempoLabel.setText(String.valueOf(value));
	}
}
