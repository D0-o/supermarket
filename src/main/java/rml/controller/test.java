package rml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.Result;
import rml.dto.ResultEnum;
import rml.model.CashierUser;

@Controller
public class test {

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(CashierUser model) {
        return new Result(ResultEnum.FAIL, false);
    }

    @RequestMapping(value = "/dome",method = RequestMethod.POST)
    public void test1(@RequestBody CashierUser model) {
        System.out.println(model);
        System.out.println("123123");
    }

    @RequestMapping(value = "/dome1",method = RequestMethod.POST)
    public void test(String id) {
        System.out.println(id);
        System.out.println("123123");
    }

    public void supertest(){
        System.out.println("123");
    }
}
