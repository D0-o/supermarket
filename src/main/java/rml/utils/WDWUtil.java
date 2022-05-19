package rml.utils;

/**
 * @author wsh
 * @version 1.0
 * @Title rml.utils
 * @Copyright 2020
 * @Description: 描述
 * @Company: fere.com
 * @Created on 2020年04月08日 23:01
 */
public class WDWUtil {

  // @描述：是否是2003的excel，返回true是2003
  public static boolean isExcel2003(String filePath)  {
    return filePath.matches("^.+\\.(?i)(xls)$");
  }

  //@描述：是否是2007的excel，返回true是2007
  public static boolean isExcel2007(String filePath)  {
    return filePath.matches("^.+\\.(?i)(xlsx)$");
  }

}
