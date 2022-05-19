package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierMenu;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月22日 12:39
 */
public interface ICashierMenuService {
  PageInfo<CashierMenu> getList(CashierMenu model);

  boolean insert(CashierMenu model);

  boolean delete(CashierMenu model);

  boolean update(CashierMenu model);

  List<CashierMenu> getAll(CashierMenu model);
}
