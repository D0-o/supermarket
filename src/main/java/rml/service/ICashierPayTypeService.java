package rml.service;

import rml.model.CashierPayType;

import java.util.List;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.service
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月30日 21:52
 */
public interface ICashierPayTypeService extends IBaseService<CashierPayType> {
  List<CashierPayType> getAllList(CashierPayType model);

  List<CashierPayType> getPay(CashierPayType cpt);
}
