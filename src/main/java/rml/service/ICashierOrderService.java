package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierOrder;
import rml.model.CashierPayType;
import rml.model.CashierReports;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月27日 22:13
 */
public interface ICashierOrderService extends IBaseService<CashierOrder> {
  List<CashierOrder> query(CashierOrder model) throws Exception;

  boolean refund(CashierOrder model, String type);

  int check(CashierOrder model);

  CashierPayType pay(CashierOrder model);

  PageInfo<CashierOrder> getReports(CashierReports model);

  boolean updateByAmt(CashierOrder model);
}
