package mystuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class StartStopButton extends Button {
	
	private boolean isSetOnStart;
	
	public StartStopButton() {
		super();
		isSetOnStart = true;
		this.setText("Start");
		
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MetronomeEvent newEvent;
				event.consume();
				
				if (isSetOnStart) {
					//onStart.handle(e);
					newEvent = new MetronomeEvent(MetronomeEvent.METRONOME_START);
				} else {
					//onStop.handle(e);
					newEvent = new MetronomeEvent(MetronomeEvent.METRONOME_STOP);
				}
				
				Event.fireEvent(StartStopButton.this, newEvent);
				updateState();
			}
		});
		
		this.setPadding(new Insets(10.0, 30.0, 10.0, 30.0));
		this.setFont(new Font(26.0));
	}
	
	private void updateState() {
		isSetOnStart = !isSetOnStart;
		if (isSetOnStart) {
			this.setText("Start");
		} else {
			this.setText("Stop");
		}
	}
}
