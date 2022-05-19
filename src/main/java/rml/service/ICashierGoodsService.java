package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierGoods;
import rml.model.CashierGoodsTotal;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月22日 17:47
 */
public interface ICashierGoodsService {
  PageInfo<CashierGoods> getList(CashierGoods model);

  boolean insert(CashierGoods model);

  boolean delete(CashierGoods model);

  boolean update(CashierGoods model);

  CashierGoods get(CashierGoods model);

  CashierGoodsTotal total(CashierGoods model);

  List<CashierGoods> all(CashierGoods model);

  void updateGoods(CashierGoods cg);

  List<CashierGoods> orders(CashierGoods model);

  void warehousing(List<CashierGoods> l);
}
