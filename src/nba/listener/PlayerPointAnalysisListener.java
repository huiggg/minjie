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

public class PlayerPointAnalysisListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    List<Player> players = Catalog.getInstance().getPlayers();
    double[] points = new double[players.size()];

    //累加所有球员的总得分
    for (int index = 0; index < players.size(); ++index) {
      double point = 0;
      for (Season season : players.get(index).getSeasons()) {
        point += season.getPoint();
      }
      points[index] = point;
    }

    //所有得分排序，统计各个分数各有多少人
    Arrays.sort(points);
    double[] nums = new double[points.length];
    String[] pointNums = new String[points.length];

    double pre = -1;
    int index = -1;
    for (double point : points) {
      if (pre != point) {
        pre = point;
        ++index;
        pointNums[index] = String.valueOf(point);
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
