package rml.controller;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.*;
import rml.service.ICashierGoodsSumService;
import rml.service.ICashierOrderGoodsService;
import rml.service.ICashierOrderService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/reports")
public class CashierReportsController {

  private static Logger logger = Logger.getLogger(CashierReportsController.class);

  @Autowired
  private ICashierOrderGoodsService cashierOrderGoodsService;

  @Autowired
  private ICashierOrderService cashierOrderService;

  @Autowired
  private ICashierGoodsSumService cashierGoodsSumService;

  @RequestMapping(value = "/order", method = RequestMethod.POST)
  @ResponseBody
  public Result order(CashierReports model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    try {
      if (model.getOrderId() != null && !"".equals(model.getOrderId())) {
        PageInfo<CashierOrderGoods> result = cashierOrderGoodsService.getReports(model);
        return new Result(ResultEnum.SUCCESS, result);
      } else {
        PageInfo<CashierOrder> result = cashierOrderService.getReports(model);
        return new Result(ResultEnum.SUCCESS, result);
      }
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/goods", method = RequestMethod.POST)
  @ResponseBody
  public Result goods(CashierReports model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    try {
      Date date = new Date();
      String str = "yyyy-MM-dd";
      SimpleDateFormat sdf = new SimpleDateFormat(str);
      if (model.getTime().equals(sdf.format(date))) {
        PageInfo<CashierGoodsSum> result = cashierGoodsSumService.getReports(model);
        return new Result(ResultEnum.SUCCESS, result);
      } else {
        PageInfo<CashierGoodsSum> result = cashierGoodsSumService.getReportsHistory(model);
        return new Result(ResultEnum.SUCCESS, result);
      }
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/total", method = RequestMethod.POST)
  @ResponseBody
  public Result total(CashierReports model) {
    try {
      Date date = new Date();
      String str = "yyyy-MM-dd";
      SimpleDateFormat sdf = new SimpleDateFormat(str);
      if (model.getTime().equals(sdf.format(date))) {
        CashierGoodsSum result = cashierGoodsSumService.getReportsTotal(model);
        return new Result(ResultEnum.SUCCESS, result);
      } else {
        CashierGoodsSum result = cashierGoodsSumService.getReportsHistoryTotal(model);
        return new Result(ResultEnum.SUCCESS, result);
      }
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/graphical", method = RequestMethod.POST)
  @ResponseBody
  public Result graphical(CashierReports model) {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      Calendar calendar = Calendar.getInstance();
      String str = "yyyy-MM-dd";
      SimpleDateFormat sdf = new SimpleDateFormat(str);
      Date date = sdf.parse(model.getSTime());
      calendar.setTime(date);
      List<String> l = new ArrayList<>(31);
      while (true) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String time = sdf.format(calendar.getTime());
        l.add(time);
        if (time.equals(model.getETime())) {
          break;
        }
      }
      result.put("m", l);
      result.put("top", model.getTop());
      result.put("sTime", model.getSTime() + "T00:00:00");
      result.put("eTime", model.getETime() + "T23:59:59");
      result.put("goodsCode", model.getGoodsCode());
      List<CashierChart> d = cashierGoodsSumService.getChart(result);
      result.put("d", d);
      return new Result(ResultEnum.SUCCESS, result);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }


}
