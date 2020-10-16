package stuff.metronome;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

public class Tick {
	
	private TickSound lowTickSound;
	private TickSound highTickSound;
	private int beatCount;
	private int currentBeat;
	private List<TickListener> tickListeners = new ArrayList<TickListener>();
	
	public Tick() throws LineUnavailableException {
		beatCount = 1;
		currentBeat = 1;
		lowTickSound = new TickSound(new File(TickSound.lowTickSoundFilePath));
		highTickSound = new TickSound(new File(TickSound.highTickSoundFilePath));
	}

	public int getBeatCount() {
		return beatCount;
	}
	
	public void setBeatCount(int bc) {
		beatCount = bc;
	}
	
	public int getCurrentBeat() {
		return currentBeat;
	}
	
	public void play() {
		if (beatCount > 1) {
			playInBar();
		} else {
			playSingle();
		}
	}
	
	public void addTickListener(TickListener listener) {
		tickListeners.add(listener);
	}
	
	private void playInBar() {
		if (currentBeat == 1) {
			highTickSound.play();
			sendTickEvent(TickEvent.Type.FIRST_BEAT);
		} else {
			playSingle();
		}
		advanceBeat();
	}
	
	private void playSingle() {
		lowTickSound.play();
		sendTickEvent(TickEvent.Type.ANY_BEAT);
	}
	
	private void advanceBeat() {
		if (currentBeat >= beatCount) {
			currentBeat = 1;
		} else {
			currentBeat += 1;
		}
	}
	
	private void sendTickEvent(TickEvent.Type type) {
		TickEvent event = new TickEvent(type, this);
		for (TickListener listener : tickListeners) {
			listener.update(event);
		}
	}
}
