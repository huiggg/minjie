package nba.test;

import nba.model.Arena;
import nba.model.ArenaTeam;
import nba.model.Location;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArenaTest {
  private static Arena arena;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    arena = new Arena("AirCanadaCentre");
  }

  @Test
  public void arenaEqualTest() {
    Assert.assertEquals(arena, arena);

    Arena arena2 = new Arena("AirCanadaCentre");
    Assert.assertEquals(arena, arena2);
  }

  @Test
  public void arenaNotEqualTest() {
    Arena arena2 = new Arena();
    Assert.assertNotEquals(arena, arena2);

    arena2 = new Arena("another_arenaName");
    Assert.assertNotEquals(arena, arena2);
  }

  @Test
  public void arenaAddLocationTest() {
    Location location = new Location();
    arena.addLocation(location);
    Assert.assertEquals(1, arena.getLocations().size());

    location = new Location(1996, 1997, "TorontoON", "AirCanadaCentre", 19500);
    arena.addLocation(location);
    Assert.assertEquals(2, arena.getLocations().size());

    location = new Location();
    arena.addLocation(location);
    Assert.assertEquals(2, arena.getLocations().size());
  }

  @Test
  public void arenaAddArenaTeamTest() {
    ArenaTeam arenaTeam = new ArenaTeam();
    arena.addArenaTeam(arenaTeam);
    Assert.assertEquals(1, arena.getArenaTeams().size());

    arenaTeam = new ArenaTeam(1996, 1997, "Toronto Raptors", "AirCanadaCentre");
    arena.addArenaTeam(arenaTeam);
    Assert.assertEquals(2, arena.getArenaTeams().size());

    arenaTeam = new ArenaTeam();
    arena.addArenaTeam(arenaTeam);
    Assert.assertEquals(2, arena.getArenaTeams().size());
  }
}
