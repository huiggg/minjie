package nba.test;

import nba.model.Season;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SeasonTest {
  private static Season season;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    season = new Season("playerName", "teamName", 1996, 100, 10, 2013);
  }

  @Test
  // Season是否相等取决于球员名字、球员出生年份、球队名称、赛季年份，而与得分、场次无关
  public void seasonEqualTest() {
    Assert.assertEquals(season, season);

    Season season2 = new Season("playerName", "teamName", 1996, 100, 10, 2013);
    Assert.assertEquals(season, season2);

    season2 = new Season("playerName", "teamName", 1996, 200, 10, 2013);
    Assert.assertEquals(season, season2);

    season2 = new Season("playerName", "teamName", 1996, 100, 20, 2013);
    Assert.assertEquals(season, season2);
  }

  @Test
  public void seasonNotEqualTest() {
    Season season2 = new Season();
    Assert.assertNotEquals(season, season2);

    season2 = new Season("another_playerName", "teamName", 1996, 100, 10, 2013);
    Assert.assertNotEquals(season, season2);

    season2 = new Season("playerName", "another_teamName", 1996, 100, 10, 2013);
    Assert.assertNotEquals(season, season2);

    season2 = new Season("playerName", "teamName", 1995, 100, 10, 2013);
    Assert.assertNotEquals(season, season2);

    season2 = new Season("playerName", "teamName", 1996, 100, 10, 2014);
    Assert.assertNotEquals(season, season2);
  }
}
