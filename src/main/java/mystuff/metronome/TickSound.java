package mystuff.metronome;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TickSound {

	public static String lowTickSoundFilePath = "/sound/tick_low.wav";
	public static String highTickSoundFilePath = "/sound/tick_high.wav";
	
	private Clip clip;
	
	public TickSound(String soundFilePath) throws LineUnavailableException {
		AudioInputStream stream;
		clip = AudioSystem.getClip();
		
		try {
			InputStream inputStream = this.getClass().getResourceAsStream(soundFilePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			stream = AudioSystem.getAudioInputStream(bufferedInputStream);
			clip.open(stream);
		} catch (IOException | UnsupportedAudioFileException ex) {
			System.out.println("Audio file couldn't be loaded!");
			ex.printStackTrace();
		}
		
		clip.addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.setFramePosition(0);
				}
			}
		});
	}
	
	public void play() {
		clip.start();
	}
}
