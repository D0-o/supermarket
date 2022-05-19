package rml.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierLossGoods;
import rml.service.ICashierLossGoodsService;
import rml.utils.UserUtil;

import java.util.List;

@Controller
@RequestMapping("/lossgoods")
public class CashierLossGoodsController {

  private static Logger logger = Logger.getLogger(CashierLossGoodsController.class);

  @Autowired
  private ICashierLossGoodsService cashierLossGoodsService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierLossGoods model) {
    if (model.getGoodsName() != null &&
        !"".equals(model.getGoodsName())
        && UserUtil.isInteger(model.getGoodsName())) {
      String code = model.getGoodsName();
      model.setGoodsCode(code);
      model.setGoodsName(null);
    } else if(model.getGoodsName().startsWith("ZDY")){
      String code = model.getGoodsName();
      model.setGoodsCode(code);
      model.setGoodsName(null);
    }
    List<CashierLossGoods> result = cashierLossGoodsService.getListAll(model);
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierLossGoods model) {
    try {
      boolean b = cashierLossGoodsService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/addlist", method = RequestMethod.POST)
  @ResponseBody
  public Result addlist(String model) {
    try {
      List<CashierLossGoods> list = JSON.parseArray(model,CashierLossGoods.class);
      boolean b = cashierLossGoodsService.insertList(list);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierLossGoods model) {
    try {
      boolean b = cashierLossGoodsService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierLossGoods model) {
    try {
      boolean b = cashierLossGoodsService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierLossGoods model) {
    try {
      CashierLossGoods b = cashierLossGoodsService.get(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

}
