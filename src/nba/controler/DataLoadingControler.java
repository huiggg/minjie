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

  private DataLoader dataLoader;// ���ݼ�����
  private DataParser dataParser;// ���ݽ�����

  public DataLoadingControler(DataLoader dataLoader, DataParser dataParser) {
    this.dataLoader = dataLoader;
    this.dataParser = dataParser;
  }

  public boolean loadData(String dataSource) {
    Catalog catalog = Catalog.getInstance();
    if (!dataLoader.loadFrom(dataSource)) { // ���_�ļ�
      return false;
    }
    while (dataLoader.hasNext()) {
      String string = dataLoader.next(); // �B�m�xȡÿһ��
      if (!dataParser.parseData(string)) { // ���o������������������������ÿ������
        return false;
      }

      player = dataParser.getPlayer(); // �Ľ��������xȡ�������������ÿ������
      team = dataParser.getTeam();
      coach = dataParser.getCoach();
      coachTeam = dataParser.getCoachTeam();
      arena = dataParser.getArena();
      arenaTeam = dataParser.getArenaTeam();
      season = dataParser.getSeason();
      location = dataParser.getLocation();

      catalog.addPlayer(player); // ��������ӽ��ܱ����ܱ�������ж��Ƿ�����ӹ���
      catalog.addSeason(season); // �����ӹ����򷵻�֮ǰ��ӵĶ��������
      catalog.addTeam(team);
      catalog.addCoach(coach);
      catalog.addArena(arena);
      catalog.addLocation(location);
      catalog.addCoachTeam(coachTeam);
      catalog.addArenaTeam(arenaTeam);

    }

    catalog.setAssociations(); // ���ö���֮�����ϵ
    return true;
  }
}
