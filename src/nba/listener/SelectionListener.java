package nba.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import nba.r.R;
import nba.window.AbstractPage;

@SuppressWarnings("rawtypes")
public class SelectionListener implements ActionListener {

  private Class classAnalysis;
  private Class classDetail;
  private JRadioButton btnAnalysis;

  public SelectionListener(Class classAnalysis, Class classDetail) {
    this.classAnalysis = classAnalysis;
    this.classDetail = classDetail;
    R r = R.getInstance();
    btnAnalysis = (JRadioButton) r.getObject("btnAnalysis");
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    try {
      if (btnAnalysis.isSelected())
        ((AbstractPage) classAnalysis.newInstance()).run();
      else
        ((AbstractPage) classDetail.newInstance()).run();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

}
