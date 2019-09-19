package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static comp1110.ass2.TestUtility.GOOD_PLACEMENTS;
import static org.junit.Assert.assertTrue;

/**
 * Determine whether a piece or peg placement is well-formed according to the following:
 * - it consists of exactly four characters
 * - the first character is in the range a .. l (pieces and pegs)
 * - the second character is in the range 1 .. 8 (columns)
 * - the third character is in the range A .. D (rows)
 * - the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a peg)
 */
public class PlacementWellFormedTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(20000);


  private void test(String in, boolean expected) {
    boolean out = TwistGame.isPlacementWellFormed(in);
    assertTrue("Input was '"+in+"', expected "+expected+" but got "+out, out == expected);
  }

  @Test
  public void fourCharacters() {
    test(GOOD_PLACEMENTS[0].substring(0,4), true);
    test(GOOD_PLACEMENTS[1].substring(0,2), false);
    test(GOOD_PLACEMENTS[3].substring(0,4), true);
    test(GOOD_PLACEMENTS[4].substring(0,6), false);
  }

  @Test
  public void firstCharacterOK() {
    for (int i = 0; i < GOOD_PLACEMENTS[0].length()-4; i+=4) {
      test(GOOD_PLACEMENTS[0].substring(i+0, i+4), true);
      test(GOOD_PLACEMENTS[0].substring(i+1, i+5), false);
    }
    for (char c = 'Z'; c < 'z'; c++) {
      test(c+"1A0", c >= 'a' && c <= 'l');
    }

  }

  @Test
  public void secondCharacterOK() {
    for (int i = 0; i < GOOD_PLACEMENTS[0].length()-4; i+=4) {
      test(GOOD_PLACEMENTS[0].substring(i+0, i+4), true);
      test(GOOD_PLACEMENTS[0].substring(i+1, i+5), false);
    }
    for (char c = '0'; c <= '9'; c++) {
      test("a"+c+"A0", c >= '1' && c <= '8');
    }
  }

  @Test
  public void thirdCharacterOK() {
    for (int i = 0; i < GOOD_PLACEMENTS[0].length()-4; i+=4) {
      test(GOOD_PLACEMENTS[0].substring(i+0, i+4), true);
      test(GOOD_PLACEMENTS[0].substring(i+1, i+5), false);
    }
    for (char c = '0'; c <= 'Z'; c++) {
      test("a4"+c+"0", c >= 'A' && c <= 'D');
    }
  }

  @Test
  public void fourthCharacterOK() {
    for (char p = 'a'; p <= 'l'; p++) {
      for (char r = '-'; r <= 'D'; r++)
        test(p+"4A"+r, (p >= 'a' && p <= 'h' && r >= '0' && r <= '7') || (p >= 'i' && p <= 'l' && r == '0'));
    }
  }

}
