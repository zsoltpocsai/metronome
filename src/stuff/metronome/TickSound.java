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

	Clip clip;
	
	public TickSound(File soundFile) throws IOException, 
			UnsupportedAudioFileException, LineUnavailableException {
		
		AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(stream);
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
