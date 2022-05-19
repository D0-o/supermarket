package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rml.dao.CashierGoodsMapper;
import rml.dao.CashierOrderGoodsMapper;
import rml.dao.CashierOrderMapper;
import rml.dto.PayTypeEnum;
import rml.model.*;
import rml.service.ICashierGoodsService;
import rml.service.ICashierOrderGoodsService;
import rml.service.ICashierOrderService;
import rml.service.ICashierPayTypeService;
import rml.utils.DES3Util;
import rml.utils.UserUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class CashierOrderServiceImpl implements ICashierOrderService {

  @Autowired
  private CashierOrderMapper cashierOrderMapper;

  @Autowired
  private ICashierOrderGoodsService cashierOrderGoodsService;


  @Autowired
  private CashierOrderGoodsMapper cashierOrderGoodsMaper;


  @Autowired
  private ICashierGoodsService cashierGoodsService;

  @Autowired
  private ICashierPayTypeService cashierPayTypeService;

  @Override
  @Transactional
  public boolean insert(CashierOrder model) {
    Date d = new Date();
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setOrderTime(d);
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(d);
    model.setSingleNum(model.getList().size());
    Integer num = 0;
    BigDecimal sum = new BigDecimal(0);
    StringBuilder unfinished = new StringBuilder(model.getList().size() * 15);
    for (int i = 0,s =  model.getList().size(); i < s; i++) {
      CashierOrderGoods order = model.getList().get(i);
      BigDecimal number;
      if (CashierOrderGoods.ORIGINAL_TYPE.equals(order.getType())) {
        number = order.getCostPrice().subtract(order.getDiscount()).multiply(BigDecimal.valueOf(order.getNum().intValue()));
        order.setCurrentPrice(order.getCostPrice());
      } else {
        number = order.getCostPrice().subtract(order.getDiscount()).multiply(BigDecimal.valueOf(order.getNum().intValue()));
        order.setCurrentPrice(order.getDiscount());
      }
      order.setSubtotal(number);
      order.setOrderId(model.getOrderId());
      boolean b = cashierOrderGoodsService.insert(order);
      if (b) {
        if(i < 6){
          unfinished.append(",").append(order.getGoodsName()).append("*").append(order.getNum());
        }
        num += order.getNum();
        sum = sum.add(number);
      }
    }
    model.setNum(num);
    model.setAmount(sum.subtract(model.getFictitious() != null ? model.getFictitious() : new BigDecimal(0)));
    model.setActualPay(model.getAmount());
    if(unfinished.length() > 0){
      model.setUnfinished(unfinished.substring(1));
    }
    return cashierOrderMapper.insert(model) > 0;
  }

  @Override
  public boolean update(CashierOrder model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    model.setSingleNum(model.getList().size());
    Integer num = 0;
    BigDecimal sum = new BigDecimal(0);
    StringBuilder unfinished = new StringBuilder(model.getList().size() * 15);
    for (int i = 0,s =  model.getList().size(); i < s; i++) {
      CashierOrderGoods order = model.getList().get(i);
      BigDecimal number;
      if (CashierOrderGoods.ORIGINAL_TYPE.equals(order.getType())) {
        number = order.getCostPrice().subtract(order.getDiscount()).multiply(BigDecimal.valueOf(order.getNum().intValue()));
        order.setCurrentPrice(order.getCostPrice());
      } else {
        number = order.getCostPrice().subtract(order.getDiscount()).multiply(BigDecimal.valueOf(order.getNum().intValue()));
        order.setCurrentPrice(order.getDiscount());
      }
      order.setSubtotal(number);
      boolean b = false;
      if(order.getOrderId() != null){
        b = cashierOrderGoodsService.update(order);
      }else {
        b = cashierOrderGoodsService.insert(order);
      }
      if (b) {
        if(i < 6){
          unfinished.append(",").append(order.getGoodsName()).append("*").append(order.getNum());
        }
        num += order.getNum();
        sum = sum.add(number);
      }
    }
    model.setNum(num);
    model.setAmount(sum.subtract(model.getFictitious() != null ? model.getFictitious() : new BigDecimal(0)));
    model.setActualPay(model.getAmount());
    if(unfinished.length() > 0){
      model.setUnfinished(unfinished.substring(1));
    }
    return cashierOrderMapper.updateByPrimaryKey(model) > 0;
  }

  @Override
  public CashierOrder get(CashierOrder model) {
    CashierUser user = UserUtil.getLocalUser();
    switch ((int)user.getType()){
      case 1:model.setParentId(null);break;
      case 2:model.setParentId(null);break;
      case 3:model.setParentId(null);break;
    }
    return cashierOrderMapper.selectByPrimaryKey(model);
  }

  @Override
  public boolean delete(CashierOrder model) {
    return cashierOrderMapper.deleteByPrimaryKey(model.getId()) > 0;
  }

  @Override
  public PageInfo<CashierOrder> getList(CashierOrder model) throws Exception {
    CashierUser user = UserUtil.getLocalUser();
    switch ((int)user.getType()){
      case 1:model.setParentId(null);break;
      case 2:model.setParentId(null);break;
      case 3:model.setParentId(null);break;
    }
//    if (user.getParentId().equals(user.getId())) {
//      model.setParentId(user.getParentId());
//    } else {
//      model.setUid(user.getId());
//    }
    PageHelper.startPage(model.getPageNo(), model.getPageSize(), model.getOrderBy());
    List<CashierOrder> list = cashierOrderMapper.selectAll(model);
    if(list != null && !list.isEmpty()){
      for(CashierOrder co : list){
        co.setOrderEdsc(DES3Util.encode(co.getOrderId()));
      }
    }
    PageInfo<CashierOrder> result = new PageInfo<CashierOrder>(list);
    return result;
  }

  @Override
  public List<CashierOrder> query(CashierOrder model) throws Exception {
    CashierUser user = UserUtil.getLocalUser();
    if (user.getParentId().equals(user.getId())) {
      model.setParentId(user.getParentId());
    } else {
      model.setUid(user.getId());
    }
    List<CashierOrder> l = cashierOrderMapper.selectByTime(model);
    if(l != null && !l.isEmpty()){
      for(CashierOrder co : l){
        co.setOrderEdsc(DES3Util.encode(co.getOrderId()));
      }
    }
    return l;
  }

  @Override
  @Transactional
  public boolean refund(CashierOrder model, String type) {
    model.setStatus(CashierOrder.ORDER_REFUND);
    //退款成功
    int r = cashierOrderMapper.updateByPrimaryKey(model);
    if(r > 0){
      //是否新建订单
      CashierOrderGoods detail = new CashierOrderGoods();
      detail.setOrderId(model.getOrderId());
      List<CashierOrderGoods> l = cashierOrderGoodsService.getAllList(detail);
      Map<String,CashierOrderGoods> inGoods = new HashMap<String,CashierOrderGoods>();
      for(CashierOrderGoods cog : l){
        inGoods.put(cog.getGoodsCode(),cog);
      }

      for(CashierOrderGoods md : model.getList()){
        CashierOrderGoods check = inGoods.get(md.getGoodsCode());
        if(!check.getNum().equals(md.getNum())){
          check.setNum(check.getNum() - md.getNum());
        } else {
          inGoods.remove(md.getGoodsCode());
        }
      }
      if(!inGoods.isEmpty()){
        CashierOrder order = new CashierOrder();
        order.setStatus(CashierOrder.ORDER_IN);
        order.setOrderId("SY" + System.currentTimeMillis());
        List<CashierOrderGoods> inList = new ArrayList<>();
        for (Map.Entry<String, CashierOrderGoods> entry : inGoods.entrySet()) {
          inList.add(entry.getValue());
        }
        order.setList(inList);
        insertNew(order);
      }
    }
    if(type.equals("1")){
      for(CashierOrderGoods md : model.getList()){
        CashierGoods g = new CashierGoods();
        g.setGoodsCode(md.getGoodsCode());
        g.setInventory(md.getNum());
        cashierGoodsService.updateGoods(g);
      }
    }
    return true;
  }

  private void insertNew(CashierOrder model) {
    Date d = new Date();
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setOrderTime(d);
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(d);
    model.setSingleNum(model.getList().size());
    Integer num = 0;
    BigDecimal sum = new BigDecimal(0);
    StringBuilder unfinished = new StringBuilder(model.getList().size() * 15);
    for (int i = 0,s =  model.getList().size(); i < s; i++) {
      CashierOrderGoods order = model.getList().get(i);
      BigDecimal number;
      if (CashierOrderGoods.ORIGINAL_TYPE.equals(order.getType())) {
        number = order.getCostPrice().multiply(BigDecimal.valueOf(order.getNum().intValue()));
        order.setCurrentPrice(order.getCostPrice());
      } else {
        number = order.getDiscount().multiply(BigDecimal.valueOf(order.getNum().intValue()));
        order.setCurrentPrice(order.getDiscount());
      }
      order.setSubtotal(number);
      order.setOrderId(model.getOrderId());
      boolean b = cashierOrderGoodsService.insert(order);
      if (b) {
        if(i < 6){
          unfinished.append(",").append(order.getGoodsName()).append("*").append(order.getNum());
        }
        num += order.getNum();
        sum = sum.add(number);
      }
    }
    model.setNum(num);
    model.setAmount(sum.subtract(model.getFictitious() != null ? model.getFictitious() : new BigDecimal(0)));
    model.setActualPay(model.getAmount());
    if(unfinished.length() > 0){
      model.setUnfinished(unfinished.substring(1));
    }
    cashierOrderMapper.insert(model);
  }

  @Override
  public int check(CashierOrder model) {
    CashierOrder order = get(model);
    if(order.getFictitious() != null && order.getFictitious().compareTo(BigDecimal.ZERO) == 1){
      CashierOrderGoods detail = new CashierOrderGoods();
      detail.setOrderId(model.getOrderId());
      List<CashierOrderGoods> l = cashierOrderGoodsService.getAllList(detail);
      if(model.getList().size() != l.size()){
        return 2;
      }
      for(CashierOrderGoods md : model.getList()){
        for(CashierOrderGoods d : l){
          if(md.getId().equals(d.getId()) && !md.getNum().equals(d.getNum())){
           return 1;
          }
        }
      }
    }
    return 0;
  }

  @Override
  public CashierPayType pay(CashierOrder model) {
    CashierOrder order = cashierOrderMapper.selectByPrimaryKey(model);
    CashierPayType cpt = new CashierPayType();
    CashierOrderGoods cashierOrderGoods=cashierOrderGoodsMaper.selectByOrderId(order.getOrderId());
    cashierOrderGoods.setOrderId(order.getOrderId());
    cashierOrderGoods.setDiscount((order.getFictitious()));
    cashierOrderGoods.setSubtotal(order.getActualPay());
    PayTypeEnum p = PayTypeEnum.valueOf(order.getPayType());
    cpt.setStatus(1);
    if(p.equals(PayTypeEnum.XJ) || p.equals(PayTypeEnum.YHK)){
      cashierOrderGoodsMaper.updateByPrimaryKey(cashierOrderGoods);
      cpt.setImage("images/wancheng.jpg");
      cpt.setText("支付已成功");
      deduction(order.getOrderId());
      return cpt;
    }
    cpt.setAmount(order.getActualPay().toString());
    cpt.setType(p.getCode());
    List<CashierPayType> cptList = cashierPayTypeService.getPay(cpt);
    if(cptList == null || cptList.isEmpty()){
      cashierOrderGoodsMaper.updateByPrimaryKey(cashierOrderGoods);
      cpt.setImage("images/shibai.jpg");
      cpt.setStatus(0);
      cpt.setText("请配置支付二维码");
      return cpt;
    }
    CashierPayType def = null;
    for(CashierPayType pt : cptList){
      pt.setImage("payfile/"+ pt.getImage());
      pt.setText("请扫描二维码");
      if(!pt.getAmount().equals("0")){
        def = pt;
        break;
      }
      def = pt;
    }
    cashierOrderGoodsMaper.updateByPrimaryKey(cashierOrderGoods);
    deduction(order.getOrderId());
    return def;
  }

  private void deduction(String orderId) {
    CashierOrderGoods model = new CashierOrderGoods();
    model.setOrderId(orderId);
    List<CashierOrderGoods> l = cashierOrderGoodsService.getAllList(model);
    for (CashierOrderGoods cog : l) {
      CashierGoods cg = new CashierGoods();
      cg.setGoodsCode(cog.getGoodsCode());
      cg.setInventory(0 - cog.getNum());
      cashierGoodsService.updateGoods(cg);
    }
  }

  @Override
  public PageInfo<CashierOrder> getReports(CashierReports model) {
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
    List<CashierOrder> list = cashierOrderMapper.selectReports(model);
    PageInfo<CashierOrder> result = new PageInfo<CashierOrder>(list);
    return result;
  }

  @Override
  public boolean updateByAmt(CashierOrder model) {
    CashierUser user = UserUtil.getLocalUser();
    model.setParentId(user.getParentId());
    model.setUid(user.getId());
    model.setUname(user.getAccount());
    model.setUpdateTime(new Date());
    return cashierOrderMapper.updateByPrimaryKey(model) > 0;
  }
}
