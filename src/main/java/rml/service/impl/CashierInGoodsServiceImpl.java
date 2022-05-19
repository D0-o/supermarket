package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierInGoodsMapper;
import rml.model.CashierGoods;
import rml.model.CashierInGoods;
import rml.model.CashierUser;
import rml.service.ICashierGoodsService;
import rml.service.ICashierInGoodsService;
import rml.utils.UserUtil;

import java.util.Date;
import java.util.List;

@Service
public class CashierInGoodsServiceImpl implements ICashierInGoodsService {

  @Autowired
  private CashierInGoodsMapper cashierInGoodsMapper;
  @Autowired
  private ICashierGoodsService cashierGoodsService;

  @Override
  public PageInfo<CashierInGoods> getList(CashierInGoods model) {
    return null;
  }

  @Override
  public boolean insert(CashierInGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setCreateTime(new Date());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierInGoodsMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierInGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierInGoodsMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierInGoods get(CashierInGoods model) {
    return cashierInGoodsMapper.selectByPrimaryKey(model.getId());
  }

  @Override
  public boolean delete(CashierInGoods model) {
    return cashierInGoodsMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public List<CashierInGoods> getListAll(CashierInGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    List<CashierInGoods> list = cashierInGoodsMapper.selectAll(model);
    return list;
  }

  @Override
  public boolean insertList(List<CashierInGoods> list) {
    CashierUser user = UserUtil.getLocalUser();
    Date d = new Date();
    for(CashierInGoods cig : list){
      cig.setParentId(user.getParentId());
      cig.setUid(user.getId());
      cig.setUname(user.getAccount());
      cig.setUpdateTime(d);
      if(cig.getId() == null){
        cig.setCreateTime(d);
        cashierInGoodsMapper.insert(cig);
      }else {
        cashierInGoodsMapper.updateByPrimaryKey(cig);
      }
      if(cig.getStatus() != null && cig.getStatus().equals(CashierInGoods.IN_GOODS)){
        CashierGoods cg = new CashierGoods(cig);
        cashierGoodsService.updateGoods(cg);
      }
    }
    return true;
  }

  @Override
  public List<String> select(CashierInGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    return cashierInGoodsMapper.select(model);
  }
}
