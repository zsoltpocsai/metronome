package stuff.metronome.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TempoControl extends HBox {
	
	private Slider slider;
	private Label label;
	
	public TempoControl() {
		super(10.0);
		
		slider = new Slider();
		label = new Label();
		
		slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				MetronomeEvent newEvent;
				event.consume();
				newEvent = new MetronomeEvent(MetronomeEvent.TEMPO_CHANGED);
				newEvent.setTempo(getValue());
				Event.fireEvent(TempoControl.this, newEvent);
				updateState();
			}
		});
		
		slider.setMajorTickUnit(5.0);
		slider.setMinorTickCount(0);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setSnapToTicks(true);

		this.getChildren().addAll(slider, label);
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
		updateState();
	}
	
	private void updateState() {
		String tempoText = Integer.toString((int)slider.getValue()) + " bpm";
		label.setText(tempoText);
	}
}
