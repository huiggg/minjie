package nba.test;

import nba.model.Arena;
import nba.model.ArenaTeam;
import nba.model.Coach;
import nba.model.CoachTeam;
import nba.model.Location;
import nba.model.Player;
import nba.model.Season;
import nba.model.Team;
import nba.parser.DataParser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataParserTest {

  private static DataParser dataParser;

  String str_normal = "1,Quincy Acy,2013-2014,23,TOR,NBA,1,9,2013-2014,NBA,"
      + "Toronto Raptors,D. Casey ,1996,2014,19,1435,584,851,0,Tor"
      + "onto Raptors,1999-2014,AirCanadaCentre,TorontoON,19500";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    dataParser = new DataParser();
  }

  @Test
  // 要求传入[1,2,3]三个字段，其中Season可以是单个年份
  public void parsePlayerTest() {
    // 正常情况
    dataParser.parseData(str_normal);
    Player player = dataParser.getPlayer();

    Assert.assertEquals(player.getBirth(), 1991);
    Assert.assertEquals(player.getName(), "Quincy Acy");

    // 可选情况1
    String str_other = ",Quincy Acy,2013-2014,23,,,,,,,,,,,,,,,,,,,,";
    dataParser.parseData(str_other);
    player = dataParser.getPlayer();

    Assert.assertEquals(player.getBirth(), 1991);
    Assert.assertEquals(player.getName(), "Quincy Acy");

    // 可选情况2：Season Single Number
    String str_other2 = ",Quincy Acy,2014,23,,,,,,,,,,,,,,,,,,,,";
    dataParser.parseData(str_other2);
    player = dataParser.getPlayer();

    Assert.assertEquals(player.getBirth(), 1991);
    Assert.assertEquals(player.getName(), "Quincy Acy");

    // 异常情况：缺少关键数据
    String str_exception =
        "1,,,23,TOR,NBA,1,9,2013-2014,NBA,"
            + "Toronto Raptors,D. Casey ,1996,2014,19,1435,584,851,0,Tor"
            + "onto Raptors,1999-2014,AirCanadaCentre,TorontoON,19500";
    dataParser.parseData(str_exception);
    player = dataParser.getPlayer();

    Assert.assertEquals(player, null);
  }

  @Test
  // 要求传入[1,2,3,4,6,7]六个字段
  public void parseSeasonTest() {
    // 正常情况
    dataParser.parseData(str_normal);
    Season season = dataParser.getSeason();

    Assert.assertEquals(season.getYear(), 2014);
    Assert.assertEquals(season.getPlayerName(), "Quincy Acy");
    Assert.assertEquals(season.getTeamAbbr(), "TOR");
    Assert.assertEquals(season.getGameNum(), 1);
    Assert.assertEquals(season.getPoint(), 9);
    Assert.assertEquals(season.getPlayerBirth(), 1991);

    // 可选情况
    String str_other = ",Quincy Acy,2013-2014,23,TOR,,1,9,,,,,,,,,,,,,,,,";
    dataParser.parseData(str_other);
    season = dataParser.getSeason();

    Assert.assertEquals(season.getYear(), 2014);
    Assert.assertEquals(season.getPlayerName(), "Quincy Acy");
    Assert.assertEquals(season.getTeamAbbr(), "TOR");
    Assert.assertEquals(season.getGameNum(), 1);
    Assert.assertEquals(season.getPoint(), 9);
    Assert.assertEquals(season.getPlayerBirth(), 1991);

    // 异常情况：缺少关键数据
    String str_exception =
        "1,,,,,NBA,,,2013-2014,NBA,Toronto Raptors,D. Casey ,1996,2014,19,1435,584,851,0,Tor"
            + "onto Raptors,1999-2014,AirCanadaCentre,TorontoON,19500";
    dataParser.parseData(str_exception);
    season = dataParser.getSeason();

    Assert.assertEquals(season, null);
  }

  @Test
  // 要求传入[4, 10, 12, 14, 15, 16, 18]七个字段
  public void parseTeamTest() {
    // 正常情况
    dataParser.parseData(str_normal);
    Team team = dataParser.getTeam();

    Assert.assertEquals(team.getName(), "Toronto Raptors");
    Assert.assertEquals(team.getAbbr(), "TOR");
    Assert.assertEquals(team.getBirth(), 1996);
    Assert.assertEquals(team.getAge(), 19);
    Assert.assertEquals(team.getChampNum(), 0);
    Assert.assertEquals(team.getGameNum(), 1435);
    Assert.assertEquals(team.getWinNum(), 584);

    // 可选情况
    String str_other = ",,,,TOR,,,,,,Toronto Raptors,,1996,,19,1435,584,851,0,,,,,";
    dataParser.parseData(str_other);
    team = dataParser.getTeam();

    Assert.assertEquals(team.getName(), "Toronto Raptors");
    Assert.assertEquals(team.getAbbr(), "TOR");
    Assert.assertEquals(team.getBirth(), 1996);
    Assert.assertEquals(team.getAge(), 19);
    Assert.assertEquals(team.getChampNum(), 0);
    Assert.assertEquals(team.getGameNum(), 1435);
    Assert.assertEquals(team.getWinNum(), 584);

    // 异常情况
    String str_exception =
        "1,Quincy Acy,2013-2014,23,,NBA,1,9,2013-2014,NBA,,D. Casey ,"
            + ",2014,,,,,,Toronto Raptors,1999-2014,AirCanadaCentre,TorontoON,19500";
    dataParser.parseData(str_exception);
    team = dataParser.getTeam();

    Assert.assertEquals(team, null);
  }

  @Test
  // 要求传入[11]一个字段，有可能有多个Coach，多次返回
  public void parseCoachTest() {
    dataParser.clear();

    // 正常情况
    dataParser.parseData(str_normal);
    Coach coach = dataParser.getCoach();

    Assert.assertEquals(coach.getName(), "D. Casey");

    // 可选情况
    String str_other = ",,,,,,,,,,,D. Casey ,,,,,,,,,,,,";
    dataParser.parseData(str_other);
    coach = dataParser.getCoach();

    Assert.assertEquals(coach.getName(), "D. Casey");

    // 异常情况
    String str_exception =
        "1,Quincy Acy,2013-2014,23,TOR,NBA,1,9,2013-2014,NBA,"
            + "Toronto Raptors, ,1996,2014,19,1435,584,851,0,Tor"
            + "onto Raptors,1999-2014,AirCanadaCentre,TorontoON,19500";
    dataParser.parseData(str_exception);
    coach = dataParser.getCoach();
    Assert.assertEquals(coach, null);

    // 多个Coach对象的情况
    String str_muti = ",,,,,,,,,,,M. Brown  B. Bickerstaff  M. D'Antoni ,,,,,,,,,,,,";
    dataParser.parseData(str_muti);
    coach = dataParser.getCoach();
    Assert.assertEquals(coach.getName(), "M. Brown");

    dataParser.parseData(str_exception);
    coach = dataParser.getCoach();
    Assert.assertEquals(coach.getName(), "B. Bickerstaff");

    dataParser.parseData(str_exception);
    coach = dataParser.getCoach();
    Assert.assertEquals(coach.getName(), "M. D'Antoni");

    dataParser.parseData(str_exception);
    coach = dataParser.getCoach();
    Assert.assertEquals(coach, null);
  }

  @Test
  // 要求传入[8,10,11]三个字段
  public void parseCoachTeamTest() {
    dataParser.clear();

    // 正常情况
    dataParser.parseData(str_normal);
    CoachTeam coachTeam = dataParser.getCoachTeam();

    Assert.assertEquals(coachTeam.getCoachName(), "D. Casey");
    Assert.assertEquals(coachTeam.getTeamName(), "Toronto Raptors");
    Assert.assertEquals(coachTeam.getStartYear(), 2013);
    Assert.assertEquals(coachTeam.getEndYear(), 2014);

    // 可选情况
    String str_other = ",,,,,,,,2013-2014,,Toronto Raptors,D. Casey ,,,,,,,,,,,,";
    dataParser.parseData(str_other);
    coachTeam = dataParser.getCoachTeam();

    Assert.assertEquals(coachTeam.getCoachName(), "D. Casey");
    Assert.assertEquals(coachTeam.getTeamName(), "Toronto Raptors");
    Assert.assertEquals(coachTeam.getStartYear(), 2013);
    Assert.assertEquals(coachTeam.getEndYear(), 2014);

    // 异常情况
    String str_exception =
        "1,Quincy Acy,2013-2014,23,TOR,NBA,1,9,,NBA,, ,1996,2014,19,1435,584,851,0,Tor"
            + "onto Raptors,1999-2014,AirCanadaCentre,TorontoON,19500";
    dataParser.parseData(str_exception);
    coachTeam = dataParser.getCoachTeam();

    Assert.assertEquals(coachTeam, null);

    // 多个Coach对象的情况
    String str_muti =
        ",,,,,,,,2012-2013,,Los Angeles Lakers,M. Brown  B. Bickerstaff  M. D'Antoni ,,,,,,,,,,,,";
    dataParser.parseData(str_muti);
    coachTeam = dataParser.getCoachTeam();
    Assert.assertEquals(coachTeam.getCoachName(), "M. Brown");
    Assert.assertEquals(coachTeam.getTeamName(), "Los Angeles Lakers");
    Assert.assertEquals(coachTeam.getStartYear(), 2012);
    Assert.assertEquals(coachTeam.getEndYear(), 2013);

    dataParser.parseData(str_exception);
    coachTeam = dataParser.getCoachTeam();
    Assert.assertEquals(coachTeam.getCoachName(), "B. Bickerstaff");
    Assert.assertEquals(coachTeam.getTeamName(), "Los Angeles Lakers");
    Assert.assertEquals(coachTeam.getStartYear(), 2012);
    Assert.assertEquals(coachTeam.getEndYear(), 2013);

    dataParser.parseData(str_exception);
    coachTeam = dataParser.getCoachTeam();
    Assert.assertEquals(coachTeam.getCoachName(), "M. D'Antoni");
    Assert.assertEquals(coachTeam.getTeamName(), "Los Angeles Lakers");
    Assert.assertEquals(coachTeam.getStartYear(), 2012);
    Assert.assertEquals(coachTeam.getEndYear(), 2013);

    dataParser.parseData(str_exception);
    coachTeam = dataParser.getCoachTeam();
    Assert.assertEquals(coachTeam, null);
  }

  @Test
  // 要求传入[21]一个字段
  public void parseArenaTest() {
    // 正常情况
    dataParser.parseData(str_normal);
    Arena arena = dataParser.getArena();

    Assert.assertEquals(arena.getName(), "AirCanadaCentre");

    // 可选情况
    String str_other = ",,,,,,,,,,,,,,,,,,,,,AirCanadaCentre,,";
    dataParser.parseData(str_other);
    arena = dataParser.getArena();

    Assert.assertEquals(arena.getName(), "AirCanadaCentre");

    // 异常情况
    String str_exception =
        "1,Quincy Acy,2013-2014,23,TOR,NBA,1,9,2013-2014,NBA,"
            + "Toronto Raptors,D. Casey ,1996,2014,19,1435,584,851,0,Tor"
            + "onto Raptors,1999-2014,,TorontoON,19500";
    dataParser.parseData(str_exception);
    arena = dataParser.getArena();

    Assert.assertEquals(arena, null);
  }

  @Test
  // 要求传入[19,20,21]三个字段
  public void parseArenaTeamTest() {
    // 正常情况
    dataParser.parseData(str_normal);
    ArenaTeam arenaTeam = dataParser.getArenaTeam();

    Assert.assertEquals(arenaTeam.getArenaName(), "AirCanadaCentre");
    Assert.assertEquals(arenaTeam.getTeamName(), "Toronto Raptors");
    Assert.assertEquals(arenaTeam.getStartYear(), 1999);
    Assert.assertEquals(arenaTeam.getEndYear(), 2014);

    // 可选情况
    String str_other = ",,,,,,,,,,,,,,,,,,,,Toronto Raptors,1999-2014,AirCanadaCentre,,";
    dataParser.parseData(str_other);
    arenaTeam = dataParser.getArenaTeam();

    Assert.assertEquals(arenaTeam.getArenaName(), "AirCanadaCentre");
    Assert.assertEquals(arenaTeam.getTeamName(), "Toronto Raptors");
    Assert.assertEquals(arenaTeam.getStartYear(), 1999);
    Assert.assertEquals(arenaTeam.getEndYear(), 2014);

    // 异常情况
    String str_exception =
        "1,Quincy Acy,2013-2014,23,TOR,NBA,1,9,2013-2014,NBA,"
            + "Toronto Raptors,D. Casey ,1996,2014,19,1435,584,851,0,,,,TorontoON,19500";
    dataParser.parseData(str_exception);
    arenaTeam = dataParser.getArenaTeam();

    Assert.assertEquals(arenaTeam, null);
  }

  @Test
  // 要求传入[20,21,22,23]四个字段
  public void parseLocationTest() {
    // 正常情况
    dataParser.parseData(str_normal);
    Location location = dataParser.getLocation();

    Assert.assertEquals(location.getName(), "TorontoON");
    Assert.assertEquals(location.getArenaName(), "AirCanadaCentre");
    Assert.assertEquals(location.getCapacity(), 19500);
    Assert.assertEquals(location.getEndYear(), 2014);
    Assert.assertEquals(location.getStartYear(), 1999);

    // 可选情况
    String str_other = ",,,,,,,,,,,,,,,,,,,,,1999-2014,AirCanadaCentre,TorontoON,19500";
    dataParser.parseData(str_other);
    location = dataParser.getLocation();

    Assert.assertEquals(location.getName(), "TorontoON");
    Assert.assertEquals(location.getArenaName(), "AirCanadaCentre");
    Assert.assertEquals(location.getCapacity(), 19500);
    Assert.assertEquals(location.getEndYear(), 2014);
    Assert.assertEquals(location.getStartYear(), 1999);

    // 异常情况
    String str_exception =
        "1,Quincy Acy,2013-2014,23,TOR,NBA,1,9,2013-2014,NBA,"
            + "Toronto Raptors,D. Casey ,1996,2014,19,1435,584,851,0,Toronto Raptors,,,,";
    dataParser.parseData(str_exception);
    location = dataParser.getLocation();

    Assert.assertEquals(location, null);
  }
}
