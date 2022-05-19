package rml.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierUser;
import rml.service.ICashierUserService;
import rml.utils.CookieTool;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class CashierUserController {

  private static Logger logger = Logger.getLogger(CashierUserController.class);

  @Autowired
  private ICashierUserService cashierUserService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierUser model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    if (model.getOrderBy() == null) {
      model.setOrderBy("createTime desc");
    }
    PageInfo<CashierUser> result = cashierUserService.getList(model);
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierUser model) {
    try {
      boolean b = cashierUserService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete( CashierUser model) {
    try {
      boolean b = cashierUserService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update( CashierUser model) {
    try {
      boolean b = cashierUserService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public Result login(HttpServletResponse response,  CashierUser model) {
    try {
      CashierUser info = new CashierUser();
      info.setAccount(model.getAccount());
      CashierUser b = cashierUserService.login(info);
      if (b == null) {
        return new Result(ResultEnum.FAIL, "用户不存在!",false);
      }
      if (!b.getPassword().equals(model.getPassword())) {
        return new Result(ResultEnum.FAIL, "密码错误!",false);
      }
      info.setAccount(null);
      info.setId(b.getId());
      info.setLoginTime(new Date());
      cashierUserService.update(info);
      CookieTool.addCookie(response, CookieTool.KEY, URLEncoder.encode(JSON.toJSONString(b), "UTF-8"), 60 * 60 * 24 * 7);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      logger.error("登陆异常",e);
      return new Result(ResultEnum.FAIL, false);
    }
  }
}
