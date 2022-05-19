package rml.dao;

import rml.model.CashierMenu;

import java.util.List;

public interface CashierMenuMapper {
  int deleteByPrimaryKey(Long id);

  int insert(CashierMenu record);

  CashierMenu selectByPrimaryKey(Long id);

  List<CashierMenu> selectAll(CashierMenu record);

  int updateByPrimaryKey(CashierMenu record);
}