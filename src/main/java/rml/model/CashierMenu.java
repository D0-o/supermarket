package rml.model;

import lombok.Data;

@Data
public class CashierMenu extends BaseModel {
  private String menuName;

  private String icon;

  private String menuUrl;

  private Integer menuType;

  private Integer uiType;

  private Integer type;

  private Integer sort;
}