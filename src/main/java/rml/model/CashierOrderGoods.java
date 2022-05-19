package rml.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashierOrderGoods extends BaseModel {

  public static Integer ORIGINAL_TYPE = 1;

  public static Integer DISCOUNT_TYPE = 0;

  private String orderId;

  private String goodsCode;

  private String goodsName;

  private Integer num;

  private BigDecimal costPrice;

  private BigDecimal discount;

  private BigDecimal currentPrice;

  private Integer type;

  private BigDecimal subtotal;

  public CashierOrderGoods() {
  }

  public CashierOrderGoods(CashierGoods g) {
    this.setGoodsCode(g.getGoodsCode());
    this.setGoodsName(g.getGoodsName());
    this.setNum(1);
    this.setCostPrice(g.getSalesPrice());
    this.setDiscount(g.getDiscount());
    if(g.getDiscount() == null || "0.00".equals(g.getDiscount().toString())){
      this.setType(ORIGINAL_TYPE);
    } else {
      this.setType(DISCOUNT_TYPE);
    }

  }
}
