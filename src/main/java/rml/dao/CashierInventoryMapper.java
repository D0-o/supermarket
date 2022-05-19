package rml.dao;

import java.util.List;
import rml.model.CashierInventory;

public interface CashierInventoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierInventory record);

    CashierInventory selectByPrimaryKey(CashierInventory record);

    List<CashierInventory> selectAll(CashierInventory record);

    int updateByPrimaryKey(CashierInventory record);

    void updateGoodsNum(CashierInventory model);

    void upInventory(String inventoryNo);
}
