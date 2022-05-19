package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierOrderGoodsMapper;
import rml.model.CashierOrderGoods;
import rml.model.CashierReports;
import rml.model.CashierUser;
import rml.service.ICashierOrderGoodsService;
import rml.utils.UserUtil;

import java.util.Date;
import java.util.List;

@Service
public class CashierOrderGoodsServiceImpl implements ICashierOrderGoodsService {

  @Autowired
  private CashierOrderGoodsMapper cashierOrderGoodsMapper;

  @Override
  public boolean insert(CashierOrderGoods model) {
    model.setCreateTime(new Date());
    return cashierOrderGoodsMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierOrderGoods model) {
    return cashierOrderGoodsMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierOrderGoods get(CashierOrderGoods model) {
    return cashierOrderGoodsMapper.selectByPrimaryKey(model.getId());
  }

  @Override
  public boolean delete(CashierOrderGoods model) {
    return cashierOrderGoodsMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public PageInfo<CashierOrderGoods> getList(CashierOrderGoods model) {
    PageHelper.startPage(model.getPageNo(), model.getPageSize(), model.getOrderBy());
    List<CashierOrderGoods> list = cashierOrderGoodsMapper.selectAll(model);
    PageInfo<CashierOrderGoods> result = new PageInfo<CashierOrderGoods>(list);
    return result;
  }

  @Override
  public List<CashierOrderGoods> getAllList(CashierOrderGoods model) {
    List<CashierOrderGoods> list = cashierOrderGoodsMapper.selectAll(model);
    return list;
  }

  @Override
  public PageInfo<CashierOrderGoods> getReports(CashierReports model) {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    if(model.getTime() != null && !"".equals(model.getTime())){
      model.setSTime(model.getTime() + "T00:00:00");
      model.setETime(model.getTime() + "T23:59:59");
    }
    PageHelper.startPage(model.getPageNo(), model.getPageSize());
    List<CashierOrderGoods> list = cashierOrderGoodsMapper.selectReports(model);
    PageInfo<CashierOrderGoods> result = new PageInfo<CashierOrderGoods>(list);
    return result;
  }
}
