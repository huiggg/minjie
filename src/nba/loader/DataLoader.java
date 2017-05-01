package nba.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {

  private BufferedReader bufIn;
  private String[] fields;
  private int index;
  private int length;

  public boolean loadFrom(File file) {
    if (!file.exists()) {
      return false;
    }
    try {
      bufIn = new BufferedReader(new FileReader(file));

      readLineNum();
      readFieldsLine();

      index = 0;
    } catch (IOException e) {
      return false;
    } catch(NumberFormatException e){
      return false;
    }
    return true;
  }

  private void readFieldsLine() throws IOException {
    fields = bufIn.readLine().split(",");
    for (int i = 0; i < fields.length; ++i)
      fields[i] = fields[i].trim();
  }

  private void readLineNum() throws NumberFormatException, IOException {
    length = Integer.parseInt(bufIn.readLine());// 读取第一行，文件行数
  }

  public boolean loadFrom(String fileName) {
    return loadFrom(new File(fileName));
  }

  public String[] getFields() {
    return fields;
  }

  public boolean hasNext() {
    return index != length;
  }

  public String next() {
    ++index;
    try {
      return bufIn.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
