package mystuff.metronome.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyControl {
	
	private KeyControl() {}
	
	public static void setStartStopButton(StartStopButton btn, Scene scene) {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
			if (event.getCode() == KeyCode.SPACE) {
				event.consume();
				btn.fire();
			}
		});
	}
	
	public static void setTempoControl(TempoControl tc, Scene scene) {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
			if (event.getCode() == KeyCode.ADD) {
				tc.increaseTempo();
			} else if (event.getCode() == KeyCode.SUBTRACT) {
				tc.decreaseTempo();
			}
		});
	}
}
