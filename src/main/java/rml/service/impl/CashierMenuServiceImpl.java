package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierMenuMapper;
import rml.dao.CashierUserMapper;
import rml.model.CashierMenu;
import rml.model.CashierUser;
import rml.service.ICashierMenuService;

import java.util.List;

@Service
public class CashierMenuServiceImpl implements ICashierMenuService {

  @Autowired
  private CashierMenuMapper cashierMenuMapper;

  @Override
  public List<CashierMenu> getAll(CashierMenu model) {
    List<CashierMenu> list = cashierMenuMapper.selectAll(model);
    return list;
  }

  @Override
  public boolean insert(CashierMenu model) {
    return cashierMenuMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierMenu model) {
    return cashierMenuMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public boolean delete(CashierMenu model) {
    return cashierMenuMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public PageInfo<CashierMenu> getList(CashierMenu model) {
    PageHelper.startPage(model.getPageNo(), model.getPageSize(), model.getOrderBy());
    List<CashierMenu> list = cashierMenuMapper.selectAll(model);
    PageInfo<CashierMenu> result = new PageInfo<CashierMenu>(list);
    return result;
  }
}
