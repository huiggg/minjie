package nba.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nba.model.ArenaTeam;

public class ArenaTeamTest {
  private static ArenaTeam arenaTeam;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    arenaTeam = new ArenaTeam(1967, 1971, "CowPalace", "Golden State Warriors");
  }

  @Test
  // ArenaTeam相等的条件：球队名称相同、球场名称相同、年份区间有交集
  public void arenaTeamEqualTest() {
    Assert.assertEquals(arenaTeam, arenaTeam);

    ArenaTeam arenaTeam2 = new ArenaTeam(1967, 1971, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1965, 1968, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1965, 1967, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1968, 1972, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);
    
    arenaTeam2 = new ArenaTeam(1971, 1972, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1968, 1970, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1966, 1972, "CowPalace", "Golden State Warriors");
    Assert.assertEquals(arenaTeam, arenaTeam2);
  }

  @Test
  public void arenaTeamNotEqualTest() {
    ArenaTeam arenaTeam2 = new ArenaTeam();
    Assert.assertNotEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1960, 1963, "CowPalace", "Golden State Warriors");
    System.out.println(arenaTeam.equals(arenaTeam2));
    Assert.assertNotEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1972, 1973, "CowPalace", "Golden State Warriors");
    Assert.assertNotEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1967, 1971, "another_arenaName", "Golden State Warriors");
    Assert.assertNotEquals(arenaTeam, arenaTeam2);

    arenaTeam2 = new ArenaTeam(1967, 1971, "CowPalace", "another_teamName");
    Assert.assertNotEquals(arenaTeam, arenaTeam2);
  }
}
