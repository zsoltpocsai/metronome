package mystuff.metronome.view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class TempoDisplay extends GridPane {

	private Label tempo;
	private Label unit;
	private Label label;
	private Label tempoName;
	private int value;
	
	public TempoDisplay() {
		super();
		GridPane gridPane = this;
		
		HBox hbox = new HBox();
		tempo = new Label();
		unit = new Label("bpm");
		label = new Label("tempo");
		tempoName = new Label();
		
		hbox.getChildren().addAll(label, tempoName);
		hbox.setSpacing(10.0);
		hbox.setAlignment(Pos.CENTER);
		
		GridPane.setConstraints(hbox, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(tempo, 0, 1, 1, 1, HPos.RIGHT, VPos.BASELINE);
		GridPane.setConstraints(unit, 1, 1, 1, 1, HPos.LEFT, VPos.BASELINE);
		
		gridPane.setHgap(5.0);
		gridPane.setVgap(5.0);
		
		gridPane.setAlignment(Pos.CENTER);
		//gridPane.setGridLinesVisible(true);
		gridPane.getChildren().addAll(hbox, tempo, unit);
		
		ColumnConstraints cc1 = new ColumnConstraints();
		ColumnConstraints cc2 = new ColumnConstraints();
		cc1.setMinWidth(80.0);
		cc2.setMinWidth(60.0);
		gridPane.getColumnConstraints().addAll(cc1, cc2);
		
		tempo.setFont(new Font(37.0));
		unit.setFont(new Font(14.0));
		label.setFont(new Font(14.0));
		tempoName.setFont(new Font(14.0));
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		update();
	}
	
	private void update() {
		tempo.setText(String.valueOf(value));
		tempoName.setText(getTempoName(value).toUpperCase());
	}
	
	private String getTempoName(int tempo) {
		String name = "";
		
		if (tempo <= 60) {
			name = "Largo";
		} else if (tempo <= 66) {
			name = "Larghetto";
		} else if (tempo <= 76) {
			name = "Adagio";
		} else if (tempo <= 108) {
			name = "Andante";
		} else if (tempo <= 120) {
			name = "Moderato";
		} else if (tempo <= 168) {
			name = "Allegro";
		} else {
			name = "Presto";
		}
		
		return name;
	}
}
