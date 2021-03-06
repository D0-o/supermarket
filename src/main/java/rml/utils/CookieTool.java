package rml.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTool {

  public static String KEY = "cashierKey";

  /**
   * 添加cookie
   *
   * @param response
   * @param name Key
   * @param value Value
   * @param maxAge 有效时间
   */
  public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    //cookie.setDomain("localhost"); // cookie作用域
    response.addCookie(cookie);
  }

  /**
   * 检索所有Cookie封装到Map集合
   *
   * @param request
   * @return
   */
  public static Map<String, String> readCookieMap(HttpServletRequest request) {
    Map<String, String> cookieMap = new HashMap<String, String>();
    Cookie[] cookies = request.getCookies();
    if (null != cookies) {
      for (Cookie cookie : cookies) {
        cookieMap.put(cookie.getName(), cookie.getValue());
      }
    }
    return cookieMap;
  }

  /**
   * 通过Key获取Value
   *
   * @param request
   * @param name Key
   * @return Value
   */
  public static String getCookieValueByName(HttpServletRequest request, String name) {
    Map<String, String> cookieMap = readCookieMap(request);
    if (cookieMap.containsKey(name)) {
      String cookieValue = (String) cookieMap.get(name);
      return cookieValue;
    } else {
      return null;
    }
  }

}