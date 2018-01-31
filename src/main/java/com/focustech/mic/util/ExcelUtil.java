package com.focustech.mic.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * User: songboxue
 * Date: 2018/1/31
 * Description:
 */
public class ExcelUtil {
    public static int getIndexByLetter(String letters) {
        char[] chars = letters.toCharArray();//拆分成char数组
        if(chars.length == 1){
            return chars[0] -65;//A的ascii码为65
        }else{
            return (chars[0]-64)*26 + (chars[1] -65);//excel的列排序规则，Z之后是AA；由此规则计算
        }
    }

    //todo excel处理工具类
    public static List<List<String>> parseExcel(int startRow, int startColumn, int colSize, byte[] buffer) {


        return null;
    }
}
