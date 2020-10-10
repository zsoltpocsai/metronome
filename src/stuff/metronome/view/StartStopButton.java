package stuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class StartStopButton extends Button {
	
	private boolean isSetOnStart;
	private EventHandler<ActionEvent> onStart;
	private EventHandler<ActionEvent> onStop;
	
	public StartStopButton() {
		super();
		isSetOnStart = true;
		this.setText("Start");
		onStart = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {}
		};
		onStop = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {}
		};
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (isSetOnStart) {
					onStart.handle(e);
				} else {
					onStop.handle(e);
				}
				isSetOnStart = !isSetOnStart;
				updateState();
			}
		});
		this.setPadding(new Insets(10.0, 30.0, 10.0, 30.0));
		this.setFont(new Font(26.0));
	}
	
	public void setOnStart(EventHandler<ActionEvent> handler) {
		this.onStart = handler;
	}
	
	public void setOnStop(EventHandler<ActionEvent> handler) {
		this.onStop = handler;
	}
	
	private void updateState() {
		if (isSetOnStart) {
			this.setText("Start");
		} else {
			this.setText("Stop");
		}
	}
}
