package rml.controller;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierGoods;
import rml.model.CashierGoodsTotal;
import rml.model.CashierOrderGoods;
import rml.service.ICashierGoodsService;
import rml.utils.ReadExcel;
import rml.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class CashierGoodsController {

  private static Logger logger = Logger.getLogger(CashierGoodsController.class);

  @Autowired
  private ICashierGoodsService cashierGoodsService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierGoods model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    if (model.getOrderBy() == null) {
      model.setOrderBy("updateTime desc");
    }
    if (model.getGoodsName() != null &&
        !"".equals(model.getGoodsName())
        && UserUtil.isInteger(model.getGoodsName())) {
      String code = model.getGoodsName();
      model.setGoodsCode(code);
      model.setGoodsName(null);
    } else if (model.getGoodsName().startsWith("ZDY")) {
      String code = model.getGoodsName();
      model.setGoodsCode(code);
      model.setGoodsName(null);
    }
    PageInfo<CashierGoods> result = cashierGoodsService.getList(model);
    return new Result(ResultEnum.SUCCESS, result);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierGoods model) {
    try {
      boolean b = cashierGoodsService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierGoods model) {
    try {
      boolean b = cashierGoodsService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierGoods model) {
    try {
      boolean b = cashierGoodsService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/get", method = RequestMethod.POST)
  @ResponseBody
  public Result get(CashierGoods model) {
    try {
      CashierGoods b = cashierGoodsService.get(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/total", method = RequestMethod.POST)
  @ResponseBody
  public Result total(CashierGoods model) {
    try {
      if (model.getGoodsName() != null &&
          !"".equals(model.getGoodsName())
          && UserUtil.isInteger(model.getGoodsName())) {
        String code = model.getGoodsName();
        model.setGoodsCode(code);
        model.setGoodsName(null);
      } else if (model.getGoodsName().startsWith("ZDY")) {
        String code = model.getGoodsName();
        model.setGoodsCode(code);
        model.setGoodsName(null);
      }
      CashierGoodsTotal b = cashierGoodsService.total(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/all", method = RequestMethod.POST)
  @ResponseBody
  public Result all(CashierGoods model) {
    try {
      if (model.getGoodsName() != null &&
          !"".equals(model.getGoodsName())
          && UserUtil.isInteger(model.getGoodsName())) {
        String code = model.getGoodsName();
        model.setGoodsCode(code);
        model.setGoodsName(null);
      } else if (model.getGoodsName().startsWith("ZDY")) {
        String code = model.getGoodsName();
        model.setGoodsCode(code);
        model.setGoodsName(null);
      }
      List<CashierGoods> list = cashierGoodsService.all(model);
      if (list == null || list.isEmpty()) {
        if (model.getGoodsCode() != null && !"".equals(model.getGoodsCode())
            && model.getObtain() == null) {
          list = new ArrayList<CashierGoods>();
          list.add(model);
        }
      } else {
        if(model.getObtain() == null){
          for(CashierGoods cg : list){
            cg.setId(null);
            cg.setInventory(1);
          }
        }
      }
      return new Result(ResultEnum.SUCCESS, list);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/order", method = RequestMethod.POST)
  @ResponseBody
  public Result order(CashierGoods model) {
    try {
      if (model.getGoodsName() != null &&
          !"".equals(model.getGoodsName())
          && UserUtil.isInteger(model.getGoodsName())) {
        String code = model.getGoodsName();
        model.setGoodsCode(code);
        model.setGoodsName(null);
      } else if (model.getGoodsName().startsWith("ZDY")) {
        String code = model.getGoodsName();
        model.setGoodsCode(code);
        model.setGoodsName(null);
      }
      List<CashierGoods> list = cashierGoodsService.all(model);
      List<CashierOrderGoods> ret = new LinkedList<CashierOrderGoods>();
      if (list != null && !list.isEmpty()) {
        for (CashierGoods g : list) {
          CashierOrderGoods gog = new CashierOrderGoods(g);
          ret.add(gog);
        }
      }
      return new Result(ResultEnum.SUCCESS, ret);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/orders", method = RequestMethod.POST)
  @ResponseBody
  public Result orders(CashierGoods model) {
    try {
      List<String> codes = Arrays.asList(model.getGoodsCode().split(","));
      model.setGoodsCode(null);
      model.setCodes(codes);
      List<CashierGoods> list = cashierGoodsService.orders(model);
      List<CashierOrderGoods> ret = new LinkedList<CashierOrderGoods>();
      if (list != null && !list.isEmpty()) {
        for (CashierGoods g : list) {
          CashierOrderGoods gog = new CashierOrderGoods(g);
          ret.add(gog);
        }
      }
      return new Result(ResultEnum.SUCCESS, ret);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping("/upload")
  @ResponseBody
  public Result upload(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file)
      throws Exception {

    String fileName = file.getOriginalFilename();
    // 判断文件是.xlsx或者xls结尾。如果不是则抛出提示信息
    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
    if (!"xlsx".equals(suffix) && !"xls".equals(suffix)) {
      System.out.println("请传入excel文件");
    }
    ReadExcel r = new ReadExcel();
    List<CashierGoods> l = r.getExcelInfo(fileName, file.getInputStream());
    if(l == null || l.isEmpty()) {
      return new Result(ResultEnum.SUCCESS, false);
    }
    cashierGoodsService.warehousing(l);
    return new Result(ResultEnum.SUCCESS, true);
  }

}
