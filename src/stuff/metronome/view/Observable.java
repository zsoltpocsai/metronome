package stuff.metronome.view;

public interface Observable {

	public void addObserver(Observer o);
	public void notifyObservers();
}
