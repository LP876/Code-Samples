import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bt extends Button {

    int lb= 0;
    ImageView lps,lp00, lp01,lp02 , lp03, dimine;
		
    public bt() {

	    
        double size = 50;
        setMinWidth(size);
        setMaxWidth(size);
        setMinHeight(size);
        setMaxHeight(size);

		
        lps = new ImageView(new Image("file:res/Cover.png"));
        lp00 = new ImageView(new Image("file:res/0.png"));
        lp01 = new ImageView(new Image("file:res/1.png"));
        lp02 = new ImageView(new Image("file:res/2.png"));
        lp03 = new ImageView(new Image("file:res/3.png"));
        dimine = new ImageView(new Image("file:res/mine-red.png"));

		
        lps.setFitHeight(size);
        lps.setFitWidth(size);
        lp00.setFitWidth(size);
        lp00.setFitHeight(size);
        lp01.setFitHeight(size);
        lp01.setFitWidth(size);
        lp02.setFitHeight(size);
        lp02.setFitWidth(size);
        lp03.setFitHeight(size);
        lp03.setFitWidth(size);
        dimine.setFitHeight(size);
        dimine.setFitWidth(size);
        setGraphic(lps);
        
    }

}
