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
import nba.model.Coach;
import nba.model.CoachTeam;
import nba.r.R;
import nba.util.NBASearcher;


public class CoachDetailListener implements ActionListener {

	  private JTextField textinput;
	  
	  private List<CoachTeam> coachTeams;
	  private JTextField coachNameTx;
	  private JFrame coachJf;
	  
	  public void actionPerformed(ActionEvent arg0) {
	    R r = R.getInstance();
	    textinput = (JTextField) r.getObject("textCoach");
	    coachNameTx = (JTextField) r.getObject("textShowCname");
	    
	    coachJf = (JFrame) r.getObject("coachJf");
	    
	    if (textinput.getText().isEmpty()) {
	      JOptionPane.showMessageDialog(null, "请输入教练姓名！", "警告", JOptionPane.WARNING_MESSAGE);
	    } else {
	      
	     Coach coach =
	          NBASearcher.find(Catalog.getInstance().getCoachs(), "getName", textinput.getText());
	      
	      if (coach == null) {
	        JOptionPane.showMessageDialog(null, "查无此人！", "查找结果", JOptionPane.PLAIN_MESSAGE);
	      } else {
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
	  }
}
	        
	        
	       
	    
	  
	
