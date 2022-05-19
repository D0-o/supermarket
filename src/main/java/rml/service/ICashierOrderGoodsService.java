package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierOrderGoods;
import rml.model.CashierReports;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月27日 22:25
 */
public interface ICashierOrderGoodsService extends IBaseService<CashierOrderGoods> {
  List<CashierOrderGoods> getAllList(CashierOrderGoods model);

  PageInfo<CashierOrderGoods> getReports(CashierReports model);
}
