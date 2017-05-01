package nba.model;

public class CoachTeam {
  private int startYear;// ��ʼ���
  private int endYear;// ������� [startYear,endYear]
  private String coachName;// ����
  private String teamName;// ���

  public CoachTeam(int start, int end, String coach, String team) {
    startYear = start;
    endYear = end;
    this.coachName = coach;
    this.teamName = team;
  }

  public CoachTeam() {
    this(0, 0, "", "");
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      CoachTeam coachTeam = (CoachTeam) obj;
      boolean flag =
          coachTeam.getCoachName().equals(coachName) && coachTeam.getTeamName().equals(teamName);
      flag = flag && isIntersect(coachTeam);
      return flag;
    }
    return false;
  }

  // �ж��������������Ƿ��н���
  private boolean isIntersect(CoachTeam coachTeam) {
    return isIntersect(startYear, endYear, coachTeam.getStartYear(), coachTeam.getEndYear());
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
    result = result * 31 + coachName.hashCode();
    result = result * 31 + teamName.hashCode();
    return result;
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

  public String getCoachName() {
    return coachName;
  }

  public void setCoachName(String coach) {
    this.coachName = coach;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String team) {
    this.teamName = team;
  }


}
