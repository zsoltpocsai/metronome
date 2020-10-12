package stuff.metronome.view;

import javafx.event.Event;
import javafx.event.EventType;

@SuppressWarnings("serial")
public class MetronomeEvent extends Event {

	public static EventType<MetronomeEvent> METRONOME_START;
	public static EventType<MetronomeEvent> METRONOME_STOP;
	public static EventType<MetronomeEvent> TEMPO_CHANGED;
	
	private int tempo;
	
	static {
		METRONOME_START = new EventType<MetronomeEvent>(Event.ANY, "Metronome start");
		METRONOME_STOP = new EventType<MetronomeEvent>(Event.ANY, "Metronome stop");
		TEMPO_CHANGED = new EventType<MetronomeEvent>(Event.ANY, "Tempo changed");
	}
	
	public MetronomeEvent(EventType<MetronomeEvent> eventType) {
		super(eventType);
	}
	
	public void setTempo(int t) {
		tempo = t;
	}
	
	public int getTempo() {
		return tempo;
	}
}
