package rml.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.model
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月02日 21:35
 */
@Data
public class CashierChart {

  private String name;

  private BigDecimal[] data;

}
