package nba.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import nba.r.R;

import nba.controler.DataLoadingControler;
import nba.loader.DataLoader;
import nba.parser.DataParser;
import nba.window.QueryPage;


public class OpenActionListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int state = jfc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {
      DataLoader dataLoader = new DataLoader();
      DataParser dataParser = new DataParser();
      DataLoadingControler dataLoadingControler = new DataLoadingControler(dataLoader, dataParser);

      File file = jfc.getSelectedFile();
      dataLoadingControler.loadData(file.getAbsolutePath());
      new QueryPage().run();

      closeHomePage();
    }
  }

  private void closeHomePage() {
    R r = R.getInstance();
    JFrame homePage = (JFrame) r.getObject("window");
    homePage.dispose();
  }
}
