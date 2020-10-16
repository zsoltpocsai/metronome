package stuff.metronome;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TickSound {

	private static String resourcesPath = 
			TickSound.class.getResource("/resources/").getPath();
	public static String lowTickSoundFilePath = resourcesPath + "tick_low.wav";
	public static String highTickSoundFilePath = resourcesPath + "tick_high.wav";
	
	private Clip clip;
	
	public TickSound(File soundFile) throws LineUnavailableException {
		AudioInputStream stream;
		clip = AudioSystem.getClip();
		
		try {
			stream = AudioSystem.getAudioInputStream(soundFile);
			clip.open(stream);
		} catch (IOException | UnsupportedAudioFileException ex) {
			System.err.println("Audio file couldn't be loaded!");
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
