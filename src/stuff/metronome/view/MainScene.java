package stuff.metronome.view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainScene extends Scene implements Observable {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	
	private List<Observer> observers;
	private StartStopButton button;
	private TempoControl tempoControl;
	
	public MainScene() {
		super(new AnchorPane(), WIDTH, HEIGHT);
		observers = new ArrayList<Observer>();
		AnchorPane pane = (AnchorPane) this.getRoot();
		button = new StartStopButton();
		tempoControl = new TempoControl();
		
		pane.getChildren().addAll(button, tempoControl);
		tempoControl.addObserver(new Observer() {
			public void update(Observable o) {
				notifyObservers();
			}
		});
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	public void setOnStartMetronome(EventHandler<ActionEvent> handler) {
		button.setOnStart(handler);
	}
	
	public void setOnStopMetronome(EventHandler<ActionEvent> handler) {
		button.setOnStop(handler);
	}
	
	public void setTempoControl(int min, int max, int value) {
		tempoControl.setLimits(min, max);
		tempoControl.setValue(value);
	}
	
	public int getTempoValue() {
		return tempoControl.getValue();
	}
}
