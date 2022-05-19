package rml.dao;

import java.util.List;
import rml.model.CashierLossGoods;

public interface CashierLossGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierLossGoods record);

    CashierLossGoods selectByPrimaryKey(Long id);

    List<CashierLossGoods> selectAll(CashierLossGoods record);

    int updateByPrimaryKey(CashierLossGoods record);
}