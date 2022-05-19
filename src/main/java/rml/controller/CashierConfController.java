package rml.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierConfigure;
import rml.service.ICashierConfService;

@Controller
@RequestMapping("/conf")
public class CashierConfController {

  private static Logger logger = Logger.getLogger(CashierConfController.class);

  @Autowired
  private ICashierConfService cashierConfService;

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierConfigure model) {
    try {
      boolean b = cashierConfService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierConfigure model) {
    try {
      CashierConfigure b = cashierConfService.get(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

}
