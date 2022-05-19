package rml.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierConfigureMapper;
import rml.model.CashierConfigure;
import rml.service.ICashierConfService;

import java.util.Date;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service.impl
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月07日 21:34
 */
@Service
public class CashierConfServiceImpl implements ICashierConfService {

  @Autowired
  private CashierConfigureMapper cashierConfigureMapper;

  @Override
  public PageInfo<CashierConfigure> getList(CashierConfigure model) throws Exception {
    return null;
  }

  @Override
  public boolean insert(CashierConfigure model) {
    return false;
  }

  @Override
  public boolean delete(CashierConfigure model) {
    return false;
  }

  @Override
  public boolean update(CashierConfigure model) {
    model.setUpdatetime(new Date());
    return cashierConfigureMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierConfigure get(CashierConfigure model) {
    return cashierConfigureMapper.selectByPrimaryKey(model);
  }
}
