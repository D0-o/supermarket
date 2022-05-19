import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rml.model.CashierUser;
import rml.service.ICashierUserService;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestMybatis {

	private static final Logger logger = Logger.getLogger(TestMybatis.class);

	private ICashierUserService muserService;

	public ICashierUserService getMuserService() {
		return muserService;
	}

	@Autowired
	public void setMuserService(ICashierUserService muserService) {
		this.muserService = muserService;
	}
	
	@Test
	public void test1() {
		
		List<CashierUser> list = muserService.getAll();
		logger.info(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
	}
	
}
