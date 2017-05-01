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

    // 名字不同
    player = new Player("another_playerName", 1996);
    catalog.addPlayer(player);
    Assert.assertEquals(2, catalog.getPlayers().size());

    // 生日不同
    player = new Player("playerName", 1995);
    catalog.addPlayer(player);
    Assert.assertEquals(3, catalog.getPlayers().size());

    // 否则是同一个人，不会添加进去
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

    // 如果添加两个equal的Season，则会合并两个Season的Game和Point
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

    // 与简称、年龄、场次数、分数、冠军数无关
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

    // 年份没有交集
    coachTeam = new CoachTeam(1950, 1956, "coachName", "teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(4, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(2050, 2060, "coachName", "teamName");
    catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());

    // 年份有交集，合并记录的时间
    // 左边端点相交
    coachTeam = new CoachTeam(2002, 2003, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2002, 2003, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2002, coachTeam.getStartYear());
    Assert.assertEquals(2010, coachTeam.getEndYear());

    // 左边部分相交（此时时间为[2002,2010]）
    coachTeam = new CoachTeam(2001, 2005, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2001, 2005, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2010, coachTeam.getEndYear());

    // 重合（此时时间为[2001,2010]）
    coachTeam = new CoachTeam(2001, 2010, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2001, 2010, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2010, coachTeam.getEndYear());

    // 右边部分相交（此时时间为[2001,2010]）
    coachTeam = new CoachTeam(2005, 2011, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2005, 2011, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2011, coachTeam.getEndYear());

    // 右边端点相交（此时时间为[2001,2011]）
    coachTeam = new CoachTeam(2011, 2012, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());
    Assert.assertEquals(new CoachTeam(2011, 2012, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(2001, coachTeam.getStartYear());
    Assert.assertEquals(2012, coachTeam.getEndYear());

    // 添加后需要去掉现有区间，如[1990,1995]和[2001,2012]，当添加了[1995,2001]时，必须减少区间并进行合并
    coachTeam = new CoachTeam(1990, 1995, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(6, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(1995, 2001, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());// 剩下5个
    Assert.assertEquals(new CoachTeam(1990, 2012, "coachName", "teamName"), coachTeam);
    Assert.assertEquals(1990, coachTeam.getStartYear());
    Assert.assertEquals(2012, coachTeam.getEndYear());

    // 试试右边，如[1990,2012]和[2017,2020]，当添加了[2011,2018]时，必须减少区间并进行合并
    coachTeam = new CoachTeam(2017, 2020, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(6, catalog.getCoachTeams().size());

    coachTeam = new CoachTeam(2011, 2018, "coachName", "teamName");
    coachTeam = catalog.addCoachTeam(coachTeam);
    Assert.assertEquals(5, catalog.getCoachTeams().size());// 剩下5个
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

    // 年份没有交集
    arenaTeam = new ArenaTeam(1950, 1960, "arenaName", "teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(4, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(2050, 2060, "arenaName", "teamName");
    catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());

    // 年份有交集，合并记录的时间
    // 左边端点相交
    arenaTeam = new ArenaTeam(2002, 2003, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2002, 2003, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2002, arenaTeam.getStartYear());
    Assert.assertEquals(2010, arenaTeam.getEndYear());

    // 左边部分相交（此时时间为[2002,2010]）
    arenaTeam = new ArenaTeam(2001, 2005, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2001, 2005, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2010, arenaTeam.getEndYear());

    // 重合（此时时间为[2001,2010]）
    arenaTeam = new ArenaTeam(2001, 2010, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2001, 2010, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2010, arenaTeam.getEndYear());

    // 右边部分相交（此时时间为[2001,2010]）
    arenaTeam = new ArenaTeam(2005, 2011, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2005, 2011, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2011, arenaTeam.getEndYear());

    // 右边端点相交（此时时间为[2001,2011]）
    arenaTeam = new ArenaTeam(2011, 2012, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());
    Assert.assertEquals(new ArenaTeam(2011, 2012, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(2001, arenaTeam.getStartYear());
    Assert.assertEquals(2012, arenaTeam.getEndYear());

    // 添加后需要去掉现有区间，如[1990,1995]和[2001,2012]，当添加了[1995,2001]时，必须减少区间并进行合并
    arenaTeam = new ArenaTeam(1990, 1995, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(6, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(1995, 2001, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());// 剩下5个
    Assert.assertEquals(new ArenaTeam(1990, 2012, "arenaName", "teamName"), arenaTeam);
    Assert.assertEquals(1990, arenaTeam.getStartYear());
    Assert.assertEquals(2012, arenaTeam.getEndYear());

    // 试试右边，如[1990,2012]和[2017,2020]，当添加了[2011,2018]时，必须减少区间并进行合并
    arenaTeam = new ArenaTeam(2017, 2020, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(6, catalog.getArenaTeams().size());

    arenaTeam = new ArenaTeam(2011, 2018, "arenaName", "teamName");
    arenaTeam = catalog.addArenaTeam(arenaTeam);
    Assert.assertEquals(5, catalog.getArenaTeams().size());// 剩下5个
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

    // 年份没有交集
    location = new Location(1950, 1960, "locationName", "arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(5, catalog.getLocations().size());

    location = new Location(2050, 2060, "locationName", "arenaName", 19500);
    catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());

    // 年份有交集，合并记录的时间
    // 左边端点相交
    location = new Location(2002, 2003, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2002, 2003, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2002, location.getStartYear());
    Assert.assertEquals(2010, location.getEndYear());

    // 左边部分相交（此时时间为[2002,2010]）
    location = new Location(2001, 2005, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2001, 2005, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2010, location.getEndYear());

    // 重合（此时时间为[2001,2010]）
    location = new Location(2001, 2010, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2001, 2010, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2010, location.getEndYear());

    // 右边部分相交（此时时间为[2001,2010]）
    location = new Location(2005, 2011, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2005, 2011, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2011, location.getEndYear());

    // 右边端点相交（此时时间为[2001,2011]）
    location = new Location(2011, 2012, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());
    Assert.assertEquals(new Location(2011, 2012, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(2001, location.getStartYear());
    Assert.assertEquals(2012, location.getEndYear());

    // 添加后需要去掉现有区间，如[1990,1995]和[2001,2012]，当添加了[1995,2001]时，必须减少区间并进行合并
    location = new Location(1990, 1995, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(7, catalog.getLocations().size());

    location = new Location(1995, 2001, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());// 剩下6个
    Assert.assertEquals(new Location(1990, 2012, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(1990, location.getStartYear());
    Assert.assertEquals(2012, location.getEndYear());

    // 试试右边，如[1990,2012]和[2017,2020]，当添加了[2011,2018]时，必须减少区间并进行合并
    location = new Location(2017, 2020, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(7, catalog.getLocations().size());

    location = new Location(2011, 2018, "locationName", "arenaName", 19500);
    location = catalog.addLocation(location);
    Assert.assertEquals(6, catalog.getLocations().size());// 剩下6个
    Assert.assertEquals(new Location(1990, 2020, "locationName", "arenaName", 19500), location);
    Assert.assertEquals(1990, location.getStartYear());
    Assert.assertEquals(2020, location.getEndYear());
  }
}
