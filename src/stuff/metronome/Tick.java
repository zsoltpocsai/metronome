package stuff.metronome;

import java.util.TimerTask;

public class Tick extends TimerTask {
	
	private TickSound lowTickSound;
	private TickSound highTickSound;
	private int beatCount;
	private int currentBeat;
	
	public Tick(TickSound low, TickSound high) {
		lowTickSound = low;
		highTickSound = high;
		beatCount = 1;
		currentBeat = 1;
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
	
	@Override
	public void run() {
		if (beatCount > 1) {
			playInBar();
		} else {
			lowTickSound.play();
		}
	}
	
	private void playInBar() {
		if (currentBeat == 1) {
			highTickSound.play();
		} else {
			lowTickSound.play();
		}
		advanceBeat();
	}
	
	private void advanceBeat() {
		if (currentBeat == beatCount) {
			currentBeat = 1;
		} else {
			currentBeat += 1;
		}
	}
}
