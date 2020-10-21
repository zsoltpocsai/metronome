package mystuff.metronome.view;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ImageDisplay extends ImageView {
	
	public static final String TEMP_CHART_PATH = "/pics/tempo_chart.png";
	public static final String CONTROLS_PATH = "/pics/controls.png";
	
	public ImageDisplay(String imgPath) {
		super();
		Image image = new Image(this.getClass().getResourceAsStream(imgPath));
		this.setImage(image);
		this.setPreserveRatio(true);
	}
}
