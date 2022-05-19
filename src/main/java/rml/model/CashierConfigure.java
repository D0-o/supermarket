package rml.model;

import lombok.Data;

import java.util.Date;

@Data
public class CashierConfigure {
  private Long id;

  private String code;

  private String json;

  private Integer status;

  private Date updatetime;

}