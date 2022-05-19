package rml.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import rml.model.CashierUser;

public interface ICashierUserService {

	List<CashierUser> getAll();
	
    boolean insert(CashierUser model);

    boolean update(CashierUser model);

    boolean delete(CashierUser model);

    PageInfo<CashierUser> getList(CashierUser model);

    CashierUser login(CashierUser model);
}
