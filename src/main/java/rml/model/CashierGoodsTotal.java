package rml.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CashierGoodsTotal implements Serializable {

  private Long countTotal;

  private BigDecimal salesPriceTotal;

  private BigDecimal purchasePriceTotal;

  private Long inventoryTotal;

  private BigDecimal discountTotal;

}