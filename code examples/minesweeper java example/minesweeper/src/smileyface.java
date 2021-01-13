import java.awt.Button;
import java.awt.Image;

import javax.swing.text.html.ImageView;
public class smileyface extends Button {
		int state;
		ImageView fteeth, fshocked, fshades, fdead;
	public smileyface () {
		double size = 70;
		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);
		
		fteeth = new ImageView(new Image("file:res/face-smile.png"));
		fshocked = new ImageView(new Image("file:res/face-O.png"));
		fshades = new ImageView(new Image("file:res/face-win.png"));
		fdead = new ImageView(new Image("file:res/face-dead.png"));
		
		fteeth.setFitHeight(size);
		fteeth.setFitWidth(size);
		fshocked.setFitHeight(size);
		fshocked.setFitWidth(size);
		fshades.setFitHeight(size);
		fshades.setFitWidth(size);
		fdead.setFitHeight(size);
		fdead.setFitWidth(size);
		
		setGraphic(fteeth);
		
	}
}
