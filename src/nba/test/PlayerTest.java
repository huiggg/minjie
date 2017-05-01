package nba.test;

import nba.model.Player;
import nba.model.Season;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

  private static Player player1;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    player1 = new Player("Name1", 1996);
  }

  @Test
  // 当且仅当两个球员的名字和出生年份相同才认为两者相等
  public void playerEqualTest() {
    Assert.assertEquals(player1, player1);

    Player player2 = new Player("Name1", 1996);
    Assert.assertEquals(player1, player2);
  }

  @Test
  public void playerNotEqualTest() {
    Player player2 = new Player("Name1", 1995);
    Assert.assertNotEquals(player1, player2);

    player2 = new Player("Name2", 1996);
    Assert.assertNotEquals(player1, player2);

    player2 = new Player();
    Assert.assertNotEquals(player1, player2);
  }

  @Test
  public void playerAddSeasonTest() {
    player1.addSeason(new Season());
    Assert.assertEquals(player1.getSeasons().size(), 1);

    player1.addSeason(new Season("playerName", "teamName", 1996, 100, 10, 2012));
    Assert.assertEquals(player1.getSeasons().size(), 2);

    player1.addSeason(new Season()); // 添加相同的不予理会
    Assert.assertEquals(player1.getSeasons().size(), 2);
  }
}
