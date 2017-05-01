package nba.test;

import nba.model.Coach;
import nba.model.CoachTeam;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoachTest {
  private static Coach coach;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    coach = new Coach("D. Casey");
  }

  @Test
  public void coachEqualTest() {
    Assert.assertEquals(coach, coach);

    Coach coach2 = new Coach("D. Casey");
    Assert.assertEquals(coach, coach2);
  }

  @Test
  public void coachNotEqualTest() {
    Coach coach2 = new Coach("D. Casey ");
    Assert.assertNotEquals(coach, coach2);

    coach2 = new Coach("S. Brooks");
    Assert.assertNotEquals(coach, coach2);
  }

  @Test
  public void coachAddCoachTeamTest() {
    CoachTeam coachTeam = new CoachTeam();
    coach.addCoachTeam(coachTeam);
    Assert.assertEquals(1, coach.getCoachTeams().size());

    coachTeam = new CoachTeam(1996, 1997, "D. Casey", "Toronto Raptors");
    coach.addCoachTeam(coachTeam);
    Assert.assertEquals(2, coach.getCoachTeams().size());

    coachTeam = new CoachTeam();
    coach.addCoachTeam(coachTeam);
    Assert.assertEquals(2, coach.getCoachTeams().size());
  }
}
