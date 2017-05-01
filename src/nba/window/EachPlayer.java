package nba.window;

import javax.swing.*;

import nba.r.R;
import nba.listener.PlayerDetailListener;
import nba.listener.PlayerMouseListener;
import nba.model.Catalog;
import nba.model.Player;

import java.util.ArrayList;
import java.util.List;

public class EachPlayer extends AbstractPage {
	private static final long serialVersionUID = 1L;

	  private JTextField textPlayer;// ������������
	  private JButton btnEnsure;// "ȷ������"��ť
	  private List<Player> players;
	  private JTextField playerNameTx;
	  private JTextField playerBirthTx;
	  private JList PlayerJlist;
	  
	  
	  public EachPlayer() {
	    super();
	    setTitle("��Ա��ϸ��Ϣ");
	    setSize(880, 610);
	    
	  }
	  
	  protected void init() {
		  setLayout(null);

		    JLabel searchLabel = new JLabel("������");
		    searchLabel.setBounds(20, 20, 50, 50);
		    add(searchLabel);
		    
		    textPlayer = new JTextField(58);
		    textPlayer.setBounds(60, 35, 655, 25);
		    add(textPlayer);
		    
		    btnEnsure = new JButton("ȷ��");
		    btnEnsure.setBounds(745, 33, 65, 26);   
		    getRootPane().setDefaultButton(btnEnsure);
		    add(btnEnsure);
		    
		    addPlayerJlist();//�����Ա����
		    
		    JLabel name = new JLabel("�� Ա �� �� �� ");
		    name.setBounds(380,85, 90, 50);
		    add(name);
		    
		    playerNameTx = new JTextField();
		    playerNameTx.setEditable(false);
		    playerNameTx.setBounds(460, 98, 150, 25);
		    add(playerNameTx);
		 
		    JLabel birth = new JLabel("�� �� �� �� �� ");
		    birth.setBounds(380,135,90,50);
		    add(birth);
		    
		    playerBirthTx = new JTextField();
		    playerBirthTx.setEditable(false);
		    playerBirthTx.setBounds(460, 150, 150, 25);
		    add(playerBirthTx);
		    
		    JLabel gameInformation = new JLabel("�� �� �� Ϣ �� ");
		    gameInformation.setBounds(380, 185, 90, 50);
		    add(gameInformation);
		  
	  }
	  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addPlayerJlist() {
		    players = Catalog.getInstance().getPlayers();
		    
		    List<String> list = new ArrayList<String>();
		    for(Player player:players)
		    	list.add(player.getName());
		    String[] name = (String[])list.toArray(new String[list.size()]);
		    
			
			PlayerJlist=new JList(name);
		    JScrollPane JScrollPane=new JScrollPane(PlayerJlist);
		    JScrollPane.setBounds(60,85,220,420);
		    add(JScrollPane);
		    
	  }
	  

	  
	  protected void addListener() {
		    btnEnsure.addActionListener(new PlayerDetailListener());
		    PlayerJlist.addMouseListener(new PlayerMouseListener());
		    
	  }
	  
	  
	  protected void regitstComponent() {
			R.getInstance().registObject("textPlayer", textPlayer);
		    R.getInstance().registObject("playerNameTx", playerNameTx);
		    R.getInstance().registObject("playerBirthTx", playerBirthTx);
		    R.getInstance().registObject("EachPlayer", this);
		    
	 }
}
	

  

  

