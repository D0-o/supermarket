package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierInventory;
import rml.model.CashierInventoryGoods;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月04日 1:38
 */
public interface ICashierInventoryService extends IBaseService<CashierInventory> {
  List<CashierInventoryGoods> getGoods(CashierInventoryGoods model);

  List<CashierInventoryGoods> check(CashierInventoryGoods model);

  boolean insertList(List<CashierInventoryGoods> model);

  boolean getRepeat();

  PageInfo<CashierInventoryGoods> checkList(CashierInventoryGoods model);

  CashierInventoryGoods sum(CashierInventoryGoods model);
}
