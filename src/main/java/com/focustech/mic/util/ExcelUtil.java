package com.focustech.mic.util;

import com.focustech.mic.pojo.MicCase;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: songboxue
 * Date: 2018/1/31
 * Description:
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static int getIndexByLetter(String letters) {
        char[] chars = letters.toCharArray();//拆分成char数组
        if(chars.length == 1){
            return chars[0] -65;//A的ascii码为65
        }else{
            return (chars[0]-64)*26 + (chars[1] -65);//excel的列排序规则，Z之后是AA；由此规则计算
        }
    }

    /*
    解析excel
     */
    public static List<List<String>> parseExcel(int startRow, int startColumn, int colSize, byte[] buffer) throws IOException {
        InputStream in = null;
        List<List<String>> result = new ArrayList<>();
        try{
            in = new ByteArrayInputStream(buffer);
            result = parseExcel(startRow,startColumn,colSize,in);
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            in.close();
        }
        return result;
    }

    /*
    解析excel
     */
    public static List<List<String>> parseExcel(int startRow, int startColumn, int colSize, InputStream in) throws
            IOException {
        List<List<String>> result = new ArrayList<>();
        //创建workbook对象
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //取第一张sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        int lastCol = startColumn + colSize;
        if(sheet != null){
            //取当前表单的最后一行行号
            int lastRow = sheet.getLastRowNum();
            for(int i = startRow;i<=lastRow;i++){
                row = sheet.getRow(i);
                if(row != null){
                    List<String> rowList = new ArrayList<>();
                    for(int j = startColumn;j<lastCol;j++){
                        String colValue = getValue(row.getCell(j));
                        rowList.add(colValue);
                    }
                    result.add(rowList);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("staic-access")
    private static String getValue(XSSFCell cell) {
       if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
           return String.valueOf(cell.getBooleanCellValue());
       }else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
           if(HSSFDateUtil.isCellDateFormatted(cell)){
               return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
           }else{
               return new BigDecimal(cell.getNumericCellValue()).toPlainString();
           }
       }else{
           return String.valueOf(cell.getStringCellValue());
       }
    }

    /*
    将获得到的list转换成micCase的JavaBean
     */
    public static void listToBean(MicCase micCase, List<String> dataList, String[] caseFeilds) throws
            Exception{
        int len = caseFeilds.length;
        for(int i=0;i<len;i++){
            invokeSet(micCase,dataList.get(i).toString(),caseFeilds[i]);
        }
    }

    private static void invokeSet(MicCase micCase, String value,String caseFeild ) throws Exception {
        Method method;
        if(caseFeild == "csrfToken" || caseFeild == "projectId"){
            method = micCase.getClass().getMethod("set"+caseFeild.substring(0, 1).toUpperCase() + caseFeild.substring(1),Integer.class);
            method.invoke(micCase,Integer.valueOf(value));
        }else{
            method = micCase.getClass().getMethod("set"+caseFeild.substring(0, 1).toUpperCase() + caseFeild.substring(1),String.class);
            method.invoke(micCase,value);
        }
    }
}
