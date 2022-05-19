package rml.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CashierGoods extends BaseModel {

  private String goodsName;

  private String goodsCode;

  private BigDecimal salesPrice;

  private BigDecimal purchasePrice;

  private Integer type;

  private Integer inventory;

  private String pinyin;

  private Integer supplier;

  private String productionPeriod;

  private Integer shelfLife;

  private Integer status;

  private BigDecimal discount;

  private Integer obtain;

  private List<String> codes;

  public CashierGoods() {
  }

  public CashierGoods(CashierLossGoods cig) {
    this.setGoodsName(cig.getGoodsName());
    this.setGoodsCode(cig.getGoodsCode());
    this.setInventory(0 - cig.getReportLoss());
  }

  public CashierGoods(CashierInGoods cig) {
    this.setGoodsName(cig.getGoodsName());
    this.setGoodsCode(cig.getGoodsCode());
    this.setInventory(cig.getInventory());
    this.setSupplier(cig.getSupplier());
    this.setType(cig.getType());
    this.setSalesPrice(cig.getSalesPrice());
    this.setPurchasePrice(cig.getPurchasePrice());
  }
}