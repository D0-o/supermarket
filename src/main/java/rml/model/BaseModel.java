package rml.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shc
 * @version 1.0
 * @Description: model基类
 * @Copyright 2019
 * @Created on 2019/12/18 22:07
 */
@Data
@ToString
public class BaseModel implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 父级
     */
    private Long parentId;
    /**
     * 当前用户
     */
    private Long uid;
    /**
     * 用户名称
     */
    private String uname;
    /**
     * 备注
     */
    private String ramerk;
    /**
     * 开始时间
     */
    private String sTime;
    /**
     * 结束时间
     */
    private String eTime;
    /**
     * 当前页
     */
    private Integer pageNo;
    /**
     * 页数量
     */
    private Integer pageSize;
    /**
     * 排序
     */
    private String orderBy;

}
