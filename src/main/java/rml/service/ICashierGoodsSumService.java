package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.*;

import java.util.List;
import java.util.Map; /**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月01日 0:57
 */
public interface ICashierGoodsSumService {
  PageInfo<CashierGoodsSum> getReports(CashierReports model);

  PageInfo<CashierGoodsSum> getReportsHistory(CashierReports model);

  CashierGoodsSum getReportsTotal(CashierReports model);

  CashierGoodsSum getReportsHistoryTotal(CashierReports model);

  List<CashierChart> getChart(Map<String, Object> result);

  void insetHistory();
}
