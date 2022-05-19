package rml.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashierInGoods extends BaseModel {

  public static final Integer IN_GOODS = 1;

  private String goodsName;

  private String goodsCode;

  private BigDecimal salesPrice;

  private BigDecimal purchasePrice;

  private Integer type;

  private Integer inventory;

  private Integer supplier;

  private String productionPeriod;

  private Integer shelfLife;

  private String batch;

  private Integer status;

}