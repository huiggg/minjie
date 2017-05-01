package nba.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nba.model.Location;

public class LocationTest {
  private static Location location;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    location = new Location(1999, 2014, "TorontoON", "AirCanadaCentre", 19500);
  }

  @Test
  // 地点相等的条件：球场名称相同、地点名称相同、容量相同、年份区间有交集
  public void locationEqualTest() {
    Assert.assertEquals(location, location);

    Location loaction2 = new Location(1999, 2014, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);

    loaction2 = new Location(1995, 1999, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);

    loaction2 = new Location(1995, 2010, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);

    loaction2 = new Location(2010, 2015, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);

    loaction2 = new Location(2014, 2017, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);

    loaction2 = new Location(1998, 2017, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);

    loaction2 = new Location(2010, 2012, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertEquals(location, loaction2);
  }

  @Test
  public void locationNotEqualTest() {
    Location loaction2 = new Location();
    Assert.assertNotEquals(location, loaction2);

    loaction2 = new Location(2015, 2017, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertNotEquals(location, loaction2);

    loaction2 = new Location(1990, 1998, "TorontoON", "AirCanadaCentre", 19500);
    Assert.assertNotEquals(location, loaction2);

    loaction2 = new Location(1999, 2014, "another_city", "AirCanadaCentre", 19500);
    Assert.assertNotEquals(location, loaction2);

    loaction2 = new Location(1999, 2014, "TorontoON", "another_arenaName", 19500);
    Assert.assertNotEquals(location, loaction2);

    loaction2 = new Location(1999, 2014, "TorontoON", "AirCanadaCentre", 19501);
    Assert.assertNotEquals(location, loaction2);
  }
}
