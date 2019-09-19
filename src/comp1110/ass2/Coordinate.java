package comp1110.ass2;

public class Coordinate {
    public String pegID;
    public double xCoord;
    public double yCoord;

    public Coordinate(String PegID, int xCoordinate, int yCoordinate){
        pegID = PegID;
        xCoord = xCoordinate;
        yCoord = yCoordinate;
    }

    public String toString()
    {
        return ("Coord " + pegID + ": (" + xCoord + ", " + yCoord + ")");
    }
}
