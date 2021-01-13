import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Timer;

public class Minesweep  extends Application{

    int[][] vm;
    final int wib = 8;
    final int bLength = 8;
    int cs;
    int tr;
    int mitot = 0;
    int ctm = 0;
    int ctmnp = 0;
    int zm = 0;
    int bbc = 0; int mcd = 10; int tct = 0;
    int ms = 10;
    int rcc = 0;
    String c = " ";
    String nc = " ";
    boolean faceChange = false; 
    boolean unflagged = false;	
    boolean bombSpot = false, game = true;
    MainButton st;
    MineButton minb[][];

    Label rm = new Label(String.format("mines:" +"%03d",mcd));
    String md = String.format("mines:" +"%03d",mcd); 

    public static void main(String[] args) {
        launch(args);

    }

    public void restart(){

        for(int x = 0; x < vm.length; x++){

            for (int y = 0; y < vm[x].length; y++){	
                minb[x][y].setGraphic(new ImageView(new Image("File:res/cover.png")));
                System.out.print("call restart");
                st.setGraphic(new ImageView(new Image("File:res/face-smile.png")));
                ctmnp = 0;  zm = 0; rcc = 0; bbc = 0;
                mcd = 10; 
                md = String.format("mines:" +"%03d",mcd); 
                rm.setText(md);
            }

        }

    }

    public void start(Stage lps) {

        BorderPane lpbp = new BorderPane();
        HBox lphb = new HBox();
        GridPane lpgp = new GridPane();
        VBox lpvb = new VBox();
        lpbp.setTop(lpvb);
        lpbp.setCenter(lphb);
        lpbp.setBottom(lpgp);
        lps.setTitle("MineSweeper");

        st = new MainButton();

        st.setOnAction(new EventHandler<ActionEvent>(){

                public void handle(ActionEvent e){
                    System.out.println("face clicked");
                    restart();
                }
            });

        Label timer = (new Label(String.format("time:" +"%03d",tct)));
        lphb.getChildren().add(timer);
        lphb.getChildren().add(st);

        lphb.getChildren().add(rm);
        lphb.setSpacing(30);
        lphb.setAlignment(Pos.CENTER);

        MenuBar menuBar = new MenuBar();
        Menu game = new Menu("Game");
        Menu help = new Menu("Help");
        menuBar.getMenus().add(game);
        menuBar.getMenus().add(help);
        MenuItem beginner = new MenuItem("Beginner");
        MenuItem intermediate = new MenuItem("Intermediate");
        MenuItem expert = new MenuItem("Expert");
        game.getItems().addAll(beginner, intermediate, expert);
        lpvb.getChildren().add(menuBar);

        beginner.setOnAction(e -> {
                mcd = 10;
                vm = makeBoard(8,8,10);
                start(lps);	

            }); 

        intermediate.setOnAction(e -> {
                mcd = 40;
                vm = makeBoard(16,16,40);
                start(lps);					
            }); 

        expert.setOnAction(e -> {
                mcd = 99;
                vm = makeBoard(16,32,99);
                start(lps);
            }); 

        if (vm == null) vm = makeBoard(8,8,10);

        minb = new MineButton[vm.length][vm[0].length];
		
        for(int i =0; i < vm.length; i++){

            for(int j = 0; j < vm[i].length; j++){

                if(vm[i][j] == 9){

                    bbc++;

                }

            }

        }

        for(int x = 0; x < vm.length; x++) {
            for(int y = 0; y < vm[x].length; y++) {
                minb[x][y] = new MineButton(0, vm[x][y], x, y);

                tr = 0;
                MineButton nminb = minb[x][y];

                nminb.setPadding(new Insets(0,0,0,0));
                nminb.setGraphic(new ImageView(new Image("File:Res/cover.png")));
                lpgp.add(minb[x][y], y, x);

                nminb.setOnAction(new EventHandler<ActionEvent>(){

                        public void handle(ActionEvent e){

                            if(!nminb.flagged){

                                c = "clicking";

                                if (nminb.value == 9) {
                                    ctm++;
                                    mitot = +ctm;
                                    st.setGraphic(new ImageView(new Image("File:res/face-dead.png")));

                                    for (int row = 0; row < vm.length; row++) {

                                        for (int col = 0; col < vm[0].length; col++) {

                                            if (vm[row][col] == 9) {

                                                minb[row][col].setGraphic(new ImageView(new Image("File:res/mine-grey.png")));
                                            }

                                        }

                                    }
                                    nminb.setGraphic(new ImageView(new Image("File:Res/mine-red.png")));	
                                }

                                else if(nminb.value != 9 && nminb.value != 10){

                                    if( nminb.state == 0 && c.equals("clicking") && zm <=  vm.length*vm[0].length)  {
                                        nminb.state=1;
                                        zm ++;

                                    }
                                    if(c.equals("clicking") && nminb.state == 1) {
                                        nc = "already";
                                        ctmnp ++;

                                    }
                                    if( mitot == 0 && zm == (vm.length*vm[0].length) - bbc){
                                        st.setGraphic(new ImageView(new Image("File:res/face-win.png")));
                                    }
                                    nminb.setGraphic(new ImageView(new Image("File:Res/" + nminb.value + ".png")));
                                }

                            }

                        }

                    });

                nminb.setOnMouseClicked(e -> {
                        MouseButton button = e.getButton();

                        if (button == MouseButton.SECONDARY && nminb.state == 0){
                            nminb.flagged = !nminb.flagged;
                            rcc+=1;

                            nminb.setGraphic(new ImageView(new Image("File:res/flag.png")));

                            if(nminb.flagged == true){
                                mcd--;
                                nminb.spotsAroundFlags();

                                nminb.setGraphic(new ImageView(new Image("File:res/flag.png")));
                            }
                            else{
                                mcd++;
                                nminb.setGraphic(new ImageView(new Image("File:res/cover.png")));
                            }

                            rm.setText(String.format("mines:" +"%03d", mcd));
                        }
                    });

            }
        }
        Scene scene = new Scene(lpbp);
        lps.setScene(scene);
        lps.show();
    }

    public int[][] makeBoard(int rc, int cc, int mined) {

        int cmc = 0;
        int [][]box = new int[rc][cc];
        int nob=0;
        int row;
        int col;

        while(cmc < mined){

            row = (int)(Math.random()*rc);
            col = (int)(Math.random()*cc);

            if(box[row][col] == 0){
                box[row][col] = 9;
                cmc++;

            }
        }

        for(int i = 0; i < box.length; i++){
            for(int j = 0; j < box[i].length; j++){
                if(box[i][j] == 0){
                    nob = 0;
                    if (i+1 < box.length && box[i+1][j] == 9) {
                        nob++;
                    }
                    if (i-1 >= 0 && box[i-1][j] == 9) {
                        nob++;
                    }
                    if (j+1 < box[0].length && box[i][j+1] == 9) {
                        nob++;
                    }
                    if (j-1 >= 0 && box[i][j-1] == 9) {
                        nob++;
                    }
                    if (i+1 < box.length && j+1 < box[0].length && box[i+1][j+1] == 9) {
                        nob++;
                    }
                    if (i+1 < box.length && j-1 >= 0 && box[i+1][j-1] == 9) {
                        nob++;
                    }
                    if (i-1 >= 0 && j+1 < box[0].length && box[i-1][j+1] == 9) {
                        nob++;
                    }
                    if (i-1 >= 0 && j-1 >= 0 && box[i-1][j-1] == 9) {
                        nob++;
                    }		
                    box[i][j] = nob;
                }

            }

        }

        return box;
    }

    class MainButton extends Button{
        int state;
        boolean faceClicked;
        Button theSmile;

        public MainButton(){

            faceClicked = false;
            double size = 5;
            setMinWidth(size);
            setMaxWidth(size);
            setMinHeight(size);

            theSmile = new Button();
            ImageView smifa;
            smifa =  new ImageView(new Image("File:res/face-smile.png"));
            setGraphic(smifa);
            setPadding(new Insets(0,0,0,0));
        }

        public boolean getClicked(){
            return faceClicked;
        }

    }

    class MineButton extends Button{
        int state;
        public int value;
        int row, col;
        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        boolean clicked = false;
        boolean flagged = false;

        public MineButton(int s, int v, int r, int c){
            state = s;
            value = v;
            row = r;
            col = c;
        }

        public void spotsAroundFlags(){
            int flags = 0;

            if (getRow()+1 < vm.length && minb[getRow()+1][getCol()+1].flagged == true) {
                flags++;
            }
            if (getRow()-1 >= 0 && minb[getRow()-1][getCol()].flagged == true) {
                flags++;
            }
            if (getRow()+1 < vm[0].length && minb[getRow()][getCol()+1].flagged == true) {
                flags++;
            }
            if (getCol()-1 >= 0 && minb[getRow()][getCol()-1].flagged == true) {
                flags++;
            }
            if (getRow()+1 < vm.length && getCol()+1 < vm[0].length && minb[getRow()+1][getCol()+1].flagged == true) {
                flags++;
            }
            if (getRow()+1 < vm.length && getCol()-1 >= 0 && minb[getRow()+1][getCol()-1].flagged == true) {
                flags++;
            }
            if (getRow()-1 >= 0 && getCol()+1 < vm[0].length && minb[getRow()-1][getCol()+1].flagged == true) {
                flags++;
            }
            if (getRow()-1 >= 0 && getCol()-1 >= 0 && minb[getRow()-1][getCol()-1].flagged == true) {
                flags++;
            }		
            vm[getRow()][getCol()] = flags;

            if(vm[getRow()][getCol()] == flags){
                minb[getRow()][getCol()].state = 1;
            }

        } 

    }

}