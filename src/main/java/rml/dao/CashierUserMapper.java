package rml.dao;

import java.util.List;
import rml.model.CashierUser;

public interface CashierUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierUser record);

    CashierUser selectByPrimaryKey(Long id);

    List<CashierUser> selectAll(CashierUser model);

    int updateByPrimaryKey(CashierUser record);

    CashierUser select(CashierUser model);
}