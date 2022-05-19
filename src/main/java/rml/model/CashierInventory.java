package rml.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CashierInventory extends BaseModel {

    private String inventoryNo;

    private Date inventoryTime;

    private Integer beforeInventory;

    private Integer afterInventory;

    private Integer difference;

    private Integer status;

}