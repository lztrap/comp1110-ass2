package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static comp1110.ass2.TestUtility.CONSTRAINED;
import static comp1110.ass2.TestUtility.GOOD_PLACEMENTS;
import static comp1110.ass2.TestUtility.NO_VIABLE;
import static org.junit.Assert.assertTrue;

/**
 * Given a string describing a placement of pieces and pegs, return a set
 * of all possible next viable piece placements.   To be viable, a piece
 * placement must be a valid placement of a single piece.  The piece must
 * not have already been placed (ie not already in the placement string),
 * and its placement must be valid.   If there are no valid piece placements
 * for the given placement string, return null.
 *
 * When symmetric placements of the same piece are viable, only the placement
 * with the lowest rotation should be included in the set.
 */
public class ViablePlacementsTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(20000);

  private void test(String start, Set<String> expected) {
    Set<String> outSet = TwistGame.getViablePiecePlacements(start);
    if (expected == null) {
      if (outSet != null) assertTrue("Expected empty set for input "+start+" but got "+outSet.toString(), outSet == null);
    } else {
      String expstr = expected.toString();
      assertTrue("Got an empty set for input " + start + ", but expected " + expstr, outSet != null);
      TreeSet<String> out = new TreeSet<>();
      out.addAll(outSet);
      String outstr = out.toString();
      assertTrue("Incorrect viable placements for input "+ start + ".  Expected " + expstr + ", but got " + outstr, expstr.equals(outstr));
    }
  }

  @Test
  public void test0() {
    Random rand = new Random();
    for (int i = 0; i < NO_VIABLE.length; i++) {
      test(NO_VIABLE[i], null);
      int target = rand.nextInt(8);
      String start = GOOD_PLACEMENTS[i].substring(0, target*4)+GOOD_PLACEMENTS[i].substring((1+target)*4, GOOD_PLACEMENTS[i].length());
      Set<String> placements = new TreeSet<>();
      placements.add(GOOD_PLACEMENTS[i].substring(target*4,(1+target)*4));
      test(start, placements);
    }
  }

  @Test
  public void test1() {
    Random rand = new Random();
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      int target = rand.nextInt(8);
      String start = GOOD_PLACEMENTS[i].substring(0, target*4)+GOOD_PLACEMENTS[i].substring((1+target)*4, GOOD_PLACEMENTS[i].length());
      Set<String> placements = new TreeSet<>();
      placements.add(GOOD_PLACEMENTS[i].substring(target*4,(1+target)*4));
      test(start, placements);
    }
  }

  private void testConstrained(boolean three) {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      Set<String> constrained = new TreeSet<>();
      constrained.add(CONSTRAINED[i].substring(0,4));
      constrained.add(CONSTRAINED[i].substring(4,8));
      if (three && CONSTRAINED[i].length() == 12)
        constrained.add(CONSTRAINED[i].substring(8,12));

      String start = "";
      for (int p = 0; p < 8; p++) {
        String piece = GOOD_PLACEMENTS[i].substring(4*p, 4*(p+1));
        if (!constrained.contains(piece)) {
          start += piece;
        }
      }
      start += GOOD_PLACEMENTS[i].substring(32, GOOD_PLACEMENTS[i].length());
      test(start, constrained);
    }
  }

  @Test
  public void test2() {
    testConstrained(false);
  }


  @Test
  public void test3() {
    testConstrained(true);
  }

  @Test
  public void test4() {
    String start = "c1A3d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0";
    String optstr = "a7A7a6A7a7A1a7A5a7A3a6A0a6B0a6A2a6A4b6A0b7A1b7A5b6A7";
    Set<String> options = new TreeSet<>();
    for (int p = 0; p < optstr.length()/4; p++) {
      options.add(optstr.substring(p*4, (p+1)*4));
    }
    test(start, options);
  }

}
