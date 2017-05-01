package nba.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

public class NBAComparator<T> implements Comparator<T> {
  public static final int FROM_BIG = 0;
  public static final int FROM_SMALL = 1;

  private int flag;
  private String methodName;

  public NBAComparator(String methodName, int flag) {
    this.flag = flag;
    this.methodName = methodName;
  }

  public NBAComparator(String methodName) {
    this(methodName, FROM_SMALL);
  }

  @Override
  public int compare(T arg0, T arg1) {
    Class<? extends Object> c = arg0.getClass();
    try {
      Method method = c.getMethod(methodName);

      Object obj0 = method.invoke(arg0);
      Object obj1 = method.invoke(arg1);

      Method m = obj0.getClass().getMethod("compareTo", obj1.getClass());
      int ret = 0;
      ret = (int) m.invoke(obj0, obj1);

      return flag == FROM_BIG ? -ret : ret;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }
}
