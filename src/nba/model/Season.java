package nba.model;

public class Season {
  private String playerName;// 赛季所属球员
  private int playerBirth;// 球员出生年份
  private String teamAbbr;// 赛季所属球队
  private int point;// 该赛季得分
  private int game_num;// 该赛季参加的场数
  private int year;// 该赛季年份（结束年）

  public Season(String player, String team, int playerBirth, int point, int game_num, int year) {
    this.playerName = player;
    this.teamAbbr = team;
    this.point = point;
    this.game_num = game_num;
    this.year = year;
    this.playerBirth = playerBirth;
  }

  public Season() {
    this("", "", 0, 0, 0, 0);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      Season season = (Season) obj;
      return season.getPlayerName().equals(playerName) && season.getYear() == year
          && season.getPlayerBirth() == playerBirth && season.getTeamAbbr().equals(teamAbbr);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + playerName.hashCode();
    result = result * 31 + teamAbbr.hashCode();
    result = result * 31 + year;
    result = result * 31 + playerBirth;
    return result;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }

  public int getGameNum() {
    return game_num;
  }

  public void setGameNum(int game_num) {
    this.game_num = game_num;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getPlayerBirth() {
    return playerBirth;
  }

  public void setPlayerBirth(int playerBirth) {
    this.playerBirth = playerBirth;
  }

  public String getTeamAbbr() {
    return teamAbbr;
  }

  public void setTeamAbbr(String teamAbbr) {
    this.teamAbbr = teamAbbr;
  }

  // 用于排序
  public String getPlayerBirthAndName() {
    return playerName + playerBirth;
  }
}
