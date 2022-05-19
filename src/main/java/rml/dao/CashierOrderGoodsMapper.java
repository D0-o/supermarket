package rml.dao;

import java.util.List;
import rml.model.CashierOrderGoods;
import rml.model.CashierReports;

public interface CashierOrderGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierOrderGoods record);

    CashierOrderGoods selectByPrimaryKey(Long id);

    List<CashierOrderGoods> selectAll(CashierOrderGoods record);

    int updateByPrimaryKey(CashierOrderGoods record);

  List<CashierOrderGoods> selectReports(CashierReports model);

    CashierOrderGoods selectByOrderId(String orderId);
}
