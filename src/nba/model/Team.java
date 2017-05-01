package nba.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private String name; // 球队名称
  private String abbreviate;// 球队简称
  private int birth_year;// 球队诞生年份
  private int age;// 球队存活时间
  private int game_num;// 球队参加过的比赛场数
  private int win_num;// 球队赢球的场数
  private int champion_num;// 球队赢得冠军的次数
  private List<Season> seasons;// 赛季列表
  private List<CoachTeam> coachTeams;
  private List<ArenaTeam> arenaTeams;

  public Team(String name, String abbreviate, int birth_year, int age, int game_num, int win_num,
      int champion_num) {
    this.name = name;
    this.abbreviate = abbreviate;
    this.birth_year = birth_year;
    this.age = age;
    this.game_num = game_num;
    this.win_num = win_num;
    this.champion_num = champion_num;

    seasons = new ArrayList<Season>();
    coachTeams = new ArrayList<CoachTeam>();
    arenaTeams = new ArrayList<ArenaTeam>();
  }

  public Team() {
    this("", "", 0, 0, 0, 0, 0);
  }

  public void addSeason(Season season) {
    if (season != null && !seasons.contains(season)) seasons.add(season);
  }

  public void addCoachTeam(CoachTeam coachTeam) {
    if (coachTeam != null && !coachTeams.contains(coachTeam)) coachTeams.add(coachTeam);
  }

  public void addArenaTeam(ArenaTeam arenaTeam) {
    if (arenaTeam != null && !arenaTeams.contains(arenaTeam)) arenaTeams.add(arenaTeam);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      Team team = (Team) obj;
      return team.getName().equals(name) && team.getBirth() == birth_year;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + name.hashCode();
    result = result * 31 + birth_year;
    return result;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setAbbr(String abbr) {
    abbreviate = abbr;
  }

  public String getAbbr() {
    return abbreviate;
  }

  public void setWinNum(int i) {
    win_num = i;
  }

  public int getWinNum() {
    return win_num;
  }

  public void setGameNum(int i) {
    game_num = i;
  }

  public int getGameNum() {
    return game_num;
  }

  public int getBirth() {
    return birth_year;
  }

  public void setBirth(int year) {
    birth_year = year;
  }

  public void setAge(int i) {
    age = i;
  }

  public int getAge() {
    return age;
  }

  public void setChampNum(int i) {
    champion_num = i;
  }

  public int getChampNum() {
    return champion_num;
  }

  public List<Season> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public void setCoachTeams(List<CoachTeam> coachTeams) {
    this.coachTeams = coachTeams;
  }

  public List<CoachTeam> getCoachTeams() {
    return coachTeams;
  }

  public List<ArenaTeam> getArenaTeams() {
    return arenaTeams;
  }

  public void setArenaTeams(List<ArenaTeam> arenaTeams) {
    this.arenaTeams = arenaTeams;
  }

}
