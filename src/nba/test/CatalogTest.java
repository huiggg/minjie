package nba.test;

import nba.model.Arena;
import nba.model.ArenaTeam;
import nba.model.Catalog;
import nba.model.Coach;
import nba.model.CoachTeam;
import nba.model.Location;
import nba.model.Player;
import nba.model.Season;
import nba.model.Team;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CatalogTest {
  private static Catalog catalog;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    catalog = Catalog.getInstance();
  }

  @Test
  public void catalogAddPlayerTest() {
    Player player = new Player("playerName", 1996);
    catalog.addPlayer(player);
    Assert.assertEquals(1, catalog.getPlayers().size());

    // ���ֲ�ͬ
    player = new Player("another_playerName", 1996);
    catalog.addPlayer(player);
    Assert.assertEquals(2, catalog.getPlayers().size());

    // ���ղ�ͬ
    player = new Player("playerName", 1995);
    catalog.addPlayer(player);
    Assert.assertEquals(3, catalog.getPlayers().size());

    // ������ͬһ���ˣ�������ӽ�ȥ
    player = new Player("playerName", 1996);
    catalog.addPlayer(player);
    Assert.assertEquals(3, catalog.getPlayers().size());
  }

  @Test
  public void catalogAddSeasonTest() {
    Season season = new Season("playerName", "teamAbbr", 1996, 100, 50, 2014);
    catalog.addSeason(season);
    Assert.assertEquals(1, catalog.getSeasons().size());

    season = new Season("another_playerName", "teamAbbr", 1996, 100, 50, 2014);
    catalog.addSeason(season);
    Assert.assertEquals(2, catalog.getSeasons().size());

    season = new Season("playerName", "another_teamAbbr", 1996, 100, 50, 2014);
    catalog.addSeason(season);
    Assert.assertEquals(3, catalog.getSeasons().size());

    season = new Season("playerName", "teamAbbr", 1997, 100, 50, 2014);
    catalog.addSeason(season);
    Assert.assertEquals(4, catalog.getSeasons().size());

    season = new Season("playerName", "teamAbbr", 1996, 100, 50, 2015);
    catalog.addSeason(season);
    Assert.assertEquals(5, catalog.getSeasons().size());

    // ����������equal��Season�����ϲ�����Season��Game��Point
    season = new Season("playerName", "teamAbbr", 1996, 50, 25, 2014);
    season = catalog.addSeason(season);
    Assert.assertEquals(5, catalog.getSeasons().size());
    Assert.assertEquals(new Season("playerName", "teamAbbr", 1996, 50, 25, 2014), season);
    Assert.assertEquals(150, season.getPoint());
    Assert.assertEquals(75, season.getGameNum());
  }

  @Test
  public void catalogAddTeamTest() {
    Team team = new Team("New York Knicks", "NYK", 1947, 68, 5235, 2617, 2);
    catalog.addTeam(team);
    Assert.assertEquals(1, catalog.getTeams().size());

    team = new Team("another_teamName", "NYK", 1947, 68, 5235, 2617, 2);
    catalog.addTeam(team);
    Assert.assertEquals(2, catalog.getTeams().size());

    team = new Team("New York Knicks", "NYK", 1946, 68, 5235, 2617, 2);
    catalog.addTeam(team);
    Assert.assertEquals(3, catalog.getTeams().size());

    // ���ơ����䡢���������������ھ����޹�
    team = new Team("New York Knicks", "another_teamAbbr", 1946, 68, 5235, 2617, 2);
    catalog.addTeam(team);
    Assert.assertEquals(3, catalog.getTeams().size());

    team = new Team("New York Knicks", "NYK", 1946, 40, 0, 0, 0);
    catalog.addTeam(team);
    Assert.assertEquals(3, catalog.getTeams().size());
  }

  @Test
  public void catalogAddCoachTest() {
    Coach coach = new Coach("coachName");
    catalog.addCoach(coach);
    Assert.assertEquals(1, catalog.getCoachs().size());

    coach = new Coach("another_coachName");
    catalog.addCoach(coach);
    Assert.assertEquals(2, catalog.getCoachs().size());

    coach = new Coach("coachName");
    catalog.addCoach(coach);
    Assert.assertEquals(2, catalog.getCoachs().size());
  }

  @Test
  public void catalogAddCoachTeamTest() {
    CoachTeam coachTeam = new CoachTeam(2003, 2010, "coachName", "teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(1, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(2003, 2010, "another_coachName", "teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(2, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(2003, 2010, "coachName", "another_teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(3, catalog.getCoachTeams().size());

    // ���û�н���
    coachTeam = new CoachTeam(1950, 1956, "coachName", "teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(4, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(2050, 2060, "coachName", "teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());

    // ����н������ϲ���¼��ʱ��
    // ��߶˵��ཻ
    coachTeam = new CoachTeam(2002, 2003, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2002, 2003, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2002, coachTeam.getStartYear());
    Assert.assertEquals(2010, coachTeam.getEndYear());

    // ��߲����ཻ����ʱʱ��Ϊ[2002,2010]��
    coachTeam = new CoachTeam(2001, 2005, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2001, 2005, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2010, coachTeam.getEndYear());

    // �غϣ���ʱʱ��Ϊ[2001,2010]��
    coachTeam = new CoachTeam(2001, 2010, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2001, 2010, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2010, coachTeam.getEndYear());

    // �ұ߲����ཻ����ʱʱ��Ϊ[2001,2010]��
    coachTeam = new CoachTeam(2005, 2011, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2005, 2011, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2011, coachTeam.getEndYear());

    // �ұ߶˵��ཻ����ʱʱ��Ϊ[2001,2011]��
    coachTeam = new CoachTeam(2011, 2012, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2011, 2012, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2012, coachTeam.getEndYear());

    // ��Ӻ���Ҫȥ���������䣬��[1990,1995]��[2001,2012]���������[1995,2001]ʱ������������䲢���кϲ�
    coachTeam = new CoachTeam(1990, 1995, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(6, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(1995, 2001, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());// ʣ��5��
    Assert.assertEquals(new CoachTeam(1990, 2012, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(1990, coachTeam.getStartYear());
    Assert.assertEquals(2012, coachTeam.getEndYear());

    // �����ұߣ���[1990,2012]��[2017,2020]���������[2011,2018]ʱ������������䲢���кϲ�
    coachTeam = new CoachTeam(2017, 2020, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(6, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(2011, 2018, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());// ʣ��5��
    Assert.assertEquals(new CoachTeam(1990, 2020, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(1990, coachTeam.getStartYear());
    Assert.assertEquals(2020, coachTeam.getEndYear());
  }

  @Test
  public void catalogAddArenaTest() {
    Arena arena = new Arena("arenaName");
    catalog.addArena(arena);
    Assert.assertEquals(1, catalog.getArenas().size());

    arena = new Arena("another_arenaName");
    catalog.addArena(arena);
    Assert.assertEquals(2, catalog.getArenas().size());

    arena = new Arena("arenaName");
    catalog.addArena(arena);
    Assert.assertEquals(2, catalog.getArenas().size());
  }

  @Test
  public void catalogAddArenaTeamTest() {
    ArenaTeam arenaTeam = new ArenaTeam(2003, 2010, "arenaName", "teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(1, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(2003, 2010, "another_arenaName", "teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(2, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(2003, 2010, "arenaName", "another_teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(3, catalog.getArenaTeams().size());

    // ���û�н���
    arenaTeam = new ArenaTeam(1950, 1960, "arenaName", "teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(4, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(2050, 2060, "arenaName", "teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());

    // ����н������ϲ���¼��ʱ��
    // ��߶˵��ཻ
    arenaTeam = new ArenaTeam(2002, 2003, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2002, 2003, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2002, arenaTeam.getStartYear());
    Assert.assertEquals(2010, arenaTeam.getEndYear());

    // ��߲����ཻ����ʱʱ��Ϊ[2002,2010]��
    arenaTeam = new ArenaTeam(2001, 2005, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2001, 2005, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2010, arenaTeam.getEndYear());

    // �غϣ���ʱʱ��Ϊ[2001,2010]��
    arenaTeam = new ArenaTeam(2001, 2010, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2001, 2010, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2010, arenaTeam.getEndYear());

    // �ұ߲����ཻ����ʱʱ��Ϊ[2001,2010]��
    arenaTeam = new ArenaTeam(2005, 2011, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2005, 2011, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2011, arenaTeam.getEndYear());

    // �ұ߶˵��ཻ����ʱʱ��Ϊ[2001,2011]��
    arenaTeam = new ArenaTeam(2011, 2012, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2011, 2012, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2012, arenaTeam.getEndYear());

    // ��Ӻ���Ҫȥ���������䣬��[1990,1995]��[2001,2012]���������[1995,2001]ʱ������������䲢���кϲ�
    arenaTeam = new ArenaTeam(1990, 1995, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(6, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(1995, 2001, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());// ʣ��5��
    Assert.assertEquals(new ArenaTeam(1990, 2012, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(1990, arenaTeam.getStartYear());
    Assert.assertEquals(2012, arenaTeam.getEndYear());

    // �����ұߣ���[1990,2012]��[2017,2020]���������[2011,2018]ʱ������������䲢���кϲ�
    arenaTeam = new ArenaTeam(2017, 2020, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(6, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(2011, 2018, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());// ʣ��5��
    Assert.assertEquals(new ArenaTeam(1990, 2020, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(1990, arenaTeam.getStartYear());
    Assert.assertEquals(2020, arenaTeam.getEndYear());
  }

  @Test
  public void catalogAddLocationTest() {
    Location location = new Location(2003, 2010, "locationName", "arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(1, catalog.getLocations().size());

    location = new Location(2003, 2010, "another_locationName", "arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(2, catalog.getLocations().size());

    location = new Location(2003, 2010, "locationName", "another_arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(3, catalog.getLocations().size());

    location = new Location(2003, 2010, "locationName", "arenaName", 20000);
    catalog.addLocation(location);
    Assert.assertEquals(4, catalog.getLocations().size());

    // ���û�н���
    location = new Location(1950, 1960, "locationName", "arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(5, catalog.getLocations().size());

    location = new Location(2050, 2060, "locationName", "arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());

    // ����н������ϲ���¼��ʱ��
    // ��߶˵��ཻ
    location = new Location(2002, 2003, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2002, 2003, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2002, location.getStartYear());
    Assert.assertEquals(2010, location.getEndYear());

    // ��߲����ཻ����ʱʱ��Ϊ[2002,2010]��
    location = new Location(2001, 2005, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2001, 2005, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2010, location.getEndYear());

    // �غϣ���ʱʱ��Ϊ[2001,2010]��
    location = new Location(2001, 2010, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2001, 2010, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2010, location.getEndYear());

    // �ұ߲����ཻ����ʱʱ��Ϊ[2001,2010]��
    location = new Location(2005, 2011, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2005, 2011, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2011, location.getEndYear());

    // �ұ߶˵��ཻ����ʱʱ��Ϊ[2001,2011]��
    location = new Location(2011, 2012, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2011, 2012, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2012, location.getEndYear());

    // ��Ӻ���Ҫȥ���������䣬��[1990,1995]��[2001,2012]���������[1995,2001]ʱ������������䲢���кϲ�
    location = new Location(1990, 1995, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(7, catalog.getLocations().size());

    location = new Location(1995, 2001, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());// ʣ��6��
    Assert.assertEquals(new Location(1990, 2012, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(1990, location.getStartYear());
    Assert.assertEquals(2012, location.getEndYear());

    // �����ұߣ���[1990,2012]��[2017,2020]���������[2011,2018]ʱ������������䲢���кϲ�
    location = new Location(2017, 2020, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(7, catalog.getLocations().size());

    location = new Location(2011, 2018, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());// ʣ��6��
    Assert.assertEquals(new Location(1990, 2020, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(1990, location.getStartYear());
    Assert.assertEquals(2020, location.getEndYear());
  }
}
