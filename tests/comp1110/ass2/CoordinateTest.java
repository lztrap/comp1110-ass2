package comp1110.ass2;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    // Tests created by Amirul (u6511947)
    @Test
    public void testPegID(){
        Coordinate coordinateTest = new Coordinate("AA",1,1);
        String toString = coordinateTest.toString();
        assertTrue(toString.contains("Coord " + coordinateTest.pegID));
    }

    @Test
    public void testXCoord(){
        Coordinate coordinateTest = new Coordinate("AA",1,1);
        String toString = coordinateTest.toString();
        assertTrue(toString.contains("Coord " + coordinateTest.pegID +": (" + coordinateTest.xCoord));
    }

    @Test
    public void testYCoord(){
        Coordinate coordinateTest = new Coordinate("AA",1,1);
        String toString = coordinateTest.toString();
        assertTrue(toString.contains("Coord " + coordinateTest.pegID +": (" + coordinateTest.xCoord + ", " + coordinateTest.yCoord));
    }
}