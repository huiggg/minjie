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
import nba.model.Coach;
import nba.model.CoachTeam;
import nba.r.R;
import nba.util.NBASearcher;

public class CoachMouseListener implements MouseListener{


	 private JTextField coachNameTx;
	 private JFrame coachJf;
	 private List<CoachTeam> coachTeams;
	
	
	@SuppressWarnings("rawtypes")
	public void mouseClicked(MouseEvent e) {
		R r = R.getInstance();
		 
		coachNameTx = (JTextField) r.getObject("textShowCname");
		coachJf = (JFrame) r.getObject("coachJf"); 
		JList CList = (JList) e.getSource();
		if(e.getClickCount() == 2){
			String Cname = CList.getSelectedValue().toString();
			Coach coach =NBASearcher.find(Catalog.getInstance().getCoachs(), "getName",Cname);
		   
			coachNameTx.setText(coach.getName());
		    	coachTeams = coach.getCoachTeams();
			        
			        String[][] data = new String[coachTeams.size()][2];
				    String[] columnNames = {"执教年份", "执教球队"};

				    for (int i = 0; i < data.length; ++i) {
				        CoachTeam s = coachTeams.get(i);
				        data[i][0] = s.getStartYear() - 1 + "-" + s.getEndYear();
				        data[i][1] = s.getTeamName();
				        
				      }
				    
				    JTable table = new JTable(data, columnNames);
				    JScrollPane JspCtable = new JScrollPane(table);
				    JspCtable.setBounds(420, 190, 400, 300);
				    
				    coachJf.add(JspCtable);
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

				    
				    
	
     