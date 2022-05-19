package rml.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CashierLossGoods extends BaseModel {

  private String goodsName;

  private String goodsCode;

  private BigDecimal purchasePrice;

  private Integer type;

  private Integer reportLoss;

}