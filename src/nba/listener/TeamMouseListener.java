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

import nba.model.CoachTeam;
import nba.model.Team;
import nba.r.R;
import nba.util.NBASearcher;

public class TeamMouseListener implements MouseListener{

	
	
	@SuppressWarnings("rawtypes")
	public void mouseClicked(MouseEvent e) {
		 R r = R.getInstance();
		 JTextField textShowTname = (JTextField) r.getObject("textShowTname");
		 JTextField textShowTabbrname = (JTextField) r.getObject("textShowTabbrname");
		 JTextField textTeamBirth = (JTextField) r.getObject("textTeamBirth");
		 JTextField textTeamAge = (JTextField) r.getObject("textTeamAge");
		 JTextField textTeamGame = (JTextField) r.getObject("textTeamGame");
		 JTextField textTeamWin = (JTextField) r.getObject("textTeamWin");
		 JTextField textTeamChampion = (JTextField) r.getObject("textTeamChampion");
		 JFrame teamJf = (JFrame) r.getObject("teamJf");
		
		 JList TList = (JList) e.getSource();
		 if(e.getClickCount() == 2){
		     String Tname = TList.getSelectedValue().toString();
			   //Coach coach =NBASearcher.find(Catalog.getInstance().getCoachs(), "getName",Cname);
			
			 Team team =NBASearcher.find(Catalog.getInstance().getTeams(), "getName", Tname);
		   
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



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
