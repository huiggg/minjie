package nba.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NBASearcher {

  public static <T> List<T> findAll(List<T> list, String methodName, Object target) {
    if (list == null || list.size() == 0) {
      return null;
    }
    List<T> ret = new ArrayList<T>();
    Class<? extends Object> c = list.get(0).getClass();
    try {
      Method method = null;
      method = c.getMethod(methodName);
      for (T t : list)
        if (method.invoke(t).equals(target)) {
          ret.add(t);
        }
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
    return ret;
  }

  public static <T> T find(List<T> list, String methodName, Object target) {
    if (list == null || list.size() == 0) {
      return null;
    }
    Class<? extends Object> c = list.get(0).getClass();
    try {
      Method method = null;
      method = c.getMethod(methodName);
      for (T t : list)
        if (method.invoke(t).equals(target)) {
          return t;
        }
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
    return null;
  }
}
