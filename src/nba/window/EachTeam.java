package nba.window;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import nba.listener.TeamDetailListener;
import nba.listener.TeamMouseListener;
import nba.model.Catalog;
import nba.model.Team;
import nba.r.R;

public class EachTeam extends AbstractPage {

	  private static final long serialVersionUID = 1L;

	  private JTextField textTeam;// ������������
	  private JButton btnEnsure;// "ȷ������"��ť
	  private JTextField textShowTname; //��ʾ�������
	  private JTextField textShowTabbrname;
	  private JTextField textTeamBirth;
	  private JTextField textTeamAge;
	  private JTextField textTeamGame;
	  private JTextField textTeamWin;
	  private JTextField textTeamChampion;
	  private JList teamJlist;
	  private List<Team> teams;
	  
	 
	  public EachTeam() {
	    super();
	    setTitle("�����ϸ��Ϣ");
	  }

	  protected void init() {
		  setLayout(null);
			JLabel search = new JLabel("������");
	        search.setBounds(20, 20, 50, 50);
		    add(search);
		    textTeam = new JTextField(58);
		    textTeam.setBounds(60, 35, 655, 25);
		    add(textTeam);

		    btnEnsure = new JButton("ȷ��");
		    btnEnsure.setBounds(745, 33, 65, 26);
		    getRootPane().setDefaultButton(btnEnsure);
		    add(btnEnsure);
		
		    
		    JLabel teamName = new JLabel("���ȫ�ƣ�");
		    teamName.setBounds(420,85, 90, 50);
		    add(teamName);
		    textShowTname = new JTextField(40);
		    textShowTname.setBounds(490, 97, 320, 25);
		    textShowTname.setEditable(false);
		    add(textShowTname);
		    
		   JLabel birth = new JLabel("������ݣ�");
		   birth.setBounds(560,120,90,50);
		   add(birth);
		   textTeamBirth = new JTextField(40);
		   textTeamBirth.setBounds(630,135,50,25);
		   textTeamBirth.setEditable(false);
		   add(textTeamBirth);
		    
		   JLabel abbr = new JLabel("��Ӽ�ƣ�");
		   abbr.setBounds(420,120,90,50);
		   add(abbr);
		   textShowTabbrname =  new JTextField(40);
		   textShowTabbrname.setBounds(490,135,50,25);
		   textShowTabbrname.setEditable(false);
		   add(textShowTabbrname);
		    
		   JLabel teamAge = new JLabel("��������:");
		   teamAge.setBounds(700,120,90,50);
		   add(teamAge);
		   textTeamAge = new JTextField(40);
		   textTeamAge.setBounds(760,135,50,25);
		   textTeamAge.setEditable(false);
		   add(textTeamAge);
		   
		   JLabel game = new JLabel("����������");
		   game.setBounds(420, 158, 90, 50);
		   add(game);
		   textTeamGame = new JTextField(20);
		   textTeamGame.setBounds(490, 173, 50, 25);
		   textTeamGame.setEditable(false);
		   add(textTeamGame);
		   
		   JLabel win = new JLabel("��ʤ������");
		   win.setBounds(560,158,90,50);
		   add(win);
		   textTeamWin = new JTextField(40);
		   textTeamWin.setBounds(630,173,50,25);
		   textTeamWin.setEditable(false);
		   add(textTeamWin);
		   
		   
		   JLabel champion = new JLabel("��ڴ���:");
		   champion.setBounds(700,158,90,50);
		   add(champion);
		   textTeamChampion = new JTextField(40);
		   textTeamChampion.setBounds(760,173,50,25);
		   textTeamChampion.setEditable(false);
		   add(textTeamChampion);
		   
		   JLabel teach = new JLabel("���������");
		   teach.setBounds(420,198,90,50);
		   add(teach);
		   
		   addTeamJlist();
	  }
	
	  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void addTeamJlist() {
		    teams = Catalog.getInstance().getTeams();
		    
		    List<String> list = new ArrayList<String>();
		    for(Team team:teams)
		    
		    list.add(team.getName());
		    String[] name = (String[])list.toArray(new String[list.size()]);
		    
			 
			teamJlist=new JList(name);
		    JScrollPane JScrollPane=new JScrollPane(teamJlist);
		    JScrollPane.setBounds(60,85,220,420);
		    add(JScrollPane);
		    
	  }
	  
	  protected void addListener() {
	    btnEnsure.addActionListener(new TeamDetailListener());
	    teamJlist.addMouseListener(new TeamMouseListener());
	    textTeam.addActionListener(new TeamDetailListener());
	  }


	  protected void regitstComponent() {
	    R.getInstance().registObject("teamJf", this);
	    R.getInstance().registObject("textTeam", textTeam);
	    R.getInstance().registObject("textShowTname", textShowTname);
	    R.getInstance().registObject("textShowTabbrname", textShowTabbrname);
	    R.getInstance().registObject("textTeamBirth", textTeamBirth);
	    R.getInstance().registObject("textTeamAge", textTeamAge);
	    R.getInstance().registObject("textTeamGame", textTeamGame);
	    R.getInstance().registObject("textTeamWin",textTeamWin);
	    R.getInstance().registObject("textTeamChampion", textTeamChampion);
	  }

}
