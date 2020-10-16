package mystuff.metronome;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;

public class Metronome {

	public static final int DEFAULT_TEMPO = 60;
	public static final int MAX_TEMPO = 200;
	public static final int MIN_TEMPO = 40;
	public static final int DEFAULT_BEAT = 4;
	public static final int MAX_BEAT = 12;
	public static final int MIN_BEAT = 1;
	
	private int tempo;
	private boolean ticking;
	private Timer timer;
	private Tick tick;
	
	public Metronome() {
		tempo = DEFAULT_TEMPO;
		ticking = false;
		timer = new Timer();
		
		try {
			tick = new Tick();
			tick.setBeatCount(DEFAULT_BEAT);
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		}
	}
	
	public void start() {
		timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				tick.play();
			}
		}, 0, tempoToMillisec(tempo));
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
		restart();
	}
	
	public int getBeatCount() {
		return tick.getBeatCount();
	}
	
	public void setBeatCount(int bc) {
		tick.setBeatCount(getLimitedValue(bc, MAX_BEAT, MIN_BEAT));
	}
	
	public void addTickListener(TickListener listener) {
		tick.addTickListener(listener);
	}
	
	private void restart() {
		if (ticking) {
			stop();
			start();
		}
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
