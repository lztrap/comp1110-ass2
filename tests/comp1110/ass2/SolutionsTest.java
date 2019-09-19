package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static comp1110.ass2.TestUtility.GOOD_PLACEMENTS;
import static comp1110.ass2.TestUtility.MULTI;
import static comp1110.ass2.TestUtility.SINGLE;
import static org.junit.Assert.assertTrue;

public class SolutionsTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(20000);


  private void test(String start, Set<String> expected) {
    String[] out = TwistGame.getSolutions(start);
    assertTrue("No solutions returned for problem " + start + ", expected " + expected, out != null);
    TreeSet<String> outSet = new TreeSet<>();
    outSet.addAll(Arrays.asList(out));
    String expstr = expected.toString();
    String outstr = outSet.toString();
    assertTrue("For problem " + start + ", was expecting " + expstr + ", but got " + outstr, expstr.equals(outstr));
  }

  private void single(int n) {
    for (int i = 0; i < GOOD_PLACEMENTS.length; i++) {
      TreeSet<String> expected = new TreeSet<>();
      expected.add(GOOD_PLACEMENTS[i].substring(0,32));
      test(SINGLE[n][i], expected);
    }
  }

  @Test
  public void single2() {
    single(0);
  }

  @Test
  public void single3() {
    single(1);
  }

  @Test
  public void single4() {
    single(2);
  }

  @Test
  public void multiA() {
    TreeSet<String> expected = new TreeSet<>();
    for (int i = 1; i < MULTI[0].length; i++)
      expected.add(MULTI[0][i]);
    test(MULTI[0][0], expected);
  }

  @Test
  public void multiB() {
    TreeSet<String> expected = new TreeSet<>();
    for (int i = 1; i < MULTI[1].length; i++)
      expected.add(MULTI[1][i]);
    test(MULTI[1][0], expected);
  }

}
