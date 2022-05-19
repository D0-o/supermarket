package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierGoodsMapper;
import rml.model.CashierGoods;
import rml.model.CashierGoodsTotal;
import rml.model.CashierUser;
import rml.service.ICashierGoodsService;
import rml.utils.UserUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CashierGoodsServiceImpl implements ICashierGoodsService {

  @Autowired
  private CashierGoodsMapper cashierGoodsMapper;

  @Override
  public boolean insert(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setCreateTime(new Date());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierGoodsMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierGoodsMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierGoods get(CashierGoods model) {
    return cashierGoodsMapper.selectByPrimaryKey(model);
  }

  @Override
  public CashierGoodsTotal total(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    switch ((int)user.getType()){
      case 1:model.setParentId(null);break;
      case 2:model.setParentId(null);break;
      case 3:model.setParentId(null);break;
    }
    return cashierGoodsMapper.selectTotal(model);
  }

  @Override
  public List<CashierGoods> all(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    switch ((int)user.getType()){
      case 1:model.setParentId(null);break;
      case 2:model.setParentId(null);break;
      case 3:model.setParentId(null);break;
    }

    List<CashierGoods> list = cashierGoodsMapper.selectAll(model);
    return list;
  }

  @Override
  public void updateGoods(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    cashierGoodsMapper.updateByCode(model);
  }

  @Override
  public List<CashierGoods> orders(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    switch ((int)user.getType()){
      case 1:model.setParentId(null);break;
      case 2:model.setParentId(null);break;
      case 3:model.setUid(user.getId());break;
    }
    List<CashierGoods> list = cashierGoodsMapper.selectAll(model);
    return list;
  }

  @Override
  public boolean delete(CashierGoods model) {
    return cashierGoodsMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public PageInfo<CashierGoods> getList(CashierGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    switch ((int)user.getType()){
      case 1:model.setParentId(null);break;
      case 2:model.setParentId(null);break;
      case 3:model.setParentId(null);break;
    }
    PageHelper.startPage(model.getPageNo(), model.getPageSize(), model.getOrderBy());
    List<CashierGoods> list = cashierGoodsMapper.selectAll(model);
    PageInfo<CashierGoods> result = new PageInfo<CashierGoods>(list);
    return result;
  }

  @Override
  public void warehousing(List<CashierGoods> l) {
    for(CashierGoods g : l){
      CashierGoods cg = get(g);
      if (cg == null) {
        if(g.getType() == null){
          g.setType(1);
        }
        if(g.getDiscount() == null){
          g.setDiscount(new BigDecimal(0));
        }
        insert(g);
      } else {
        updateGoods(g);
      }
    }
  }
}
