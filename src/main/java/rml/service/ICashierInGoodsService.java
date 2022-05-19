package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierInGoods;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月23日 22:35
 */
public interface ICashierInGoodsService extends IBaseService<CashierInGoods> {
  List<CashierInGoods> getListAll(CashierInGoods model);

  boolean insertList(List<CashierInGoods> list);

  List<String> select(CashierInGoods model);
}
