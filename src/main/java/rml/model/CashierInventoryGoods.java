package rml.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CashierInventoryGoods extends BaseModel {

  private String inventoryNo;

  private String goodsName;

  private String goodsCode;

  private BigDecimal purchasePrice;

  private Integer type;

  private Integer beforeInventory;

  private Integer afterInventory;

  private Integer difference;

  private String time;

}