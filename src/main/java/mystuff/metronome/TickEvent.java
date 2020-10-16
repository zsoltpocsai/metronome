package mystuff.metronome;

import java.util.EventObject;

@SuppressWarnings("serial")
public class TickEvent extends EventObject {
	
	public static class Type {
		public static Type FIRST_BEAT = new Type("FIRST_BEAT");
		public static Type ANY_BEAT = new Type("ANY_BEAT");
		
		private String name;
		
		private Type(String name) {
			this.name = name;
		}
		
		@Override
		public int hashCode() {
			return this.name.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Type) {
				Type type = (Type) o;
				if (type.toString().equals(this.toString())) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private Type type;
	
	public TickEvent(Type type, Tick source) {
		super(source);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
