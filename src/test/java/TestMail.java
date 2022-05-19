import com.alibaba.fastjson.JSON;
import rml.utils.DES3Util;

import java.util.Map;

/**
 * @author wsh
 * @version 1.0
 * @Title PACKAGE_NAME
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年03月28日 15:46
 */
public class TestMail {
  public static void main(String[] args) {
    try {
      String t = "{\"machine\": 1,\"address\": \"沈阳市皇姑区泰山路69号\",\"tel\": \"13391927398\",\"number\": 1,\"ramerk\": \"* 凭小票可在7天内退货\",\"preview\": 1}";
      Map<String,Object> l = JSON.parseObject(t,Map.class);
      System.out.println(JSON.toJSONString(l));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
