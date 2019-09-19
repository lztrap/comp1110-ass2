package comp1110.ass2.gui;

import javafx.scene.image.ImageView;

public class Piece extends ImageView {

    private int heightInGridPane = 0;
    private int widthInGridPane = 0;
    private String pieceName = null;


    Piece(char pieceID, char column, char row, char orientation){

    }
    Piece(String pieceName, int heightInGridPane, int widthInGridPane){

        this.pieceName = pieceName;
        this.heightInGridPane = heightInGridPane;
        this.widthInGridPane = widthInGridPane;

    }

    public int getHeightInGridPane() {
        return heightInGridPane;
    }

    public int getWidthInGridPane() {
        return widthInGridPane;
    }

    public String getPieceName() {
        return pieceName;
    }
}
