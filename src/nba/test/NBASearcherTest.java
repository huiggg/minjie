package nba.test;

import java.util.ArrayList;
import java.util.List;

import nba.util.NBASearcher;

import org.junit.Assert;
import org.junit.Test;

public class NBASearcherTest {

  @Test
  public void searchInteger() {
    List<Integer> list = new ArrayList<Integer>();
    list.add(1000);
    list.add(2000);
    list.add(3000);
    list.add(2000);
    list.add(1000);

    Assert.assertTrue(list.get(1) == NBASearcher.find(list, "intValue", 2000));
    Assert.assertFalse(new Integer(2000) == NBASearcher.find(list, "intValue", 2000));// 返回该对象的引用
    Assert.assertTrue(null == NBASearcher.find(list, "intValue", 5000));

    List<Integer> result = NBASearcher.findAll(list, "intValue", 1000);
    Assert.assertEquals(2, result.size());
    Assert.assertTrue(list.get(0) == result.get(0));
    Assert.assertTrue(list.get(4) == result.get(1));
  }

  @Test
  public void searchString() {
    List<String> list = new ArrayList<String>();
    list.add("str1");
    list.add("str2");
    list.add("str3");
    list.add("str2");
    list.add("str1");

    Assert.assertTrue(list.get(1) == NBASearcher.find(list, "toString", "str2"));
    Assert.assertFalse(new String("str2") == NBASearcher.find(list, "toString", "str2"));// 返回该对象的引用
    Assert.assertTrue(null == NBASearcher.find(list, "toString", "str5"));

    List<String> result = NBASearcher.findAll(list, "toString", "str1");
    Assert.assertEquals(2, result.size());
    Assert.assertTrue(list.get(0) == result.get(0));
    Assert.assertTrue(list.get(4) == result.get(1));
  }
}
