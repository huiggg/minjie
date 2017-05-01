package nba.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nba.model.ArenaTeam;
import nba.model.CoachTeam;
import nba.model.Season;
import nba.model.Team;

public class TeamTest {
  private static Team team;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    team = new Team("New York Knicks", "NYK", 1947, 68, 5235, 2617, 2);
  }

  @Test
  // Team是否相等取决于球队名称与球队诞生年份，而与球队简称、球队参加赛季、胜负次数、冠军次数无关
  public void teamEqualTest() {
    Assert.assertEquals(team, team);

    Team team2 = new Team("New York Knicks", "NYK", 1947, 68, 5235, 2617, 2);
    Assert.assertEquals(team, team2);

    team2 = new Team("New York Knicks", "another_teamAbbr", 1947, 68, 5235, 2617, 2);
    Assert.assertEquals(team, team2);

    team2 = new Team("New York Knicks", "NYK", 1947, 47, 5235, 2617, 2);
    Assert.assertEquals(team, team2);

    team2 = new Team("New York Knicks", "NYK", 1947, 68, 4000, 2617, 2);
    Assert.assertEquals(team, team2);

    team2 = new Team("New York Knicks", "NYK", 1947, 68, 5235, 2000, 2);
    Assert.assertEquals(team, team2);

    team2 = new Team("New York Knicks", "NYK", 1947, 68, 5235, 2617, 0);
    Assert.assertEquals(team, team2);
  }

  @Test
  public void teamNotEqualTest() {
    Team team2 = new Team("another_teamName", "NYK", 1947, 68, 5235, 2617, 2);
    Assert.assertNotEquals(team, team2);

    team2 = new Team("New York Knicks", "NYK", 1948, 68, 5235, 2617, 2);
    Assert.assertNotEquals(team, team2);
  }

  @Test
  public void teamAddSeasonTest() {
    Season season = new Season();
    team.addSeason(season);
    Assert.assertEquals(1, team.getSeasons().size());

    season = new Season("playerName", "teamName", 1996, 100, 10, 2012);
    team.addSeason(season);
    Assert.assertEquals(2, team.getSeasons().size());

    season = new Season();
    team.addSeason(season);
    Assert.assertEquals(2, team.getSeasons().size());
  }

  @Test
  public void teamAddCoachTeamTest() {
    CoachTeam coachTeam = new CoachTeam();
    team.addCoachTeam(coachTeam);
    Assert.assertEquals(1, team.getCoachTeams().size());

    coachTeam = new CoachTeam(1996, 1997, "D. Casey", "Toronto Raptors");
    team.addCoachTeam(coachTeam);
    Assert.assertEquals(2, team.getCoachTeams().size());

    coachTeam = new CoachTeam();
    team.addCoachTeam(coachTeam);
    Assert.assertEquals(2, team.getCoachTeams().size());
  }

  @Test
  public void teamAddArenaTeamTest() {
    ArenaTeam arenaTeam = new ArenaTeam();
    team.addArenaTeam(arenaTeam);
    Assert.assertEquals(1, team.getArenaTeams().size());

    arenaTeam = new ArenaTeam(1996, 1997, "Toronto Raptors", "AirCanadaCentre");
    team.addArenaTeam(arenaTeam);
    Assert.assertEquals(2, team.getArenaTeams().size());

    arenaTeam = new ArenaTeam();
    team.addArenaTeam(arenaTeam);
    Assert.assertEquals(2, team.getArenaTeams().size());
  }
}
