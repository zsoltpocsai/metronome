package mystuff.metronome.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TempoControl extends HBox {
	
	private Slider slider;
	
	public TempoControl() {
		super(10.0);
		
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

		this.getChildren().addAll(slider);
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
