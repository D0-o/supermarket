package rml.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierOrderGoods;
import rml.service.ICashierOrderGoodsService;
import rml.utils.DES3Util;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class CashierOrderGoodsController {

  private static Logger logger = Logger.getLogger(CashierOrderGoodsController.class);

  @Autowired
  private ICashierOrderGoodsService cashierOrderGoodsService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierOrderGoods model) {
    try {
    if(model.getOrderId() != null && !"".equals(model.getOrderId())){
      model.setOrderId(DES3Util.decode(model.getOrderId()));
    }
    List<CashierOrderGoods> result = cashierOrderGoodsService.getAllList(model);
    return new Result(ResultEnum.SUCCESS, result);
    } catch (Exception e) {
      return new Result(ResultEnum.ORDER_FAIL, false);
    }
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierOrderGoods model) {
    try {
      boolean b = cashierOrderGoodsService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierOrderGoods model) {
    try {
      boolean b = cashierOrderGoodsService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierOrderGoods model) {
    try {
      boolean b = cashierOrderGoodsService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierOrderGoods model) {
    try {
      CashierOrderGoods b = cashierOrderGoodsService.get(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/order", method = RequestMethod.POST)
  @ResponseBody
  public Result order(CashierOrderGoods model) {
    try {
      List<CashierOrderGoods> result = cashierOrderGoodsService.getAllList(model);
      return new Result(ResultEnum.SUCCESS, result);
    } catch (Exception e) {
      return new Result(ResultEnum.ORDER_FAIL, false);
    }
  }

}
