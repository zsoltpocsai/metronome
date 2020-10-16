package stuff.metronome;

import java.io.File;

import javax.sound.sampled.LineUnavailableException;

public class Tick {
	
	private TickSound lowTickSound;
	private TickSound highTickSound;
	private int beatCount;
	private int currentBeat;
	
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
	
	private void playInBar() {
		if (currentBeat == 1) {
			highTickSound.play();
		} else {
			lowTickSound.play();
		}
		advanceBeat();
	}
	
	private void playSingle() {
		lowTickSound.play();
	}
	
	private void advanceBeat() {
		if (currentBeat >= beatCount) {
			currentBeat = 1;
		} else {
			currentBeat += 1;
		}
	}
}
