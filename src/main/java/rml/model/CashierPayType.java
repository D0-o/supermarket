package rml.model;

import lombok.Data;

import java.util.Date;

@Data
public class CashierPayType extends BaseModel {

    private Long id;
    private Integer type;

    private String acct;

    private String amount;

    private String image;

    private String text;

    private Integer status;

    private String uname;

}
