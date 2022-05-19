package rml.model;

import lombok.Data;

import java.util.Date;

@Data
public class CashierUser extends BaseModel {

  private String account;

  private String password;

  private Integer type;

  private Date loginTime;

  private Long createId;
}