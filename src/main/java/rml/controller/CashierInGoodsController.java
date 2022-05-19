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
import rml.model.CashierInGoods;
import rml.service.ICashierInGoodsService;
import rml.utils.UserUtil;

import java.util.List;

@Controller
@RequestMapping("/ingoods")
public class CashierInGoodsController {

  private static Logger logger = Logger.getLogger(CashierInGoodsController.class);

  @Autowired
  private ICashierInGoodsService cashierInGoodsService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierInGoods model) {
    if (model.getGoodsName() != null &&
        !"".equals(model.getGoodsName())
        && UserUtil.isInteger(model.getGoodsName())) {
      String code = model.getGoodsName();
      model.setGoodsCode(code);
      model.setGoodsName(null);
    } else if(model.getGoodsName() != null &&"ZDY".startsWith(model.getGoodsName())){
      String code = model.getGoodsName();
      model.setGoodsCode(code);
      model.setGoodsName(null);
    }
    List<CashierInGoods> result = cashierInGoodsService.getListAll(model);
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierInGoods model) {
    try {
      boolean b = cashierInGoodsService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/addlist", method = RequestMethod.POST)
  @ResponseBody
  public Result addlist(String model) {
    try {
      List<CashierInGoods> list = JSON.parseArray(model,CashierInGoods.class);
      boolean b = cashierInGoodsService.insertList(list);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierInGoods model) {
    try {
      boolean b = cashierInGoodsService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierInGoods model) {
    try {
      boolean b = cashierInGoodsService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierInGoods model) {
    try {
      CashierInGoods b = cashierInGoodsService.get(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/select", method = RequestMethod.POST)
  @ResponseBody
  public Result select(CashierInGoods model) {
    try {
      List<String> list = cashierInGoodsService.select(model);
      return new Result(ResultEnum.SUCCESS, list);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

}
