package nba.window;

import java.awt.Color;
import javax.swing.JButton;

import nba.listener.PlayerAgeAnalysisListener;
import nba.listener.PlayerGameAnalysisListener;
import nba.listener.PlayerPointAnalysisListener;
import nba.listener.PlayerSeasonAnalysisListener;
import nba.r.R;

public class Allplayer extends AbstractPage {

  private static final long serialVersionUID = 1L;
  private JButton btnPlayerAge;
  private JButton btnPlayerGames;
  private JButton btnPlayerPTS;
  private JButton btnPlayerSeason;

  public Allplayer() {
    super();
    setTitle("�������");
    setSize(880, 610);
    Color c = new Color(202, 225, 255);
    getContentPane().setBackground(c);
  }

  protected void regitstComponent() {
	  R.getInstance().registObject("AllPlayer", this);
  }

  protected void init() {
    setLayout(null);

    btnPlayerAge = new JButton("��Ա����ֲ�");
    btnPlayerAge.setBounds(80, 80, 200, 60);
    add(btnPlayerAge);

    btnPlayerGames = new JButton("��Ա�������ηֲ�");
    btnPlayerGames.setBounds(80, 230, 200, 60);
    add(btnPlayerGames);

    btnPlayerPTS = new JButton("��Ա�÷ֲַ�");
    btnPlayerPTS.setBounds(80, 380, 200, 60);
    add(btnPlayerPTS);

    btnPlayerSeason = new JButton("��Ա�������ȷֲ�");
    btnPlayerSeason.setBounds(500, 230, 200, 60);
    add(btnPlayerSeason);

  }

  protected void addListener() {
    btnPlayerAge.addActionListener(new PlayerAgeAnalysisListener());
    btnPlayerGames.addActionListener(new PlayerGameAnalysisListener());
    btnPlayerPTS.addActionListener(new PlayerPointAnalysisListener());
    btnPlayerSeason.addActionListener(new PlayerSeasonAnalysisListener());
  }
}
