package nba.window;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nba.listener.CoachDetailListener;
import nba.listener.CoachMouseListener;

import nba.model.Catalog;
import nba.model.Coach;

import nba.r.R;


public class EachCoach extends AbstractPage {

	  private static final long serialVersionUID = 1L;

	  private JTextField textCoach;// 输入搜索内容
	  private JButton btnEnsure;// "确定搜索"按钮
	  private JTextField textShowCname; //显示教练名字
	  private JTable tableCoach;
	  private JScrollPane JspCtable;
	  private JList coachJlist;
	  private List<Coach> coachs;
	
	  public EachCoach() {
	    super();
	    setTitle("教练详细信息");
	  }
	 
	 

	  protected void init() {
		  
		  setLayout(null);
			JLabel search = new JLabel("搜索：");
	        search.setBounds(20, 20, 50, 50);
		    add(search);
		    textCoach = new JTextField(58);
		    textCoach.setBounds(60, 35, 655, 25);
		    add(textCoach);

		    btnEnsure = new JButton("确定");
		    btnEnsure.setBounds(745, 33, 65, 26);
		    getRootPane().setDefaultButton(btnEnsure);
		    add(btnEnsure);
	    
	    
	    JLabel coachName = new JLabel("教练姓名：");
	    coachName.setBounds(420,85, 90, 50);
	    add(coachName);


	    
	    textShowCname = new JTextField(40);
	    textShowCname.setBounds(490, 97, 150, 25);
	    textShowCname.setEditable(false);
	    add(textShowCname);
	    
	    JLabel teach = new JLabel("执教情况：");
	    teach.setBounds(420,140,90,50);
	    add(teach); 
		    
		    
	    addCoachJlist();
		    
	  }

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addCoachJlist() {
		    coachs = Catalog.getInstance().getCoachs();
		    
		    List<String> list = new ArrayList<String>();
		    for(Coach coach:coachs)
		    
		    	list.add(coach.getName());
		    String[] name = (String[])list.toArray(new String[list.size()]);
		    
		
		    coachJlist=new JList(name);
		    JScrollPane JScrollPane=new JScrollPane(coachJlist);
		    JScrollPane.setBounds(60,85,220,420);
		    add(JScrollPane);
		    
	  }
	  
	  protected void addListener() {
	    btnEnsure.addActionListener(new CoachDetailListener());
	    coachJlist.addMouseListener(new CoachMouseListener());
	    textCoach.addActionListener(new CoachDetailListener());
	  }


	  protected void regitstComponent() {
	    R.getInstance().registObject("textCoach", textCoach);
	    R.getInstance().registObject("JspCtable", JspCtable);
	    R.getInstance().registObject("textShowCname", textShowCname);
	    R.getInstance().registObject("tableCoach", tableCoach);
	    R.getInstance().registObject("coachJf", this);
	    R.getInstance().registObject("coachJlist", coachJlist);
	  }

}
