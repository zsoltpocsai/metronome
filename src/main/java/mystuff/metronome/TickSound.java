package mystuff.metronome;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TickSound {

	private static String soundSourcesPath;
	public static String lowTickSoundFilePath;
	public static String highTickSoundFilePath;
	
	static {
		
		try {
			Path path = Paths.get(TickSound.class.getProtectionDomain().
					getCodeSource().getLocation().toURI());
			
			if (path.endsWith("bin")) {
				soundSourcesPath = path.toString() + "/sound";
			} else {
				soundSourcesPath = path.getParent().toString() + "/sound";
			}
		} catch(Exception ex) {
			System.out.println("TickSound: resource couldn't be loaded!");
			soundSourcesPath = ".";
		}
		
		lowTickSoundFilePath = soundSourcesPath + "/tick_low.wav";
		highTickSoundFilePath = soundSourcesPath + "/tick_high.wav";
	}
	
	private Clip clip;
	
	public TickSound(String soundFilePath) throws LineUnavailableException {
		AudioInputStream stream;
		clip = AudioSystem.getClip();
		
		try {
			File soundFile = new File(soundFilePath);
			stream = AudioSystem.getAudioInputStream(soundFile);
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
