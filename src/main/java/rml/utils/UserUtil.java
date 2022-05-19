package rml.utils;

import rml.model.CashierUser;

import java.util.regex.Pattern;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.utils
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月21日 22:02
 */
public class UserUtil {
  protected static final ThreadLocal<CashierUser> LOCAL_USER = new ThreadLocal();

  public UserUtil() {
  }

  public static void setLocalUser(CashierUser page) {
    LOCAL_USER.set(page);
  }

  public static CashierUser getLocalUser() {
    return (CashierUser)LOCAL_USER.get();
  }

  public static void clearUser() {
    LOCAL_USER.remove();
  }

  public static boolean isInteger(String str) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    return pattern.matcher(str).matches();
  }
}
