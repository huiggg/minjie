package nba.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import nba.diagram.BarDiagram;
import nba.diagram.DiagramFactory;
import nba.model.Catalog;
import nba.model.Player;
import nba.model.Season;

public class PlayerGameAnalysisListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    List<Player> players = Catalog.getInstance().getPlayers();
    double[] games = new double[players.size()];

    //累加所有球员各自的场次种树
    for (int index = 0; index < players.size(); ++index) {
      double game = 0;
      for (Season season : players.get(index).getSeasons()) {
        game += season.getGameNum();
      }
      games[index] = game;
    }

    //所有得分排序，统计各个分数各有多少人
    Arrays.sort(games);
    double[] nums = new double[games.length];
    String[] pointNums = new String[games.length];

    double pre = -1;
    int index = -1;
    for (double game : games) {
      if (pre != game) {
        pre = game;
        ++index;
        pointNums[index] = String.valueOf(game);
      }
      ++nums[index];
    }

    double[] values = new double[index + 1];
    String[] keys = new String[index + 1];
    System.arraycopy(pointNums, 0, keys, 0, index + 1);
    System.arraycopy(nums, 0, values, 0, index + 1);

    BarDiagram b = DiagramFactory.createBarDiagram(values, keys);
    b.draw();
  }

}
