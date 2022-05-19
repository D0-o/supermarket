package rml.dao;

import java.util.List;
import rml.model.CashierConfigure;

public interface CashierConfigureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierConfigure record);

    CashierConfigure selectByPrimaryKey(CashierConfigure record);

    List<CashierConfigure> selectAll();

    int updateByPrimaryKey(CashierConfigure record);
}