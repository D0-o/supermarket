package rml.dao;

import java.util.List;
import rml.model.CashierPayType;

public interface CashierPayTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CashierPayType record);

    CashierPayType selectByPrimaryKey(CashierPayType record);

    List<CashierPayType> selectAll(CashierPayType record);

    int updateByPrimaryKey(CashierPayType record);

  List<CashierPayType> selectPay(CashierPayType cpt);
}