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
import rml.model.CashierOrder;
import rml.model.CashierPayType;
import rml.service.ICashierOrderService;
import rml.utils.DES3Util;

import java.util.List;

@Controller
@RequestMapping("/order")
public class CashierOrderController {

  private static Logger logger = Logger.getLogger(CashierOrderController.class);

  @Autowired
  private ICashierOrderService cashierOrderService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierOrder model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    if (model.getOrderBy() == null) {
      model.setOrderBy("orderTime desc");
    }
    PageInfo<CashierOrder> result = null;
    try {
      result = cashierOrderService.getList(model);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(String order) {
    try {
      CashierOrder model = JSON.parseObject(order, CashierOrder.class);
      model.setOrderId("SY" + System.currentTimeMillis());
      boolean b = cashierOrderService.insert(model);
      if (b) {
        String id = DES3Util.encode(model.getOrderId());
        return new Result(ResultEnum.SUCCESS, id);
      } else {
        return new Result(ResultEnum.FAIL, b);
      }

    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierOrder model) {
    try {
      boolean b = cashierOrderService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(String order) {
    try {
      CashierOrder model = JSON.parseObject(order, CashierOrder.class);
      String id = model.getOrderId();
      model.setOrderId(DES3Util.decode(id));
      boolean b = cashierOrderService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  @ResponseBody
  public Result edit(CashierOrder model) {
    try {
      String id = DES3Util.decode(model.getOrderId());
      model.setOrderId(id);
      model.setStatus(CashierOrder.ORDER_IN);
      boolean b = cashierOrderService.updateByAmt(model);
      if(b){
        return new Result(ResultEnum.SUCCESS, DES3Util.encode(id));
      }else{
        return new Result(ResultEnum.ORDER_PAY_FAIL, b);
      }
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierOrder model) {
    try {
      String id = model.getOrderId();
      model.setOrderId(DES3Util.decode(id));
      CashierOrder b = cashierOrderService.get(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/query", method = RequestMethod.POST)
  @ResponseBody
  public Result query(CashierOrder model) {
    try {
      List<CashierOrder> list = cashierOrderService.query(model);
      return new Result(ResultEnum.SUCCESS, list);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/refund", method = RequestMethod.POST)
  @ResponseBody
  public Result refund(String order, String type) {
    try {
      CashierOrder model = JSON.parseObject(order, CashierOrder.class);
      int msg = cashierOrderService.check(model);
      switch (msg) {
        case 1:
        case 2:
          return new Result(ResultEnum.ORDER_ALL_FAIL, false);
        default:
          break;
      }
      boolean result = cashierOrderService.refund(model, type);
      return new Result(result ? ResultEnum.SUCCESS : ResultEnum.FAIL, result);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }


  @RequestMapping(value = "/pay", method = RequestMethod.POST)
  @ResponseBody
  public Result order(CashierOrder model) {
    try {
      String id = model.getOrderId();
      model.setOrderId(DES3Util.decode(id));
      CashierPayType ret = cashierOrderService.pay(model);
      return new Result(ResultEnum.SUCCESS, ret);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }
}
