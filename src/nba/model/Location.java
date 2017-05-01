package nba.model;

public class Location {
  private String name;// 地點名
  private int startYear;// 開始時間
  private int endYear;// 結束時間 [startYear,endYear]
  private int capacity;// 容量
  private String arenaName;// 體育場名

  public Location(int start, int end, String name, String arenaName, int capacity) {
    startYear = start;
    endYear = end;
    this.name = name;
    this.capacity = capacity;
    this.arenaName = arenaName;
  }

  public Location() {
    this(0, 0, "", "", 0);
  }

  @Override
  public boolean equals(Object obj) { // 当两个位置的地点、球场名、容量相同，且有相交年份时，认为两个位置相等（可以合并）
    if (obj != null && obj.getClass().equals(this.getClass())) {
      Location location = (Location) obj;
      boolean flag =
          location.getName().equals(name) && location.getCapacity() == capacity
              && location.getArenaName().equals(arenaName);
      flag = flag && isIntersect(location);
      return flag;
    }
    return false;
  }

  // 判断两个对象的年份是否有交集
  private boolean isIntersect(Location location) {
    return isIntersect(startYear, endYear, location.getStartYear(), location.getEndYear());
  }

  private boolean isIntersect(int start1, int end1, int start2, int end2) {
    boolean flag = false;
    flag = flag || (start1 >= start2 && start1 <= end2);
    flag = flag || (end1 >= start2 && end1 <= end2);
    flag = flag || (start1 < start2 && end1 > end2);
    return flag;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + name.hashCode();
    result = result * 31 + arenaName.hashCode();
    result = result * 31 + capacity;
    return result;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(int startYear) {
    this.startYear = startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public void setEndYear(int endYear) {
    this.endYear = endYear;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getArenaName() {
    return arenaName;
  }

  public void setArenaName(String arenaName) {
    this.arenaName = arenaName;
  }
}
