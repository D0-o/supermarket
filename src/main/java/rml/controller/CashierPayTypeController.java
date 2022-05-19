package rml.controller;

import com.github.pagehelper.PageInfo;
import lombok.val;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierPayType;
import rml.service.ICashierPayTypeService;
import rml.utils.GeneratID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/paytype")
public class CashierPayTypeController {

  private static Logger logger = Logger.getLogger(CashierPayTypeController.class);

  @Autowired
  private ICashierPayTypeService cashierPayTypeService;

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @ResponseBody
  public Result list(CashierPayType model) {
    if (model.getPageNo() == null) {
      model.setPageNo(1);
    }
    if (model.getPageSize() == null) {
      model.setPageSize(10);
    }
    if (model.getOrderBy() == null) {
      model.setOrderBy("updateTime desc");
    }
    try {
      PageInfo<CashierPayType> result = cashierPayTypeService.getList(model);
      return new Result(ResultEnum.SUCCESS, result);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Result add(CashierPayType model) {
    try {
      boolean b = cashierPayTypeService.insert(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public Result delete(CashierPayType model) {
    try {
      boolean b = cashierPayTypeService.delete(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Result update(CashierPayType model) {
    try {
      boolean b = cashierPayTypeService.update(model);
      return new Result(ResultEnum.SUCCESS, b);
    } catch (Exception e) {
      return new Result(ResultEnum.FAIL, false);
    }
  }


  @RequestMapping("/upfile")
  @ResponseBody
  public Result upfile(HttpServletRequest request, @RequestParam MultipartFile file)
      throws Exception {
    WebApplicationContext webApplicationContext = ContextLoader
        .getCurrentWebApplicationContext();
    ServletContext servletContext = webApplicationContext.getServletContext();
    // 得到文件绝对路径
    String realPath = servletContext.getRealPath("/payfile");
    System.out.println("realPath :" + realPath);
    File file1 = new File(realPath);
    if (!file1.exists()) {
      file1.mkdir();   //如果该目录不存在，就创建此抽象路径名指定的目录。
    }
    String oriName = file.getOriginalFilename();
    String extName = oriName.substring(0, oriName.lastIndexOf("."));
    String type = request.getParameter("payType");
    String acct = request.getParameter("acct");
    file.transferTo(new File(realPath + "/" + oriName));
    CashierPayType model = new CashierPayType();
    double d = Double.parseDouble(extName);
    String result = String .format("%.2f",d);
    model.setAmount(result);
    model.setType(Integer.parseInt(type));
    List<CashierPayType> ret = cashierPayTypeService.getAllList(model);
    model.setImage(oriName);
    model.setAcct(acct == null ? "" : acct);
    Long Id=GeneratID.getGeneratID();
    model.setId(Id);
    if (ret != null && !ret.isEmpty()) {
      for (CashierPayType cpt : ret) {
        if (acct.equals(cpt.getAcct())) {
          boolean b = cashierPayTypeService.delete(cpt);
          if (b) {
            cashierPayTypeService.insert(model);
          }
        } else {
          cashierPayTypeService.insert(model);
        }
      }
    } else {
      cashierPayTypeService.insert(model);
    }
    return new Result(ResultEnum.SUCCESS, Id);
  }

  @RequestMapping("/gxcashierPayType")
  @ResponseBody
  public void upfile(HttpServletRequest request) throws Exception {
    String type = request.getParameter("payType");
    String acct = request.getParameter("acct");
    String id = request.getParameter("id");
    CashierPayType model = new CashierPayType();
    model.setId(Long.valueOf(id));
    CashierPayType cashierPayType = cashierPayTypeService.get(model);
    cashierPayType.setType(Integer.parseInt(type));
    cashierPayType.setAcct(acct == null ? "" : acct);
    cashierPayTypeService.update(cashierPayType);
  }
}
