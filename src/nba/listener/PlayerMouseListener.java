package nba.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nba.model.Catalog;
import nba.model.Player;
import nba.model.Season;
import nba.r.R;
import nba.util.NBASearcher;

public class PlayerMouseListener implements MouseListener{
	private JTextField playerNameTx;
	private JTextField playerBirthTx;
	private List<Season> seasons;
	private JFrame PlayerJf;
	

	@Override
	public void mouseClicked(MouseEvent e) {
		R r = R.getInstance();
	    playerNameTx = (JTextField) r.getObject("playerNameTx");
	    playerBirthTx = (JTextField) r.getObject("playerBirthTx");
	    PlayerJf = (JFrame) r.getObject("EachPlayer");
	    
	    @SuppressWarnings("rawtypes")
		JList PList = (JList) e.getSource();
	    if(e.getClickCount() == 2){
		    String Pname = PList.getSelectedValue().toString();
			Player player =
	           NBASearcher.find(Catalog.getInstance().getPlayers(), "getName",Pname);
	        setPlayerName(player);
  	        setPlayerBirth(player);
	        fillTable(player);
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

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
