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
import nba.model.CoachTeam;
import nba.model.Team;
import nba.r.R;
import nba.util.NBASearcher;

public class TeamDetailListener implements ActionListener {

	 
	  public void actionPerformed(ActionEvent arg0) {
	    R r = R.getInstance();
	    JTextField textinput = (JTextField) r.getObject("textTeam");
	    JTextField textShowTname = (JTextField) r.getObject("textShowTname");
	    JTextField textShowTabbrname = (JTextField) r.getObject("textShowTabbrname");
	    JTextField textTeamBirth = (JTextField) r.getObject("textTeamBirth");
	    JTextField textTeamAge = (JTextField) r.getObject("textTeamAge");
	    JTextField textTeamGame = (JTextField) r.getObject("textTeamGame");
	    JTextField textTeamWin = (JTextField) r.getObject("textTeamWin");
	    JTextField textTeamChampion = (JTextField) r.getObject("textTeamChampion");
	    JFrame teamJf = (JFrame) r.getObject("teamJf");
	    
	    if (textinput.getText().isEmpty()) {
	      JOptionPane.showMessageDialog(null, "请输入球队名字！", "警告", JOptionPane.WARNING_MESSAGE);
	    } else {
	      
	      Team team =
	          NBASearcher.find(Catalog.getInstance().getTeams(), "getName", textinput.getText());
	      
	      if (team == null) {
	        JOptionPane.showMessageDialog(null, "查无此球队！", "查找结果", JOptionPane.PLAIN_MESSAGE);
	      } else {
	    	 textShowTname.setText(team.getName());
	    	 textShowTabbrname.setText(team.getAbbr());
	    	 
	    	 String birth = team.getBirth() + "";
	    	 textTeamBirth.setText(birth);
	    	 
	    	 String age = team.getAge() + "";
	    	 textTeamAge.setText(age);
	    	 
	    	 String game = team.getGameNum()+ "";
	    	 textTeamGame.setText(game);
	    	 
	    	 String win = team.getWinNum()+"";
	    	 textTeamWin.setText(win);
	    	 
	    	 String champion = team.getChampNum()+"";
	    	 textTeamChampion.setText(champion);
	    	 
	    	 List<CoachTeam> coachTeams = team.getCoachTeams();
		     String[][] data = new String[coachTeams.size()][2];
			 String[] columnNames = {"任职教练", "参赛时间"};

			    for (int i = 0; i < data.length; ++i) {
			        CoachTeam c = coachTeams.get(i);
			        data[i][0] = c.getCoachName();
			        data[i][1] = c.getStartYear() - 1 + "-" + c.getEndYear();
			       
			        
			      }
			    
			   JTable table = new JTable(data, columnNames);
			   JScrollPane JspTtable = new JScrollPane(table);
			    JspTtable.setBounds(420, 245, 395, 250);
			    
			    teamJf.add(JspTtable);
			    
			    
			    
	        
	        }
	    }
	  }
}
	