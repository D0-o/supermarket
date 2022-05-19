package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierPayTypeMapper;
import rml.model.CashierPayType;
import rml.model.CashierUser;
import rml.service.ICashierPayTypeService;
import rml.utils.UserUtil;

import java.util.Date;
import java.util.List;

@Service
public class CashierPayTypeServiceImpl implements ICashierPayTypeService {

  @Autowired
  private CashierPayTypeMapper cashierPayTypeMapper;

  @Override
  public boolean insert(CashierPayType model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());

    return cashierPayTypeMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierPayType model) {
    return cashierPayTypeMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierPayType get(CashierPayType model) {
    return cashierPayTypeMapper.selectByPrimaryKey(model);
  }

  @Override
  public boolean delete(CashierPayType model) {
    return cashierPayTypeMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public PageInfo<CashierPayType> getList(CashierPayType model) {
    PageHelper.startPage(model.getPageNo(), model.getPageSize(), model.getOrderBy());
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    List<CashierPayType> list = cashierPayTypeMapper.selectAll(model);
    PageInfo<CashierPayType> result = new PageInfo<CashierPayType>(list);
    return result;
  }

  @Override
  public List<CashierPayType> getAllList(CashierPayType model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    return cashierPayTypeMapper.selectAll(model);
  }

  @Override
  public List<CashierPayType> getPay(CashierPayType cpt) {
    return cashierPayTypeMapper.selectPay(cpt);
  }
}
