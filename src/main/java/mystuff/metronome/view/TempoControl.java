package mystuff.metronome.view;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class TempoControl extends GridPane {
	
	private static final int STEP = 1;
	private Slider slider;
	
	public TempoControl() {
		super();
		
		slider = new Slider();
		
		slider.setOnMouseClicked((MouseEvent event) -> {
			event.consume();
			this.setValue(this.getValue());
		});
		
		slider.setMajorTickUnit(5.0);
		slider.setMinorTickCount(0);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setSnapToTicks(true);

		this.add(slider, 0, 0);
		this.setAlignment(Pos.CENTER);
	}
	
	public void setWidth(int w) {
		slider.setPrefWidth(w);
	}
	
	public int getValue() {
		return (int) slider.getValue();
	}
	
	public void setLimits(int min, int max) {
		slider.setMin(min);
		slider.setMax(max);
	}
	
	public void setValue(int value) {
		slider.setValue(value);
		fireTempoChangedEvent();
	}
	
	public void increaseTempo() {
		this.setValue(this.getValue() + STEP);
	}
	
	public void decreaseTempo() {
		this.setValue(this.getValue() - STEP);
	}
	
	private void fireTempoChangedEvent() {
		MetronomeEvent event = new MetronomeEvent(MetronomeEvent.TEMPO_CHANGED);
		event.setTempo(this.getValue());
		Event.fireEvent(this, event);
	}
}
