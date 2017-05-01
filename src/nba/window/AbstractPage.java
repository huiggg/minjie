package nba.window;

import javax.swing.*;

public abstract class AbstractPage extends JFrame {
  private static final long serialVersionUID = 1L;

  public final void run() {
    initPage(); // �Դ��ڵĳ�ʼ������
    init(); // ��ʼ������
    regitstComponent(); // ע�����
    addListener(); // ��Ӽ�����
  }

  protected abstract void addListener();

  protected abstract void regitstComponent();

  protected abstract void init();

  protected void initPage() {
    setSize(870, 610);
    setLocationRelativeTo(null); // ���þ�����ʾ
    setVisible(true);// Ĭ�Ͽɼ�
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �رյ�Ĭ�ϲ������رմ���
  }
}
