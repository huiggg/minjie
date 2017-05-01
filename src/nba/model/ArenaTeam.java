package nba.model;

public class ArenaTeam {
  private String arenaName;// 球场
  private String teamName;// 球队
  private int startYear;// 开始年份
  private int endYear;// 结束年份 [startYear,endYear]

  public ArenaTeam(int start, int end, String arena, String team) {
    startYear = start;
    endYear = end;
    this.arenaName = arena;
    this.teamName = team;
  }

  public ArenaTeam() {
    this(0, 0, "", "");
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      ArenaTeam arenaTeam = (ArenaTeam) obj;
      boolean flag = arenaTeam.getArenaName().equals(arenaName) && arenaTeam.getTeamName().equals(teamName);
      flag = flag && isIntersect(arenaTeam);
      return flag;
    }
    return false;
  }

  // 判断两个对象的年份是否有交集
  private boolean isIntersect(ArenaTeam arenaTeam) {
    return isIntersect(startYear, endYear, arenaTeam.getStartYear(), arenaTeam.getEndYear());
  }

  private boolean isIntersect(int start1, int end1, int start2, int end2) {
    boolean flag = false;
    flag = flag || (start1 >= start2 && start1 <= end2);
    flag = flag || (end1 >= start2 && end1 <= end2);
    flag = flag || (start1 < start2 && end1 > end2);
    return flag;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + arenaName.hashCode();
    result = result * 31 + teamName.hashCode();
    return result;
  }


  public String getArenaName() {
    return arenaName;
  }

  public void setArenaName(String arena) {
    this.arenaName = arena;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String team) {
    this.teamName = team;
  }

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(int startYear) {
    this.startYear = startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public void setEndYear(int endYear) {
    this.endYear = endYear;
  }

}
