package nba.window;

import javax.swing.*;

public abstract class AbstractPage extends JFrame {
  private static final long serialVersionUID = 1L;

  public final void run() {
    initPage(); // 对窗口的初始化操作
    init(); // 初始化界面
    regitstComponent(); // 注册组件
    addListener(); // 添加监听器
  }

  protected abstract void addListener();

  protected abstract void regitstComponent();

  protected abstract void init();

  protected void initPage() {
    setSize(870, 610);
    setLocationRelativeTo(null); // 设置居中显示
    setVisible(true);// 默认可见
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 关闭的默认操作：关闭窗口
  }
}
