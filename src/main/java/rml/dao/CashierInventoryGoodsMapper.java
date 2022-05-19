package rml.dao;

import java.util.Date;
import java.util.List;
import rml.model.CashierInventory;
import rml.model.CashierInventoryGoods;

public interface CashierInventoryGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierInventoryGoods record);

    CashierInventoryGoods selectByPrimaryKey(CashierInventoryGoods record);

    List<CashierInventoryGoods> selectAll(CashierInventoryGoods record);

    int updateByPrimaryKey(CashierInventoryGoods record);

  List<CashierInventoryGoods> selectCheck(CashierInventoryGoods model);

  void delInventoryDay(CashierInventoryGoods model);

  void insertInventoryDay(CashierInventoryGoods model);

  void updateInventoryDay(CashierInventoryGoods model);

  Date selectDateMax(CashierInventoryGoods model);

  List<CashierInventoryGoods> selectDay(CashierInventoryGoods model);

  CashierInventoryGoods selectSum(CashierInventoryGoods model);

  int insertList(CashierInventory model);

  void update(CashierInventoryGoods g);
}