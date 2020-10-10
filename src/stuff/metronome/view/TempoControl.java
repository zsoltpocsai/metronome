package stuff.metronome.view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TempoControl extends HBox implements Observable {
	
	private Slider slider;
	private Label label;
	private List<Observer> observers;
	
	public TempoControl() {
		super(10.0);
		observers = new ArrayList<Observer>();
		
		slider = new Slider();
		slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				updateState();
				notifyObservers();
			}
		});

		label = new Label();
		
		this.getChildren().addAll(slider, label);
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
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
	
	private void updateState() {
		String tempoText = Double.toString(slider.getValue()) + " bpm";
		label.setText(tempoText);
	}
}
