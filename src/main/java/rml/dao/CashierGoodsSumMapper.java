package rml.dao;

import rml.model.CashierGoodsSum;
import rml.model.CashierReports;

import java.util.List;
import java.util.Map;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.dao
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月01日 1:02
 */
public interface CashierGoodsSumMapper {
  List<CashierGoodsSum> selectReports(CashierReports model);

  List<CashierGoodsSum> selectReportsHistory(CashierReports model);

  void update(CashierReports model);

  List<CashierGoodsSum> selectReportsHistoryByPid(CashierReports model);

  CashierGoodsSum selectReportsTotal(CashierReports model);

  CashierGoodsSum selectReportsHistoryTotalByPid(CashierReports model);

  CashierGoodsSum selectReportsHistoryTotal(CashierReports model);

  List<Map<String,Object>> selectChart(Map<String, Object> result);
}
