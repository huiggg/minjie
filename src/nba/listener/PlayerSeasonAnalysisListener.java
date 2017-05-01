package nba.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import nba.diagram.BarDiagram;
import nba.diagram.DiagramFactory;
import nba.model.Catalog;
import nba.model.Season;
import nba.util.NBAComparator;

public class PlayerSeasonAnalysisListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    double[] nums = new double[100];
    String[] years = new String[100];

    List<Season> seasons = Catalog.getInstance().getSeasons();
    NBAComparator<Season> comparator = new NBAComparator<Season>("getYear");
    seasons.sort(comparator);

    int year = 0;
    int index = -1;
    for (Season season : seasons) {
      if (year != season.getYear()) {
        year = season.getYear();
        ++index;
        years[index] = String.valueOf(year);
      }
      ++nums[index];
    }

    double[] values = new double[index + 1];
    String[] keys = new String[index + 1];
    System.arraycopy(years, 0, keys, 0, index + 1);
    System.arraycopy(nums, 0, values, 0, index + 1);

    BarDiagram b = DiagramFactory.createBarDiagram(values, keys);
    b.draw();
  }

}
