package rml.dto;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    private List<String> menu;

    public Result(ResultEnum resultEnum, Object data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = data;
    }

    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result (ResultEnum resultEnum, String msg, Object data){
        this.code = resultEnum.getCode();
        this.msg = msg;
        this.data = data;
    }
}
