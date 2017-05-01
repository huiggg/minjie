package nba.test;

import nba.util.NBAComparator;

import org.junit.Assert;
import org.junit.Test;

public class NBAComparatorTest {

  @Test
  public void integerCompareFromSmallTest() {
    NBAComparator<Integer> comparator = new NBAComparator<Integer>("intValue");

    Assert.assertTrue(comparator.compare(3, 4) < 0);
    Assert.assertTrue(comparator.compare(4, 4) == 0);
    Assert.assertTrue(comparator.compare(5, 4) > 0);
  }

  @Test
  public void integerCompareFromBigTest() {
    NBAComparator<Integer> comparator = new NBAComparator<Integer>("intValue",NBAComparator.FROM_BIG);

    Assert.assertTrue(comparator.compare(3, 4) > 0);
    Assert.assertTrue(comparator.compare(4, 4) == 0);
    Assert.assertTrue(comparator.compare(5, 4) < 0);
  }

  @Test
  public void stringCompareFromSmallTest() {
    NBAComparator<String> comparator = new NBAComparator<String>("toString");

    Assert.assertTrue(comparator.compare("abcdefg", "ABCDEFG") > 0);
    Assert.assertTrue(comparator.compare("long string", "short string") < 0);
    Assert.assertTrue(comparator.compare("the same string", "the same string") == 0);
  }
  
  @Test
  public void stringCompareFromBigTest() {
    NBAComparator<String> comparator = new NBAComparator<String>("toString",NBAComparator.FROM_BIG);

    Assert.assertTrue(comparator.compare("abcdefg", "ABCDEFG") < 0);
    Assert.assertTrue(comparator.compare("long string", "short string") > 0);
    Assert.assertTrue(comparator.compare("the same string", "the same string") == 0);
  }
}
