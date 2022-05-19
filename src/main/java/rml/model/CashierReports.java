package rml.model;

import lombok.Data;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.model
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月01日 0:00
 */
@Data
public class CashierReports extends BaseModel {

  private Integer store;

  private String time;

  private String goodsCode;

  private String orderId;

  private Integer type;

  private Integer top;
}
