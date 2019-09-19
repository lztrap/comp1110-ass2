package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertTrue;

/**
 * Determine whether a placement string is valid.  To be valid, the placement
 * string must be well-formed and each piece placement must be a valid placement
 * according to the rules of the game.
 * - pieces must be entirely on the board
 * - pieces must not overlap each other
 * - pieces may only overlap pegs when the a) peg is of the same color and b) the
 *   point of overlap in the piece is a hole.
 */
public class PlacementStringValidTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(20000);

  private void test(String in, String invalid, boolean expected) {
    boolean out = TwistGame.isPlacementStringValid(in);
    assertTrue("Input was '"+in+"', expected "+expected+" but got "+out+(invalid == "" ? "": " (subsequence "+invalid+" is not valid)"), out == expected);
  }

  @Test
  public void offBoardA() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      int badpiece = i % 8;
      String good = GOOD_PLACEMENTS[i].substring((badpiece * 4), (badpiece + 1)*4);
      test(good, "", true);
      test(OFF_BOARD[badpiece], "", false);
    }
  }

  @Test
  public void offBoardB() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      int badpiece = i % 8;
      String bad = GOOD_PLACEMENTS[i].substring(0, (badpiece * 4)) + OFF_BOARD[badpiece] + GOOD_PLACEMENTS[i].substring((badpiece + 1) * 4, GOOD_PLACEMENTS[i].length());
      test(GOOD_PLACEMENTS[i], "", true);
      test(bad, OFF_BOARD[badpiece], false);
    }
  }

  @Test
  public void overlapA() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      int targeta = i % 8;
      int targetb = (i+1) % 8;
      if (targeta == 7) { targeta = 0; targetb = 7; }
      String good = GOOD_PLACEMENTS[i].substring((targeta * 4), (targeta + 1)*4)+GOOD_PLACEMENTS[i].substring((targetb * 4), (targetb + 1)*4);
      test(good, "", true);
      test(OVERLAP[i % 8], "", false);
    }
  }

  @Test
  public void overlapB() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      String overlap = OVERLAP[i%8];
      String bad = "";
      for (int p = 0; p < 8; p++){
        char piece = GOOD_PLACEMENTS[i].charAt(p*4);
        if (piece == overlap.charAt(0)) {
          bad += overlap.substring(0, 4);
        } else if (piece == overlap.charAt(4)) {
          bad += overlap.substring(4,8);
        } else
          bad += GOOD_PLACEMENTS[i].substring(p*4, (p+1)*4);
      }
      bad += GOOD_PLACEMENTS[i].substring(8*4, GOOD_PLACEMENTS[i].length());
      test(GOOD_PLACEMENTS[i], "", true);
      test(bad, overlap, false);
    }
  }

  @Test
  public void badPegs() {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      String badpeg = INVALID_PEGS[i%INVALID_PEGS.length];
      test(GOOD_PLACEMENTS[i], "", true);
      test(badpeg, badpeg, false);
    }
  }

}
