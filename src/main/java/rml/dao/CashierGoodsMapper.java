package rml.dao;

import rml.model.CashierGoods;
import rml.model.CashierGoodsTotal;

import java.util.List;

public interface CashierGoodsMapper {
  int deleteByPrimaryKey(Long id);

  int insert(CashierGoods record);

  CashierGoods selectByPrimaryKey(CashierGoods model);

  List<CashierGoods> selectAll(CashierGoods model);

  int updateByPrimaryKey(CashierGoods record);

  CashierGoodsTotal selectTotal(CashierGoods model);

  void updateByCode(CashierGoods model);
}