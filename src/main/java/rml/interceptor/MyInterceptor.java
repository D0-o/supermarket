package rml.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierUser;
import rml.utils.CookieTool;
import rml.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.interceptor
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月21日 21:38
 */
public class MyInterceptor implements HandlerInterceptor {
  /**
   * 在处理方法之前执行，一般用来做一些准备工作：比如日志，权限检查
   * 如果返回false 表示被拦截，将不会执行处理方法
   * 返回true继续执行处理方法
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    try {
      if(request.getRequestURI().contains("login")){
        return true;
      }
      String cashierName = CookieTool.getCookieValueByName(request, CookieTool.KEY);
      if (cashierName != null && !"".equals(cashierName)) {
        String json = URLDecoder.decode(cashierName, "UTF-8");
        CashierUser user = JSON.parseObject(json, CashierUser.class);
        //验证session中是否有用户存在 如果有  继续执行
        if (user != null) {
          UserUtil.setLocalUser(user);
          return true;
        }
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    //执行跳转到登录页面
    response.setCharacterEncoding("UTF-8");
    response.getWriter().print(JSON.toJSONString(new Result(ResultEnum.BEOVERDUE, ResultEnum.BEOVERDUE.getMessage())));
    return false;
  }

  /**
   * 在处理方法执行之后，在渲染视图执行之前执行，一般用来做一些清理工作
   */
  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView mv)
      throws Exception {
    System.out.println("执行postHandler");
  }

  /**
   * 在视图渲染后执行  一般用来释放资源
   */
  @Override
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
      throws Exception {
    UserUtil.clearUser();
  }
}
