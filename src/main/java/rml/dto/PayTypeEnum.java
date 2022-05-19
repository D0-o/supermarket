package rml.dto;

import lombok.Getter;

@Getter
public enum PayTypeEnum {
    XJ(0, "XJ"),
    YHK(3, "YHK"),
    ZFB(1, "ZFB"),
    WX(2, "WX");
    private Integer code;
    private String name;

    PayTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    // 普通方法
    public static PayTypeEnum getCode(String name) {
        for (PayTypeEnum c : PayTypeEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}