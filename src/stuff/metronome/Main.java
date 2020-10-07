package stuff.metronome;

public class Main {

	public static void main(String[] args) {
		Metronome metronome;
		
		try {
			metronome = new Metronome();
		} catch (Exception ex) {
			System.out.println("Metronome couldn't be loaded!");
			return;
		}
		
		metronome.setTempo(80);
		metronome.setBeatCount(6);
		metronome.start();
	}

}
