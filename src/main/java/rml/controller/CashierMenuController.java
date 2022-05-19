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
import rml.model.CashierMenu;
import rml.model.CashierUser;
import rml.service.ICashierMenuService;
import rml.service.ICashierUserService;
import rml.utils.CookieTool;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class CashierMenuController {

  private static Logger logger = Logger.getLogger(CashierMenuController.class);

  @Autowired
  private ICashierMenuService cashierMenuService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierMenu model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    if (model.getOrderBy() == null) {
      model.setOrderBy("sort asc");
    }
    switch ((int)model.getMenuType()){
      case 1:model.setMenuType(1);break;
      case 2:model.setMenuType(null);model.setUiType(2);break;
      case 3:model.setMenuType(null);model.setType(3);break;
    }

    PageInfo<CashierMenu> result = cashierMenuService.getList(model);
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierMenu model) {
    try {
      boolean b = cashierMenuService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierMenu model) {
    try {
      boolean b = cashierMenuService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierMenu model) {
    try {
      boolean b = cashierMenuService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

}
