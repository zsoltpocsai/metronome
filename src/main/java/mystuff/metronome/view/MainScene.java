package mystuff.metronome.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class MainScene extends Scene {
	
	private static final int WIDTH = 450;
	private static final int HEIGHT = 250;
	
	private StartStopButton button;
	private TempoControl tempoControl;
	private BeatControl beatControl;
	private TempoDisplay tempoDisplay;
	private ImageDisplay tempoChartImage;
	private ImageDisplay controlsImage;
	
	public MainScene() {
		super(new GridPane(), WIDTH, HEIGHT);
		
		button = new StartStopButton();
		tempoControl = new TempoControl();
		beatControl = new BeatControl();
		tempoDisplay = new TempoDisplay();
		tempoChartImage = new ImageDisplay(ImageDisplay.TEMP_CHART_PATH);
		controlsImage = new ImageDisplay(ImageDisplay.CONTROLS_PATH);
		
		tempoControl.setWidth(WIDTH * 3 / 4);
		
		KeyControl.setStartStopButton(button, this);
		KeyControl.setTempoControl(tempoControl, this);
		
		setGridLayout();
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
	
	private void setListeners() {
		this.addEventHandler(MetronomeEvent.TEMPO_CHANGED,
			(MetronomeEvent event) -> {
				tempoDisplay.setValue(event.getTempo());
			}
		);
	}
	
	private void setGridLayout() {
		GridPane pane = (GridPane) this.getRoot();
		
		pane.add(beatControl, 0, 0);
		pane.add(tempoDisplay, 1, 0);
		pane.add(controlsImage, 0, 1);
		pane.add(button, 1, 1);
		pane.add(tempoChartImage, 2, 0, 1, 2);
		pane.add(tempoControl, 0, 2, 3, 1);
		
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(10.0));
		//pane.setGridLinesVisible(true);
		pane.setVgap(10.0);
		pane.setHgap(10.0);
		
		pane.getRowConstraints().addAll(new RowConstraints(), 
				new RowConstraints(), new RowConstraints());
		
		pane.getColumnConstraints().addAll(new ColumnConstraints(), 
				new ColumnConstraints(), new ColumnConstraints());
		
		for (RowConstraints rc : pane.getRowConstraints()) {
			rc.setValignment(VPos.CENTER);
			rc.setVgrow(Priority.ALWAYS);
		}
		
		for (int i = 0; i < pane.getColumnConstraints().size(); i++) {
			ColumnConstraints cc = pane.getColumnConstraints().get(i);
			cc.setHalignment(HPos.CENTER);
			cc.setHgrow(Priority.ALWAYS);
			if (i == 1) {
				cc.setPercentWidth(40);
			}
		}
		
		pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}
}
