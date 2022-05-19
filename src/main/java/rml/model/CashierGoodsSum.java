package rml.model;

import lombok.Data;

@Data
public class CashierGoodsSum extends BaseModel {

  private String goodsCode;

  private String goodsName;

  private String inventory;

  private String salesPrice;

  private String purchasePrice;

  private String profit;

  private String rate;

  private String sum;

}