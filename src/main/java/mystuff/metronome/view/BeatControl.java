package mystuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class BeatControl extends GridPane {
	
	private Label beatDisplay;
	private Button btnDecrease;
	private Button btnIncrease;
	private int beat;
	private Label label;
	
	public BeatControl() {
		super();
		GridPane gridPane = this;
		
		beatDisplay = new Label();
		btnDecrease = new Button("<");
		btnIncrease = new Button(">");
		label = new Label("beat count");
		
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
		
		beatDisplay.setFont(new Font(26.0));
		beatDisplay.setPadding(new Insets(2.0, 5.0, 2.0, 5.0));
		beatDisplay.setTextAlignment(TextAlignment.CENTER);
		btnDecrease.setFont(new Font(16.0));
		btnIncrease.setFont(new Font(16.0));
		label.setFont(new Font(14.0));
		label.setPadding(new Insets(2.0, 5.0, 2.0, 5.0));
		label.setTextAlignment(TextAlignment.CENTER);
		btnDecrease.setPadding(new Insets(2.0, 6.0, 2.0, 6.0));
		btnIncrease.setPadding(new Insets(2.0, 6.0, 2.0, 6.0));
		
		GridPane.setConstraints(label, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(btnDecrease, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
		GridPane.setConstraints(btnIncrease, 2, 1, 1, 1, HPos.LEFT, VPos.CENTER);
		GridPane.setConstraints(beatDisplay, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		
		ColumnConstraints cc1 = new ColumnConstraints();
		ColumnConstraints cc2 = new ColumnConstraints();
		ColumnConstraints cc3 = new ColumnConstraints();
		cc1.setPercentWidth(35);
		cc2.setPercentWidth(30);
		cc3.setPercentWidth(35);
		
		gridPane.getColumnConstraints().addAll(cc1, cc2, cc3);
		//gridPane.setGridLinesVisible(true);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(5.0);
		gridPane.getChildren().addAll(label, btnDecrease, btnIncrease, beatDisplay);
	}
	
	public void setBeat(int b) {
		beat = b;
		updateDisplay();
	}
	
	public void increaseBeat() {
		this.setBeat(this.beat + 1);
	}
	
	public void decreaseBeat() {
		
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
