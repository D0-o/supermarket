package rml.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierLossGoodsMapper;
import rml.model.CashierGoods;
import rml.model.CashierLossGoods;
import rml.model.CashierUser;
import rml.service.ICashierGoodsService;
import rml.service.ICashierLossGoodsService;
import rml.utils.UserUtil;

import java.util.Date;
import java.util.List;

@Service
public class CashierLossGoodsServiceImpl implements ICashierLossGoodsService {

  @Autowired
  private CashierLossGoodsMapper cashierLossGoodsMapper;
  @Autowired
  private ICashierGoodsService cashierGoodsService;

  @Override
  public PageInfo<CashierLossGoods> getList(CashierLossGoods model) {
    return null;
  }

  @Override
  public boolean insert(CashierLossGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setCreateTime(new Date());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierLossGoodsMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierLossGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierLossGoodsMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierLossGoods get(CashierLossGoods model) {
    return cashierLossGoodsMapper.selectByPrimaryKey(model.getId());
  }

  @Override
  public boolean delete(CashierLossGoods model) {
    return cashierLossGoodsMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public List<CashierLossGoods> getListAll(CashierLossGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    List<CashierLossGoods> list = cashierLossGoodsMapper.selectAll(model);
    return list;
  }

  @Override
  public boolean insertList(List<CashierLossGoods> list) {
    CashierUser user = UserUtil.getLocalUser();
    Date d = new Date();
    for(CashierLossGoods cig : list){
      cig.setParentId(user.getParentId());
      cig.setUid(user.getId());
      cig.setUname(user.getAccount());
      cig.setUpdateTime(d);
      cig.setCreateTime(d);
      cashierLossGoodsMapper.insert(cig);
      CashierGoods cg = new CashierGoods(cig);
      cashierGoodsService.updateGoods(cg);
    }
    return true;
  }

}
