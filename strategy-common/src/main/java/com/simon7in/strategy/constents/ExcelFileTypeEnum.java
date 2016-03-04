package com.simon7in.strategy.constents;

/**
 * Created by yucheng.syc on 1:01 2015/9/24.
 */
public enum ExcelFileTypeEnum {
    EXCEL_2003("xls"),
    EXCEL_2007("xlsx");
    private String code;
    private ExcelFileTypeEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
