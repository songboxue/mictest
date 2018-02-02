package com.focustech.mic.util;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
                        String colValue = row.getCell(j).toString();
                        rowList.add(colValue);
                    }
                    result.add(rowList);
                }
            }
        }
        return result;
    }
}
