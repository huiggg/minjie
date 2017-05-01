package nba.test;

import nba.model.CoachTeam;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoachTeamTest {
  private static CoachTeam coachTeam;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    coachTeam = new CoachTeam(2000, 2014, "D. Casey", "Toronto Raptors");
  }

  @Test
  // CoachTeam相等的条件：球队名称相同、教练名称相同、年份区间有交集
  public void coachTeamEqualTest() {
    Assert.assertEquals(coachTeam, coachTeam);

    CoachTeam coachTeam2 = new CoachTeam(2000, 2014, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(1995, 2000, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(1995, 2010, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(2010, 2017, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);
    
    coachTeam2 = new CoachTeam(2014, 2017, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(2010, 2012, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(1995, 2017, "D. Casey", "Toronto Raptors");
    Assert.assertEquals(coachTeam, coachTeam2);
  }

  @Test
  public void coachTeamNotEqualTest() {
    CoachTeam coachTeam2 = new CoachTeam();
    Assert.assertNotEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(2013, 2014, "S. Brooks", "Toronto Raptors");
    Assert.assertNotEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(2013, 2014, "D. Casey", "Dallas Mavericks");
    Assert.assertNotEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(1995, 1998, "D. Casey", "Toronto Raptors");
    Assert.assertNotEquals(coachTeam, coachTeam2);

    coachTeam2 = new CoachTeam(2015, 2017, "D. Casey", "Toronto Raptors");
    Assert.assertNotEquals(coachTeam, coachTeam2);
  }
}
