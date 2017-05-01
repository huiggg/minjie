package nba.model;

import java.util.ArrayList;
import java.util.List;

public class Coach {
  private String name;// ΩÃ¡∑–’√˚
  private List<CoachTeam> coachTeams;

  public Coach(String name) {
    this.name = name;
    coachTeams = new ArrayList<CoachTeam>();
  }

  public Coach() {
    this("");
  }

  public void addCoachTeam(CoachTeam coachTeam) {
    if (coachTeam != null && !coachTeams.contains(coachTeam)) coachTeams.add(coachTeam);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      Coach coach = (Coach) obj;
      return coach.getName().equals(name);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + name.hashCode();
    return result;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CoachTeam> getCoachTeams() {
    return coachTeams;
  }

  public void setCoachTeams(List<CoachTeam> coachTeams) {
    this.coachTeams = coachTeams;
  }

}
