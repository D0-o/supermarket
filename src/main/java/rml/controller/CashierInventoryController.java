package rml.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierInventory;
import rml.model.CashierInventoryGoods;
import rml.service.ICashierInventoryService;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class CashierInventoryController {

  private static Logger logger = Logger.getLogger(CashierInventoryController.class);

  @Autowired
  private ICashierInventoryService cashierInventoryService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierInventory model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    if (model.getOrderBy() == null) {
      model.setOrderBy("createTime desc");
    }
    PageInfo<CashierInventory> result = null;
    try {
      result = cashierInventoryService.getList(model);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(String order) {
    try {
      List<CashierInventoryGoods> model = JSON.parseArray(order, CashierInventoryGoods.class);
      boolean b = cashierInventoryService.insertList(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierInventory model) {
    try {
      boolean b = cashierInventoryService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierInventory model) {
    try {
      boolean b = cashierInventoryService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierInventoryGoods model) {
    try {
      List<CashierInventoryGoods> b = cashierInventoryService.getGoods(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/check", method = RequestMethod.POST)
  @ResponseBody
  public Result check(CashierInventoryGoods model) {
    try {
      boolean b = cashierInventoryService.getRepeat();
      if(b){
        return new Result(ResultEnum.CHEACKED_TODAY, false);
      }
      List<CashierInventoryGoods> list = cashierInventoryService.check(model);
      return new Result(ResultEnum.SUCCESS, list);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/checkList", method = RequestMethod.POST)
  @ResponseBody
  public Result checkList(CashierInventoryGoods model) {
    try {
      if (model.getPageNo() == null) {
        model.setPageNo(1);
      }
      if (model.getPageSize() == null) {
        model.setPageSize(17);
      }
      PageInfo<CashierInventoryGoods> list = cashierInventoryService.checkList(model);
      return new Result(ResultEnum.SUCCESS, list);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/sum", method = RequestMethod.POST)
  @ResponseBody
  public Result sum(CashierInventoryGoods model) {
    try {
      CashierInventoryGoods cig = cashierInventoryService.sum(model);
      return new Result(ResultEnum.SUCCESS, cig);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

}
