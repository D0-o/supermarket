package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import rml.dao.CashierGoodsMapper;
import rml.dao.CashierInventoryGoodsMapper;
import rml.dao.CashierInventoryMapper;
import rml.model.CashierGoods;
import rml.model.CashierInventory;
import rml.model.CashierInventoryGoods;
import rml.model.CashierUser;
import rml.service.ICashierGoodsSumService;
import rml.service.ICashierInventoryService;
import rml.utils.UserUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service.impl
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月04日 1:39
 */

@Service
public class CashierInventoryServiceImpl implements ICashierInventoryService {

  @Autowired
  private CashierInventoryMapper cashierInventoryMapper;

  @Autowired
  private CashierInventoryGoodsMapper cashierInventoryGoodsMapper;

  @Autowired
  private ICashierGoodsSumService cashierGoodsSumService;

  @Override
  public PageInfo<CashierInventory> getList(CashierInventory model) throws Exception {
    CashierUser user = UserUtil.getLocalUser();
    if(user.getParentId().equals(user.getId()) ){
      model.setParentId(user.getParentId());
    }else{
      model.setUid(user.getId());
    }
    PageHelper.startPage(model.getPageNo(), model.getPageSize(), model.getOrderBy());
    List<CashierInventory> list = cashierInventoryMapper.selectAll(model);
    PageInfo<CashierInventory> result = new PageInfo<CashierInventory>(list);
    return result;
  }

  @Override
  public boolean insert(CashierInventory model) {
    return false;
  }

  @Override
  public boolean delete(CashierInventory model) {
    return false;
  }

  @Override
  public boolean update(CashierInventory model) {
    return false;
  }

  @Override
  public CashierInventory get(CashierInventory model) {
    return null;
  }

  @Override
  public List<CashierInventoryGoods> getGoods(CashierInventoryGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    if(user.getParentId().equals(user.getId()) ){
      model.setParentId(user.getParentId());
    }else{
      model.setUid(user.getId());
    }
    return cashierInventoryGoodsMapper.selectAll(model);
  }

  @Override
  @Transactional
  public List<CashierInventoryGoods> check(CashierInventoryGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    Date d = cashierInventoryGoodsMapper.selectDateMax(model);
    String str = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(str);
    model.setTime(sdf.format(new Date()));
    if(d == null){
      model.setSTime(sdf.format(new Date()));
    } else {
      model.setSTime(sdf.format(d));
    }
    cashierInventoryGoodsMapper.delInventoryDay(model);
    cashierInventoryGoodsMapper.insertInventoryDay(model);
    cashierInventoryGoodsMapper.updateInventoryDay(model);
    return cashierInventoryGoodsMapper.selectCheck(model);
  }

  @Override
  @Transactional
  public boolean insertList(List<CashierInventoryGoods> list) {
    try {
        CashierInventory model = new CashierInventory();
      Date d = new Date();
      model.setInventoryNo("PD" + System.currentTimeMillis());
      model.setInventoryTime(d);
      model.setStatus(1);
      model.setCreateTime(d);
      CashierUser user = UserUtil.getLocalUser();
      model.setParentId(user.getParentId());
      model.setUid(user.getId());
      model.setUname(user.getAccount());
      int rowGoods = cashierInventoryGoodsMapper.insertList(model);
      if(rowGoods > 0) {
        for (CashierInventoryGoods g : list) {
          g.setInventoryNo(model.getInventoryNo());
          cashierInventoryGoodsMapper.update(g);
        }
      }

      int row = cashierInventoryMapper.insert(model);
      cashierInventoryMapper.upInventory(model.getInventoryNo());
      if (row > 0) {
        cashierInventoryMapper.updateGoodsNum(model);
        cashierGoodsSumService.insetHistory();
        return row > 0;
      } else {
        throw new RuntimeException("盘点记录异常");
      }
    } catch (Exception e){
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return false;
    }
  }

  @Override
  public boolean getRepeat() {
    CashierInventory model = new CashierInventory();
    CashierUser user = UserUtil.getLocalUser();
    if(user.getParentId().equals(user.getId()) ){
      model.setParentId(user.getParentId());
    }else{
      model.setUid(user.getId());
    }
    model.setInventoryTime(new Date());
    return cashierInventoryMapper.selectByPrimaryKey(model) != null;
  }

  @Override
  public PageInfo<CashierInventoryGoods> checkList(CashierInventoryGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    String str = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(str);
    model.setTime(sdf.format(new Date()));
    PageHelper.startPage(model.getPageNo(), model.getPageSize());
    List<CashierInventoryGoods> list = cashierInventoryGoodsMapper.selectDay(model);
    PageInfo<CashierInventoryGoods> result = new PageInfo<CashierInventoryGoods>(list);
    return result;
  }

  @Override
  public CashierInventoryGoods sum(CashierInventoryGoods model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    String str = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(str);
    model.setTime(sdf.format(new Date()));
    return cashierInventoryGoodsMapper.selectSum(model);
  }
}
