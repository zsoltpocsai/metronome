package mystuff.metronome.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class TempoControl extends GridPane {
	
	private Slider slider;
	
	public TempoControl() {
		super();
		
		slider = new Slider();
		
		slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				MetronomeEvent newEvent;
				event.consume();
				newEvent = new MetronomeEvent(MetronomeEvent.TEMPO_CHANGED);
				newEvent.setTempo(getValue());
				Event.fireEvent(TempoControl.this, newEvent);
			}
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
	}
}
