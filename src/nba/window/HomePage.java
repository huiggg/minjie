package nba.window;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nba.r.R;

import nba.listener.ExitActionListener;
import nba.listener.OpenActionListener;

public class HomePage extends AbstractPage {

  private static final long serialVersionUID = 1L;

  private JMenuItem openItem;
  private JMenuItem exitItem;

  @Override
  protected void addListener() {
    openItem.addActionListener(new OpenActionListener());
    exitItem.addActionListener(new ExitActionListener());
  }

  @Override
  protected void regitstComponent() {
    R r = R.getInstance();
    r.registObject("window", this);
  }

  @Override
  protected void init() {
    JMenuBar menubar = new JMenuBar();

    JMenu menu = new JMenu("菜单");

    openItem = new JMenuItem("导入");
    menu.add(openItem);
    exitItem = new JMenuItem("退出");
    menu.add(exitItem);

    menubar.add(menu);
    setJMenuBar(menubar);

    ImageIcon background = new ImageIcon("res/nba7.jpg");
    JLabel label = new JLabel(background);
    add(label);

    validate();
  }

  @Override
  protected void initPage() {
    super.initPage();
    setTitle("NBA信息统计");
    setSize(880, 600);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
