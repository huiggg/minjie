package nba.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nba.model.Catalog;
import nba.model.Player;
import nba.model.Season;
import nba.r.R;

import nba.util.NBASearcher;



public class PlayerDetailListener implements ActionListener {

  private JTextField textinput;
  private JTextField playerNameTx;
  private JTextField playerBirthTx;
  private List<Season> seasons;
  private JFrame PlayerJf;
  
  public void actionPerformed(ActionEvent arg0) {
    R r = R.getInstance();
    textinput = (JTextField) r.getObject("textPlayer");
    playerNameTx = (JTextField) r.getObject("playerNameTx");
    playerBirthTx = (JTextField) r.getObject("playerBirthTx");
    PlayerJf = (JFrame) r.getObject("EachPlayer");
    
    if (textinput.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "请输入球员姓名！", "警告", JOptionPane.WARNING_MESSAGE);
    } else {
    Player player =
          NBASearcher.find(Catalog.getInstance().getPlayers(), "getName", textinput.getText());
      if (player == null) {
        JOptionPane.showMessageDialog(null, "查无此人！", "查找结果", JOptionPane.PLAIN_MESSAGE);
      } else {
    	  setPlayerName(player);
    	  setPlayerBirth(player);
  	      fillTable(player);
      }
    }
  }
  
  
  private void setPlayerName(Player player) {
	    playerNameTx.setText(player.getName());
  }
  
  
  
  private void setPlayerBirth(Player player) {
	    String str = player.getBirth() + "";
	    playerBirthTx.setText(str);
  }
  
  private void fillTable(Player player) {
	    seasons = player.getSeasons();
	    String[][] data = new String[seasons.size()][4];
	    String[] columnNames = {"赛季", "参赛场次", "得分", "所属球队缩写"};

	    for (int i = 0; i < data.length; ++i) {
	      Season s = seasons.get(i);
	      data[i][0] = s.getYear() - 1 + "-" + s.getYear();
	      data[i][1] = s.getGameNum() + "";
	      data[i][2] = s.getPoint() + "";
	      data[i][3] = s.getTeamAbbr();
	    }

	    JTable table = new JTable(data, columnNames);
	    JScrollPane JSP = new JScrollPane(table);
	    JSP.setBounds(460, 200, 360, 300);
	    PlayerJf.add(JSP);
	  }
}
