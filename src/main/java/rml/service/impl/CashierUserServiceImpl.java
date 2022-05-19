package rml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.CashierUserMapper;
import rml.model.CashierUser;
import rml.service.ICashierUserService;
import rml.utils.UserUtil;

import java.util.Date;
import java.util.List;

@Service
public class CashierUserServiceImpl implements ICashierUserService {

    @Autowired
    private CashierUserMapper cashierUserMapper;

    @Override
    public List<CashierUser> getAll() {
        return null;
    }

    @Override
    public boolean insert(CashierUser model) {
        CashierUser user = UserUtil.getLocalUser();
        model.setCreateTime(new Date());
        model.setCreateId(user.getId());
        model.setParentId(user.getParentId());
        return cashierUserMapper.insert(model) > 0;
    }

    @Override
    public boolean update(CashierUser model) {
        return cashierUserMapper.updateByPrimaryKey(model) > 0;
    }

    @Override
    public boolean delete(CashierUser model) {
        return cashierUserMapper.deleteByPrimaryKey(model.getId()) > 0;
    }

    @Override
    public PageInfo<CashierUser> getList(CashierUser model) {
        PageHelper.startPage(model.getPageNo(),model.getPageSize(),model.getOrderBy());
        List<CashierUser> list = cashierUserMapper.selectAll(model);
        PageInfo<CashierUser> result = new PageInfo<CashierUser>(list);
        return result;
    }

    @Override
    public CashierUser login(CashierUser model) {
        return cashierUserMapper.select(model);
    }
}
