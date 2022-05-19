package rml.service;

import com.github.pagehelper.PageInfo;
import rml.model.CashierGoods;
import rml.model.CashierInGoods;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月23日 22:35
 */
public interface IBaseService<T> {
  PageInfo<T> getList(T model) throws Exception;

  boolean insert(T model);

  boolean delete(T model);

  boolean update(T model);

  T get(T model);

}
