package rml.dao;

import java.util.List;
import rml.model.CashierOrder;
import rml.model.CashierReports;

public interface CashierOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierOrder record);

    CashierOrder selectByPrimaryKey(CashierOrder record);

    List<CashierOrder> selectAll(CashierOrder record);

    int updateByPrimaryKey(CashierOrder record);

  List<CashierOrder> selectByTime(CashierOrder model);

  List<CashierOrder> selectReports(CashierReports model);
}