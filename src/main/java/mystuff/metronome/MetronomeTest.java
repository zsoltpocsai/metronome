package mystuff.metronome;

public class MetronomeTest {

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
		
		metronome.addTickListener(new TickListener() {
			@Override
			public void update(TickEvent event) {
				System.out.println("Tick event: " + event.getType());
			}
		});
	}

}
