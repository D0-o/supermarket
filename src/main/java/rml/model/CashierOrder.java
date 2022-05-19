package rml.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CashierOrder extends BaseModel {

    public static Integer ORDER_IN = 1;
    public static Integer ORDER_OUT = 0;
    public static Integer ORDER_REFUND = 4;

    private String orderEdsc;

    private String orderId;

    private BigDecimal amount;

    private BigDecimal fictitious;

    private BigDecimal actualPay;

    private BigDecimal giveChange;

    private Integer num;

    private Integer singleNum;

    private String payType;

    private Integer status;

    private String unfinished;

    private Date orderTime;

    private List<CashierOrderGoods> list;


}