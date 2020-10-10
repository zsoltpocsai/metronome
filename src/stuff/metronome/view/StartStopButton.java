package stuff.metronome.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StartStopButton extends Button {
	
	public StartStopButton() {
		super();
		this.setText("Start");
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Action event: " + e);
			}
		});
	}
	
	public void setOnStart() {
		
	}
}
