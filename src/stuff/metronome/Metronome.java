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
	private Tick tick;
	private Timer timer;
	
	public Metronome() throws Exception {
		tempo = DEFAULT_TEMPO;
		timer = new Timer();
		
		try {
			File lowTickFile = new File(this.getClass().getResource("/resources/tick_low.wav").getPath());
			File highTickFile = new File(this.getClass().getResource("/resources/tick_high.wav").getPath());
			
			TickSound lowTickSound = new TickSound(lowTickFile);
			TickSound highTickSound = new TickSound(highTickFile);
			
			tick = new Tick(lowTickSound, highTickSound);
			
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
			ex.printStackTrace();
			throw new Exception();
		}
		
		setBeatCount(DEFAULT_BEAT);
	}
	
	public void start() {
		timer.scheduleAtFixedRate(tick, 0, tempoToMillisec(tempo));
	}
	
	public void stop() {
		timer.cancel();
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public void setTempo(int t) {
		tempo = getLimitedValue(t, MAX_TEMPO, MIN_TEMPO);
	}
	
	public void setBeatCount(int bc) {
		tick.setBeatCount(getLimitedValue(bc, MAX_BEAT, MIN_BEAT));
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
