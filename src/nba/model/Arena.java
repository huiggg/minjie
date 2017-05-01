package nba.model;

import java.util.ArrayList;
import java.util.List;

public class Arena {
  private String name;
  private List<ArenaTeam> arenaTeams;
  private List<Location> locations;

  public Arena(String name) {
    this.name = name;
    arenaTeams = new ArrayList<ArenaTeam>();
    locations = new ArrayList<Location>();
  }

  public Arena() {
    this("");
  }

  public void addArenaTeam(ArenaTeam arenaTeam) {
    if (arenaTeam != null && !arenaTeams.contains(arenaTeam)) arenaTeams.add(arenaTeam);
  }

  public void addLocation(Location location) {
    if (location != null && !locations.contains(location)) locations.add(location);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.getClass().equals(this.getClass())) {
      Arena arena = (Arena) obj;
      return arena.getName().equals(name);
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

  public List<ArenaTeam> getArenaTeams() {
    return arenaTeams;
  }

  public void setArenaTeams(List<ArenaTeam> arenaTeams) {
    this.arenaTeams = arenaTeams;
  }

  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }
}
