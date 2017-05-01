package nba.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
 

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
 
 
import javax.swing.JTextField;
 

import nba.model.Arena;
import nba.model.ArenaTeam;
import nba.model.Catalog;
import nba.model.Location;
 
import nba.r.R;
import nba.util.NBASearcher;

public class ArenaDetailListener implements ActionListener {
	  private JTextField textinput;
	  private JTextField ArenaNameTx;
	  private List<Location> Location;
	  private List<ArenaTeam> ArenaTeam;
	  private JFrame ArenaJf;
	  
	  public void actionPerformed(ActionEvent arg0) {
	    R r = R.getInstance();
	    textinput = (JTextField) r.getObject("textArena");
	    ArenaNameTx = (JTextField) r.getObject("ArenaNameTx");
	    ArenaJf = (JFrame) r.getObject("EachArena");
	    
	    if (textinput.getText().isEmpty()) {
	      JOptionPane.showMessageDialog(null, "���������������֣�", "����", JOptionPane.WARNING_MESSAGE);
	    } else {
	    Arena arena =
	          NBASearcher.find(Catalog.getInstance().getArenas(), "getName", textinput.getText());
	      if (arena == null) {
	        JOptionPane.showMessageDialog(null, "���޴���������", "���ҽ��", JOptionPane.PLAIN_MESSAGE);
	      } else {
	    	  setArenaName(arena);
	    	  LocationTable(arena);
	    	  ArenaTeamTable(arena);
	      }
	    }
	  }
	  
	  
	  private void setArenaName(Arena arena) {
		    ArenaNameTx.setText(arena.getName());
	  }
	  
	  
	  private void LocationTable(Arena arena) {
		    Location = arena.getLocations();
		    String[][] data = new String[Location.size()][3];
		    String[] columnNames = {"ʹ�����", "���ڵ�","����"};

		    for (int i = 0; i < data.length; ++i) {
		      Location loc = Location.get(i);
		      data[i][0] = loc.getStartYear() + "-" + loc.getEndYear();
		      data[i][1] = loc.getName();
		      data[i][2] = loc.getCapacity()+ "";
		
		    }
       
		    JTable table = new JTable(data, columnNames);
		   /* DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		    tcr.setHorizontalAlignment(SwingConstants.CENTER);
		    table.setDefaultRenderer(Object.class, tcr);*/
		    JScrollPane JSP = new JScrollPane(table);
		    JSP.setBounds(470, 150, 360, 100);
		    ArenaJf.add(JSP);
	  }
	  
	  private void ArenaTeamTable(Arena arena) {
		    ArenaTeam = arena.getArenaTeams();
		    String[][] data = new String[ArenaTeam.size()][2];
		    String[] columnNames = {"ʹ�����","�������"};

		    for (int i = 0; i < data.length; ++i) {
		      ArenaTeam atm = ArenaTeam.get(i);
		      data[i][0] = atm.getStartYear() + "-" + atm.getEndYear();
		      data[i][1] = atm.getTeamName();
		    }
     
		    JTable table = new JTable(data, columnNames);
		    JScrollPane JSP = new JScrollPane(table);
		    JSP.setBounds(470, 300, 360, 200);
		    ArenaJf.add(JSP);
		  }
}
  
