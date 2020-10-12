package stuff.metronome;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Metronome {

	public static final int DEFAULT_TEMPO = 60;
	public static final int MAX_TEMPO = 208;
	public static final int MIN_TEMPO = 40;
	public static final int DEFAULT_BEAT = 4;
	public static final int MAX_BEAT = 12;
	public static final int MIN_BEAT = 1;
	
	private int tempo;
	private int beatCount;
	private boolean ticking;
	private Timer timer;
	TickSound lowTickSound;
	TickSound highTickSound;
	
	public Metronome() {
		tempo = DEFAULT_TEMPO;
		beatCount = DEFAULT_BEAT;
		ticking = false;
		
		try {
			File lowTickFile = new File(this.getClass().getResource("/resources/tick_low.wav").getPath());
			File highTickFile = new File(this.getClass().getResource("/resources/tick_high.wav").getPath());
			
			lowTickSound = new TickSound(lowTickFile);
			highTickSound = new TickSound(highTickFile);
			
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (UnsupportedAudioFileException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public void start() {
		if (lowTickSound != null && highTickSound != null) {
			timer = new Timer();
			Tick tick = new Tick(lowTickSound, highTickSound);
			tick.setBeatCount(beatCount);
			timer.scheduleAtFixedRate(tick, 0, tempoToMillisec(tempo));
		}
		ticking = true;
	}
	
	public void stop() {
		timer.cancel();
		ticking = false;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public void setTempo(int t) {
		tempo = getLimitedValue(t, MAX_TEMPO, MIN_TEMPO);
		if (ticking) {
			stop();
			start();
		}
	}
	
	public void setBeatCount(int bc) {
		beatCount = getLimitedValue(bc, MAX_BEAT, MIN_BEAT);
	}
	
	private int getLimitedValue(int value, int max, int min) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		} else {
			return value;
		}
	}
	
	private long tempoToMillisec(int tempo) {
		// tempo is beat per minute (bpm)
		return 60000 / tempo;
	}
}
