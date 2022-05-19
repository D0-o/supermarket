package rml.dto;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    ORDER_FAIL(202, "订单获取失败"),
    ORDER_ALL_FAIL(203, "订单带有折扣,请整单退款"),
    ORDER_PAY_FAIL(204, "订单支付失败"),
    CHEACKED_TODAY(205, "今日已盘点"),
    PARAMETER_NONE(400, "缺少必要参数"),
    BEOVERDUE(401, "过期"),
    ERROR(500, "系统异常");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}