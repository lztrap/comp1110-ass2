package comp1110.ass2.gui;

import comp1110.ass2.TwistGame;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    public  GridPane grid = new GridPane();

    // FIXME Task 7: Implement a basic playable Twist Game in JavaFX that only allows pieces to be placed in valid places
    // From Anzee u6744888
    public void initialBoard(){

            grid.setHgap(1);
            grid.setVgap(1);
            grid.setGridLinesVisible(true);
            grid.setPadding(new Insets(10, 10, 100, 10));

            for (int column=0; column<8; column++){
                grid.getColumnConstraints().add(new ColumnConstraints(100));
            }
            for (int row=0; row<4; row++){
                grid.getRowConstraints().add(new RowConstraints(100));
            }
            for(int column=0; column<8; column++ ) {
                for(int row=0; row<4; row++ ){
                    Rectangle rectangle = new Rectangle();
                    rectangle.setWidth(100);
                    rectangle.setHeight(100);
                    rectangle.setFill(Color.ANTIQUEWHITE);
                    grid.add(rectangle, column, row);
                }
            }

    }

    @FXML
    protected void handleButtonAction(Event event) throws IOException {

        String idBt = ((Button)event.getSource()).getId();
        if ( ("btStart").equals(idBt) ){
            //makePlacement("c3A3a6B7");
        }

    }

    // The game will be initialize with a fixed window size and
    // an empty board with the pegs by using the initialBoard method.
    // The user will have to drag the pieces and drop it on the board
    // to place it. Each shaped pieces will be on the side of the board.
    // The user can also rotate the pieces by pressing the keyboard
    // keys while the users is still holding on the piece.
    // - u6511947

    public String currentBoard(){
        return null;
    }

    // When the piece is placed on the board, the method
    // currentBoard will return in string of the current
    // placements of pieces. Then the main method will check
    // by calling isPlacementStringValid to see if the placement
    // is valid. If it is valid the piece is placed down on the board
    // ready for the user to select another piece. Otherwise,
    // piece will return back to the the selection area.
    // - u6511947

    // FIXME Task 8: Implement starting placements

    public String startPlacements() {
        //Randomly place two pieces
        //From Anzee u6744888
        char[] pieceOrPeg = {'a','b','c','d','e','f','g','h'};
        char[] rowRandom = {'A','B','C','D'};

        String initPlacements = "";
        while((initPlacements=="") || (!TwistGame.isPlacementStringWellFormed(initPlacements)) ||(!TwistGame.isPlacementStringValid(initPlacements)))
        {
            initPlacements = "";
            Random random=new Random();
            for(int i=0; i<2 ;i++){

                String whichPiece = "";
                while((!TwistGame.isPlacementWellFormed(whichPiece)) && (!TwistGame.isPlacementStringValid(whichPiece)))
                {
                    whichPiece = "";
                    int c1 = random.nextInt(8);
                    int c2 = random.nextInt(8)+1;
                    int c3 = random.nextInt(4);
                    int c4 = random.nextInt(8);
                    whichPiece = whichPiece + pieceOrPeg[c1] + c2 + rowRandom[c3] + c4;
                    //System.out.println(whichPiece);
                }
                initPlacements = Viewer.BubbleSort(Viewer.getLocationFromPlacementStr(initPlacements + whichPiece));
            }
        }

        System.out.println("***************************************************************");
        System.out.println("********** 随机生成的状态字符串  : " + initPlacements);

        if((initPlacements==null) && (initPlacements == "")){
            return null;
        }else {
            return initPlacements;
        }

    }
    // When the game is started the main method will call to
    // to this method to start the game with some pieces
    // already on the board. To do this the method will
    // produce a valid starting placement at random.
    // It will return a string that is suitable for
    // the starting piece.
    // - u6511947

    // FIXME Task 10: Implement hints

    public void hint(){

    }
    // The hint method will take the current board placement
    // and uses the current placement string into the getSolutions
    // method which then only return the next immediate piece
    // of any solutions.
    // - u6511947




    // FIXME Task 11: Generate interesting starting placements

    // To generate interesting starting placements the startPlacements
    // is modified so that instead of only one piece is on the board,
    // there is several pieces on the board but not the complete solution.
    // To do this the method will generate partial solutions within
    // a range of number of pieces. The resulting placements must not be
    // the whole solutions so that the user can play the game with
    // the starting piece placements.
    // - u6511947

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane rootPane = (BorderPane)FXMLLoader.load(getClass().getResource("IqTwistBoard.fxml"));
        primaryStage.setTitle("TWISTGAME VIEWER");

        VBox vDownBox = new VBox();
        HBox hboxUp = new HBox();

        HBox hBoxDown = new HBox();
        VBox vbox = new VBox();
        Image imageChoose = new Image("comp1110/ass2/gui/assets/k.png");
        ImageView imageViewChoosen = new ImageView();

        addPieceOrPegHbox(hboxUp);
        initialBoard();

        Label label = new Label();
        label.setText(" Option");
        label.setFont(Font.font("Timer New Roman",
                FontWeight.BOLD, FontPosture.ITALIC, 24));
        vbox.getChildren().addAll(label,new ImageView(imageChoose));

        hBoxDown.getChildren().addAll(grid,vbox);
        vDownBox.getChildren().addAll(hboxUp,hBoxDown);

        rootPane.setCenter(vDownBox);
        primaryStage.setScene(new Scene(rootPane, 933, 700));
        primaryStage.show();
    }

    private void addPieceOrPegHbox(HBox hBox){

        Label choosePieceOrPeg = new Label(" CHOOSE: ");
        choosePieceOrPeg.setFont(Font.font("Timer New Roman", 26));

        Image img1 = new Image("comp1110/ass2/gui/assets/a.png");
        ImageView imgv1 = new ImageView(img1);
        imgv1.setFitHeight(50);
        imgv1.setFitWidth(75);

        Image img2 = new Image("comp1110/ass2/gui/assets/b.png");
        ImageView imgv2 = new ImageView(img2);
        imgv2.setFitHeight(50);
        imgv2.setFitWidth(75);
        Image img3 = new Image("comp1110/ass2/gui/assets/c.png");
        ImageView imgv3 = new ImageView(img3);
        imgv3.setFitHeight(25);
        imgv3.setFitWidth(100);
        Image img4 = new Image("comp1110/ass2/gui/assets/d.png");
        ImageView imgv4 = new ImageView(img4);
        imgv4.setFitHeight(50);
        imgv4.setFitWidth(75);
        Image img5 = new Image("comp1110/ass2/gui/assets/e.png");
        ImageView imgv5 = new ImageView(img5);
        imgv5.setFitHeight(50);
        imgv5.setFitWidth(50);
        Image img6 = new Image("comp1110/ass2/gui/assets/f.png");
        ImageView imgv6 = new ImageView(img6);
        imgv6.setFitHeight(50);
        imgv6.setFitWidth(75);
        Image img7 = new Image("comp1110/ass2/gui/assets/g.png");
        ImageView imgv7 = new ImageView(img7);
        imgv7.setFitHeight(50);
        imgv7.setFitWidth(75);
        Image img8 = new Image("comp1110/ass2/gui/assets/h.png");
        ImageView imgv8 = new ImageView(img8);
        imgv8.setFitHeight(25);
        imgv8.setFitWidth(75);
        Image img9 = new Image("comp1110/ass2/gui/assets/i.png");
        ImageView imgv9 = new ImageView(img9);
        imgv9.setFitHeight(50);
        imgv9.setFitWidth(50);
        Image img10 = new Image("comp1110/ass2/gui/assets/j.png");
        ImageView imgv10 = new ImageView(img10);
        imgv10.setFitHeight(50);
        imgv10.setFitWidth(50);
        Image img11 = new Image("comp1110/ass2/gui/assets/k.png");
        ImageView imgv11 = new ImageView(img11);
        imgv11.setFitHeight(50);
        imgv11.setFitWidth(50);
        Image img12 = new Image("comp1110/ass2/gui/assets/l.png");
        ImageView imgv12 = new ImageView(img12);
        imgv12.setFitHeight(50);
        imgv12.setFitWidth(50);

        hBox.getChildren().addAll(choosePieceOrPeg,imgv1,imgv2,imgv3,imgv4,
                imgv5,imgv6,imgv7,imgv8,imgv9,imgv10,imgv11,imgv12);


    }


}
