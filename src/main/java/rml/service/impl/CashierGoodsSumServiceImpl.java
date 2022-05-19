package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierGoodsSumMapper;
import rml.model.CashierChart;
import rml.model.CashierGoodsSum;
import rml.model.CashierReports;
import rml.model.CashierUser;
import rml.service.ICashierGoodsSumService;
import rml.utils.UserUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service.impl
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月01日 0:58
 */
@Service
public class CashierGoodsSumServiceImpl implements ICashierGoodsSumService {

  @Autowired
  private CashierGoodsSumMapper cashierGoodsSumMapper;

  @Override
  public PageInfo<CashierGoodsSum> getReports(CashierReports model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    if (model.getTime() != null && !"".equals(model.getTime())) {
      model.setSTime(model.getTime() + "T00:00:00");
      model.setETime(model.getTime() + "T23:59:59");
    }
    PageHelper.startPage(model.getPageNo(), model.getPageSize());
    List<CashierGoodsSum> list = cashierGoodsSumMapper.selectReports(model);
    PageInfo<CashierGoodsSum> result = new PageInfo<CashierGoodsSum>(list);
    return result;
  }

  @Override
  public PageInfo<CashierGoodsSum> getReportsHistory(CashierReports model) {
    CashierUser user = UserUtil.getLocalUser();
    if (model.getTime() != null && !"".equals(model.getTime())) {
      model.setSTime(model.getTime() + "T00:00:00");
      model.setETime(model.getTime() + "T23:59:59");
    }
    List<CashierGoodsSum> list = null;
    PageHelper.startPage(model.getPageNo(), model.getPageSize());
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
      list = cashierGoodsSumMapper.selectReportsHistoryByPid(model);
    } else {
      model.setUid(user.getId());
      list = cashierGoodsSumMapper.selectReportsHistory(model);
    }
    PageInfo<CashierGoodsSum> result = new PageInfo<CashierGoodsSum>(list);
    return result;
  }

  @Override
  public CashierGoodsSum getReportsTotal(CashierReports model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    if (model.getTime() != null && !"".equals(model.getTime())) {
      model.setSTime(model.getTime() + "T00:00:00");
      model.setETime(model.getTime() + "T23:59:59");
    }
    CashierGoodsSum result = cashierGoodsSumMapper.selectReportsTotal(model);
    settingRate(result);
    return result;
  }

  @Override
  public CashierGoodsSum getReportsHistoryTotal(CashierReports model) {
    CashierUser user = UserUtil.getLocalUser();
    if (model.getTime() != null && !"".equals(model.getTime())) {
      model.setSTime(model.getTime() + "T00:00:00");
      model.setETime(model.getTime() + "T23:59:59");
    }
    CashierGoodsSum result = null;
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
      result = cashierGoodsSumMapper.selectReportsHistoryTotalByPid(model);
    } else {
      model.setUid(user.getId());
      result = cashierGoodsSumMapper.selectReportsHistoryTotal(model);
    }
    settingRate(result);
    return result;
  }

  @Override
  public List<CashierChart> getChart(Map<String, Object> result) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      result.put("pid", user.getParentId());
    } else {
      result.put("uid", user.getId());
    }
    List<Map<String, Object>> d = cashierGoodsSumMapper.selectChart(result);
    List<CashierChart> l = new ArrayList<CashierChart>();
    List<String> key = (List<String>) result.get("m");
    if (!d.isEmpty()) {
      for (Map<String, Object> m : d) {
        CashierChart c = new CashierChart();
        BigDecimal[] data = new BigDecimal[key.size()];
        c.setName((String) m.get("name"));
        for (int i = 0, s = key.size(); i < s; i++) {
          String k = key.get(i);
          data[i] = (BigDecimal) m.get(k);
        }
        c.setData(data);
        l.add(c);
      }
    }
    return l;
  }

  @Override
  public void insetHistory() {
    CashierReports model = new CashierReports();
    Date date = new Date();//获取当前时间
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, -1);
    String str = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(str);
    String time = sdf.format(calendar.getTime());
    model.setSTime(time + "T00:00:00");
    model.setETime(time + "T23:59:59");
    cashierGoodsSumMapper.update(model);
  }

  public void settingRate(CashierGoodsSum result) {
    if (result != null) {
      BigDecimal salesPrice = new BigDecimal(result.getSalesPrice());
      BigDecimal purchasePrice = new BigDecimal(result.getPurchasePrice());
      BigDecimal rate = salesPrice.subtract(purchasePrice).divide(salesPrice, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
      result.setRate(rate.toString());
    }
  }
}
