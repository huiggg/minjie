package nba.model;

import java.util.ArrayList;
import java.util.List;

import nba.association.AssociationManager;

public class Catalog {

  private static Catalog catalog = null;

  private List<Player> players;
  private List<Season> seasons;
  private List<Team> teams;
  private List<Coach> coachs;
  private List<CoachTeam> coachTeams;
  private List<Arena> arenas;
  private List<Location> locations;
  private List<ArenaTeam> arenaTeams;

  private Catalog() {
    players = new ArrayList<Player>();
    seasons = new ArrayList<Season>();
    teams = new ArrayList<Team>();
    coachs = new ArrayList<Coach>();
    coachTeams = new ArrayList<CoachTeam>();
    arenas = new ArrayList<Arena>();
    locations = new ArrayList<Location>();
    arenaTeams = new ArrayList<ArenaTeam>();
  }

  public static Catalog getInstance() {
    if (catalog == null) catalog = new Catalog();
    return catalog;
  }

  public void setAssociations() {
    AssociationManager manager = new AssociationManager();
    manager.setPlayerToSeasonAssociations(players, seasons);
    manager.setTeamToSeasonAssociations(teams, seasons);
    manager.setCoachToTeamAssociations(coachs, coachTeams);
    manager.setTeamToCoachAssociations(teams, coachTeams);
    manager.setArenaToTeamAssociations(arenas, arenaTeams);
    manager.setTeamToArenaAssociations(teams, arenaTeams);
    manager.setArenaToLocationAssociations(arenas, locations);
  }

  public Player addPlayer(Player player) {
    if (player != null) {
      if (!players.contains(player))
        players.add(player);
      else
        player = players.get(players.indexOf(player));
    }
    return player;
  }

  public Season addSeason(Season season) {
    if (season != null) {
      if (seasons.contains(season)) { // 如果已经添加过了，则合并两条记录
        Season s = seasons.get(seasons.indexOf(season));
        s.setPoint(s.getPoint() + season.getPoint());
        s.setGameNum(s.getGameNum() + season.getGameNum());
        season = s;
      } else { // 如果没添加过，则直接添加该记录
        seasons.add(season);
      }
    }
    return season;
  }

  public Team addTeam(Team team) {
    if (team != null) {
      if (!teams.contains(team))
        teams.add(team);
      else
        team = teams.get(teams.indexOf(team));
    }
    return team;
  }

  public Coach addCoach(Coach coach) {
    if (coach != null) {
      if (!coachs.contains(coach))
        coachs.add(coach);
      else
        coach = coachs.get(coachs.indexOf(coach));
    }
    return coach;
  }

  public CoachTeam addCoachTeam(CoachTeam coachTeam) {
    if (coachTeam != null) {
      while (coachTeams.contains(coachTeam)) {
        CoachTeam c = coachTeams.get(coachTeams.indexOf(coachTeam));
        coachTeams.remove(c);
        c.setStartYear(Math.min(c.getStartYear(), coachTeam.getStartYear()));
        c.setEndYear(Math.max(c.getEndYear(), coachTeam.getEndYear()));
        coachTeam = c;
      }
      coachTeams.add(coachTeam);
    }
    return coachTeam;
  }

  public Arena addArena(Arena arena) {
    if (arena != null) {
      if (!arenas.contains(arena))
        arenas.add(arena);
      else
        arenas.get(arenas.indexOf(arena));
    }
    return arena;
  }

  public ArenaTeam addArenaTeam(ArenaTeam arenaTeam) {
    if (arenaTeam != null) {
      while (arenaTeams.contains(arenaTeam)) {
        ArenaTeam a = arenaTeams.get(arenaTeams.indexOf(arenaTeam));
        arenaTeams.remove(a);
        a.setStartYear(Math.min(a.getStartYear(), arenaTeam.getStartYear()));
        a.setEndYear(Math.max(a.getEndYear(), arenaTeam.getEndYear()));
        arenaTeam = a;
      }
      arenaTeams.add(arenaTeam);
    }
    return arenaTeam;
  }

  public Location addLocation(Location location) {
    if (location != null) {
      while (locations.contains(location)) {
        Location l = locations.get(locations.indexOf(location));
        locations.remove(l);
        l.setStartYear(Math.min(l.getStartYear(), location.getStartYear()));
        l.setEndYear(Math.max(l.getEndYear(), location.getEndYear()));
        location = l;
      }
      locations.add(location);
    }
    return location;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public List<Season> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public List<Coach> getCoachs() {
    return coachs;
  }

  public void setCoachs(List<Coach> coachs) {
    this.coachs = coachs;
  }

  public List<CoachTeam> getCoachTeams() {
    return coachTeams;
  }

  public void setCoachTeams(List<CoachTeam> coachTeams) {
    this.coachTeams = coachTeams;
  }

  public List<Arena> getArenas() {
    return arenas;
  }

  public void setArenas(List<Arena> arenas) {
    this.arenas = arenas;
  }

  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }

  public List<ArenaTeam> getArenaTeams() {
    return arenaTeams;
  }

  public void setArenaTeams(List<ArenaTeam> arenaTeams) {
    this.arenaTeams = arenaTeams;
  }
  
}
