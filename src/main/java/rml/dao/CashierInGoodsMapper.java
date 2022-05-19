package rml.dao;

import java.util.List;
import rml.model.CashierInGoods;

public interface CashierInGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierInGoods record);

    CashierInGoods selectByPrimaryKey(Long id);

    List<CashierInGoods> selectAll(CashierInGoods record);

    int updateByPrimaryKey(CashierInGoods record);

  List<String> select(CashierInGoods model);
}