package rml.service;

import rml.model.CashierLossGoods;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月25日 22:59
 */
public interface ICashierLossGoodsService extends IBaseService<CashierLossGoods> {
  List<CashierLossGoods> getListAll(CashierLossGoods model);

  boolean insertList(List<CashierLossGoods> list);
}
