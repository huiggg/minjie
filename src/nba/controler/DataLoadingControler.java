package nba.controler;

import nba.loader.DataLoader;
import nba.model.Arena;
import nba.model.ArenaTeam;
import nba.model.Catalog;
import nba.model.Coach;
import nba.model.CoachTeam;
import nba.model.Location;
import nba.model.Player;
import nba.model.Season;
import nba.model.Team;
import nba.parser.DataParser;

public class DataLoadingControler {

  private Player player;
  private Team team;
  private Coach coach;
  private Arena arena;
  private CoachTeam coachTeam;
  private ArenaTeam arenaTeam;
  private Location location;
  private Season season;

  private DataLoader dataLoader;// 数据加载器
  private DataParser dataParser;// 数据解析器

  public DataLoadingControler(DataLoader dataLoader, DataParser dataParser) {
    this.dataLoader = dataLoader;
    this.dataParser = dataParser;
  }

  public boolean loadData(String dataSource) {
    Catalog catalog = Catalog.getInstance();
    if (!dataLoader.loadFrom(dataSource)) { // 打開文件
      return false;
    }
    while (dataLoader.hasNext()) {
      String string = dataLoader.next(); // 連續讀取每一行
      if (!dataParser.parseData(string)) { // 交給解析器解析，解析器解析出每個對象
        return false;
      }

      player = dataParser.getPlayer(); // 從解析器中讀取出剛解析出來的每個對象
      team = dataParser.getTeam();
      coach = dataParser.getCoach();
      coachTeam = dataParser.getCoachTeam();
      arena = dataParser.getArena();
      arenaTeam = dataParser.getArenaTeam();
      season = dataParser.getSeason();
      location = dataParser.getLocation();

      catalog.addPlayer(player); // 将对象添加进总表，由总表对象负责判断是否已添加过。
      catalog.addSeason(season); // 如果添加过，则返回之前添加的对象的引用
      catalog.addTeam(team);
      catalog.addCoach(coach);
      catalog.addArena(arena);
      catalog.addLocation(location);
      catalog.addCoachTeam(coachTeam);
      catalog.addArenaTeam(arenaTeam);

    }

    catalog.setAssociations(); // 设置对象之间的联系
    return true;
  }
}
