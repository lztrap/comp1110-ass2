package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static comp1110.ass2.TestUtility.BAD_PEGS;
import static comp1110.ass2.TestUtility.BAD_PIECES;
import static comp1110.ass2.TestUtility.GOOD_PLACEMENTS;
import static org.junit.Assert.assertTrue;

/**
 * Determine whether a placement string is well-formed:
 * - it consists of exactly N four-character piece placements (where N = 1 .. 15);
 * - each piece or peg placement is well-formed
 * - each piece or peg placement occurs in the correct alphabetical order (duplicate pegs can be in either order)
 * - no piece or red peg appears more than once in the placement
 * - no green, blue or yellow peg appears more than twice in the placement
 */
public class PlacementStringWellFormedTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(20000);

  private void test(String in, boolean expected) {
    boolean out = TwistGame.isPlacementStringWellFormed(in);
    assertTrue("Input was '"+in+"', expected "+expected+" but got "+out, out == expected);
  }

  @Test
  public void nPieces() {
    test("", false);
    test(GOOD_PLACEMENTS[0].substring(0,8),true);
    test(GOOD_PLACEMENTS[1]+"a",false);
    test(GOOD_PLACEMENTS[1],true);
  }

  @Test
  public void piecesWellFormed() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++)
      for (int j = 4; j < GOOD_PLACEMENTS[i].length(); j+=4) {
        test(GOOD_PLACEMENTS[i].substring(0, j), true);
        test(GOOD_PLACEMENTS[i].substring(0, j-4)+BAD_PIECES[i%BAD_PIECES.length], false);
      }
  }

  @Test
  public void piecesOrdered() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++)
      for (int j = 8; j < GOOD_PLACEMENTS[i].length(); j += 4) {
        test(GOOD_PLACEMENTS[i].substring(0, j), true);
        test(GOOD_PLACEMENTS[i].substring(GOOD_PLACEMENTS[i].length() - 4, GOOD_PLACEMENTS[i].length())+GOOD_PLACEMENTS[i].substring(0, j - 4), false);
      }
  }

  @Test
  public void duplicatesA() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      String[] pieces = new String[GOOD_PLACEMENTS[i].length() / 4];
      for (int j = 0; j < GOOD_PLACEMENTS[i].length(); j += 4) {
        pieces[j / 4] = GOOD_PLACEMENTS[i].substring(j, j + 4);
      }
      int target = i % 9;
      int victim = (i + 3) % 9;
      String bad = "";
      for (int j = 0; j < pieces.length; j++) {
        bad += pieces[(i == victim) ? target : i];
      }
      test(GOOD_PLACEMENTS[i], true);
      test(bad, false);
    }
  }

  @Test
  public void duplicatesB() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      String bad = GOOD_PLACEMENTS[i].substring(0,32)+BAD_PEGS[i % BAD_PEGS.length];
      test(GOOD_PLACEMENTS[i], true);
      test(bad, false);
    }
  }

}
