package nba.window;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import nba.listener.ArenaDetailListener;
import nba.listener.ArenaMouseListener;
import nba.model.Arena;
import nba.model.Catalog;
import nba.r.R;

public class EachArena extends AbstractPage {

	  private static final long serialVersionUID = 1L;

	  private JTextField  textArena;// ������������
	  private JButton btnEnsure;// "ȷ������"��ť
	  private JTextField ArenaNameTx;
	  private List<Arena> arenas;
	  private JList ArenaJList;
	  
	  public EachArena() {
	    super();
	    setTitle("��������ϸ��Ϣ");
	    setSize(880, 610);
	  }

	  protected void init() {
		  setLayout(null);
		  JLabel searchLabel = new JLabel("������");
		  searchLabel.setBounds(20, 20, 50, 50);
		  add(searchLabel);
		    
		  textArena = new JTextField(58);
		  textArena .setBounds(60, 35, 655, 25);
		  add(textArena);
		    
		  btnEnsure = new JButton("ȷ��");
		  btnEnsure.setBounds(745, 33, 65, 26);   
		  getRootPane().setDefaultButton(btnEnsure);
		  add(btnEnsure); 
		  
		  addArenaJlist(); //����������б�
	     
	      JLabel ArenaName = new JLabel("���������� ��");
	      ArenaName.setBounds(380,85, 90, 50);
	      add(ArenaName);
	      
	      ArenaNameTx = new JTextField();
	      ArenaNameTx.setEditable(false);
	      ArenaNameTx.setBounds(470, 98, 150, 25);
	      add(ArenaNameTx);
	      
	      JLabel ArenaLocation = new JLabel("λ �� �� Ϣ ��");
	      ArenaLocation.setBounds(380,130, 90, 50);
	      add(ArenaLocation);
	     
	      JLabel ArenaTeam = new JLabel("�� �� �� �� ��");
	      ArenaTeam.setBounds(380,280, 90, 50);
	      add(ArenaTeam);
	     
	  }
	  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addArenaJlist() {
		    arenas = Catalog.getInstance().getArenas();
		    
		    List<String> list = new ArrayList<String>();
		    for(Arena arena:arenas)
		    	list.add(arena.getName());
		    String[] name = (String[])list.toArray(new String[list.size()]);
		    
			ArenaJList = new JList(name);
		    JScrollPane JScrollPane=new JScrollPane(ArenaJList);
		    JScrollPane.setBounds(60,85,220,420);
		    add(JScrollPane);
		    
	  }
	
	  
	  
	  protected void addListener() {
	    btnEnsure.addActionListener(new ArenaDetailListener());
	    ArenaJList.addMouseListener(new ArenaMouseListener());
	  }
	  
	  protected void regitstComponent() {
		  R.getInstance().registObject("textArena", textArena);
		  R.getInstance().registObject("ArenaNameTx", ArenaNameTx);
		  R.getInstance().registObject("EachArena",this);
	  }
	  
}

