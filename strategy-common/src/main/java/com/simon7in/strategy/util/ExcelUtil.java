package com.simon7in.strategy.util;

import com.alibaba.common.lang.StringUtil;
import com.simon7in.strategy.constents.ExcelFileTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yucheng.syc on 1:12 2015/9/24.
 */
public class ExcelUtil {
    public static <T> List<T> defaultImportXlsx(Class<T> clazz,
                                                Map<String, String> defineMapping, int dataRowNumStart, String url, String suffix)
            throws Exception {
        if (StringUtil.isBlank(url)) {
            throw new IllegalArgumentException("无效的excel文件!");
        }

        InputStream is = new FileInputStream(url);
        return defaultImportXlsx(clazz, defineMapping, dataRowNumStart, is, suffix);
    }


    public static <T> List<T> defaultImportXlsx(Class<T> clazz,
                                                Map<String, String> defineMapping, int dataRowNumStart, InputStream is, String suffix)
            throws IOException, Exception {
        // 创建book
        Workbook workbook = null;
        if ("xlsx".equals(suffix)) {
            workbook = new XSSFWorkbook(is);
        } else if ("xls".equals(suffix)) {
            workbook = new HSSFWorkbook(is);
        } else {
            throw new RuntimeException("ExcelUtil 导入excel文件的后缀不符合要求!");
        }
        // 获得指定的表
        Sheet sheet = workbook.getSheetAt(0);
        // 获得数据总行数
        int rowCount = sheet.getLastRowNum();
        if (rowCount < 1) {
            return Collections.emptyList();
        }
        String[] properties = new String[defineMapping.size()];
        int i = 0;
        for (String value : defineMapping.values()) {
            properties[i] = value;
            i++;
        }

        is.close();
        return readRow(sheet, rowCount, properties, dataRowNumStart, clazz);
    }

    public static <T> Map<String, Object> defaultExportXlsxBigData(
            String sheetName, List<T> dataList, Map<String, String> defineMapping,
            int dataRowNumStart, String suffix) throws Exception {
        if (StringUtil.isBlank(sheetName)) {
            sheetName = "sheet1";
        }

        // 创建book
        Workbook wb = null;
        if ("xlsx".equals(suffix)) {
            wb = new XSSFWorkbook();
        } else if ("xls".equals(suffix)) {
            wb = new HSSFWorkbook();
        } else {
            throw new RuntimeException("ExcelUtil 导出excel文件的后缀不符合要求!");
        }

        // 表头单元格边STYLE
        CellStyle headStyle = getDefautHeaderStyle(wb);
        // 数据 单元格STYLE
        CellStyle dataStyle = getDefautDataStyle(wb);
        // 创建 包裹信息sheet
        Sheet sheet = wb.createSheet(sheetName);

        String[] titles = new String[defineMapping.size()];
        String[] properties = new String[defineMapping.size()];
        int[] widths = new int[defineMapping.size()];
        int i = 0;
        for (String key : defineMapping.keySet()) {

            // 表头
            titles[i] = key;
            // 数据映射对象属性
            properties[i] = defineMapping.get(key);
//            if (defineMapping[i].length > 2) {
            // 列宽
//                widths[i] = Integer.parseInt(defineMapping[i][2]);
//            }
        }
        //设置列宽
//        if(defineMapping[0].length > 2) {
//            for (int j = 0; j < widths.length; j++) {
//                sheet.setColumnWidth(j, Integer.valueOf(widths[j]) * 38);
//            }
//        }

        // writeHeader
        writeRow(sheet, titles, headStyle, 0);

        // writeData
        int dataRowNumNext = writeRow(sheet, dataList, properties, dataStyle,
                dataRowNumStart);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("workbook", wb);
        map.put("sheet", sheet);
        map.put("dataRowNumNext", dataRowNumNext);

        return map;
    }

    /**
     * 根据给定的模板导出数据
     *
     * @param sheetName       指定的Sheet名,默认为sheet1
     * @param dataList        输出数据列表 ，数据对象要有public的get方法
     * @param defineMapping   数据定义 {"商品Id":"itemId"}
     * @param dataRowNumStart 开始写入数据的行索引
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> defaultExportXlsx(String sheetName,
                                                            List<T> dataList, Map<String, String> defineMapping, int dataRowNumStart, String suffix)
            throws Exception {
        if (StringUtil.isBlank(sheetName)) {
            sheetName = "sheet1";
        }

        // 创建book
        Workbook wb = null;
        if ("xlsx".equals(suffix)) {
            wb = new XSSFWorkbook();
        } else if ("xls".equals(suffix)) {
            wb = new HSSFWorkbook();
        } else {
            throw new RuntimeException("ExcelUtil 导出excel文件的后缀不符合要求!");
        }

        // 表头单元格边STYLE
        CellStyle headStyle = getDefautHeaderStyle(wb);
        // 数据 单元格STYLE
        CellStyle dataStyle = getDefautDataStyle(wb);
        // 创建 包裹信息sheet
        Sheet sheet = wb.createSheet(sheetName);

        String[] titles = new String[defineMapping.size()];
        String[] properties = new String[defineMapping.size()];
        int[] widths = new int[defineMapping.size()];
        int i = 0;
        for (String key : defineMapping.keySet()) {
            // 表头
            titles[i] = key;
            // 数据映射对象属性
            properties[i] = defineMapping.get(key);
//            if (defineMapping[i].length > 2) {
            // 列宽
//                widths[i] = Integer.parseInt(defineMapping[i][2]);
//            }
        }
        //设置列宽
//        if(defineMapping[0].length > 2) {
//            for (int j = 0; j < widths.length; j++) {
//                sheet.setColumnWidth(j, Integer.valueOf(widths[j]) * 38);
//            }
//        }
        // writeHeader
        writeRow(sheet, titles, headStyle, 0);

        // writeData
        int dataRowNumNext = writeRow(sheet, dataList, properties, dataStyle,
                dataRowNumStart);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("workbook", wb);
        map.put("sheet", sheet);
        map.put("dataRowNumNext", dataRowNumNext);

        return map;
    }


    private static CellStyle getDefaultHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        // 线条
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        return style;
    }

    /*
    * 读excel sheet到List<T>
    * @param properties 按属性的set方法注入对象
    */
    private static <T> List<T> readRow(Sheet sheet, int rowCount,
                                       String[] properties, int rowNumStart, Class<T> clazz)
            throws Exception {
        List<T> result = new ArrayList<T>();
        if (sheet != null && rowCount > 0) {
            Method[] objMds = new Method[properties.length];
            Class<?>[] fieldsType = new Class<?>[properties.length];
            int i = 0;
            for (String prop : properties) {
                Class<?> cls = clazz;
                while (cls != Object.class) {
                    try {
                        fieldsType[i] = cls.getDeclaredField(prop).getType();
                        objMds[i] = cls.getDeclaredMethod(
                                "set" + prop.substring(0, 1).toUpperCase()
                                        + prop.substring(1), fieldsType[i]);
                        break;
                    } catch (NoSuchFieldException e) {
                        cls = cls.getSuperclass();
                        if (cls == Object.class) {
                            throw new NoSuchFieldException();
                        }
                    }
                }
                i++;
            }

            // 逐行读取数据
            for (int rowIndex = rowNumStart; rowIndex <= rowCount; rowIndex++) {
                // 获得行对象
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    // 去除Cell全为空的Row
                    boolean notblankRow = false;
                    T obj = clazz.newInstance();
                    rowNumStart++;
                    // 获得本行中各单元格中的数据
                    for (i = 0; i < objMds.length; i++) {
                        try {
                            Cell cell = row.getCell(i);
                            // 获得指定单元格中数据
                            if (int.class == fieldsType[i]
                                    || Integer.class == fieldsType[i]
                                    || short.class == fieldsType[i]
                                    || Short.class == fieldsType[i]
                                    || byte.class == fieldsType[i]
                                    || Byte.class == fieldsType[i]) {
                                String str = StringUtil
                                        .trim((String) getCellDetail(cell));
                                if (StringUtil.isBlank(str)) {
                                    continue;
                                }
                                notblankRow = true;
                                objMds[i].invoke(obj,
                                        new BigDecimal(str).intValue());
                            } else if (long.class == fieldsType[i]
                                    || Long.class == fieldsType[i]) {
                                String str = StringUtil
                                        .trim((String) getCellDetail(cell));
                                if (StringUtil.isBlank(str)) {
                                    continue;
                                }
                                notblankRow = true;
                                objMds[i].invoke(obj,
                                        new BigDecimal(str).longValue());
                            } else if (double.class == fieldsType[i]
                                    || Double.class == fieldsType[i]) {
                                String str = StringUtil
                                        .trim((String) getCellDetail(cell));
                                if (StringUtil.isBlank(str)) {
                                    continue;
                                }
                                notblankRow = true;
                                objMds[i].invoke(obj,
                                        new BigDecimal(str).doubleValue());
                            } else if (float.class == fieldsType[i]
                                    || Float.class == fieldsType[i]) {
                                String str = StringUtil
                                        .trim((String) getCellDetail(cell));
                                if (StringUtil.isBlank(str)) {
                                    continue;
                                }
                                notblankRow = true;
                                objMds[i].invoke(obj,
                                        new BigDecimal(str).floatValue());
                            } else if (Number.class == fieldsType[i]
                                    || BigDecimal.class == fieldsType[i]) {
                                String str = StringUtil
                                        .trim((String) getCellDetail(cell));
                                if (StringUtil.isBlank(str)) {
                                    continue;
                                }
                                notblankRow = true;

                                objMds[i].invoke(obj, new BigDecimal(str));
                            } else if (String.class == fieldsType[i]) {
                                String str = (String) getCellDetail(cell);
                                if (StringUtil.isEmpty(str)) {
                                    continue;
                                }
                                notblankRow = true;

                                objMds[i].invoke(obj, fieldsType[i].cast(str));
                            } else {
                                Object resultObj = getCellDetail(cell);
                                if (null == resultObj) {
                                    continue;
                                }
                                notblankRow = true;
                                objMds[i]
                                        .invoke(obj, fieldsType[i].cast(resultObj));
                            }
                        } catch (Exception e) {
                            throw new Exception("第" + rowNumStart + "行数据解析失败！请检查数据！" + e);
                        }
                    }
                    if (notblankRow) {
                        result.add(obj);
                    }
                }
            }
        }
        return result;
    }


    /**
     * 获得单元格中的内容,如果是date类型则 ，获取该cell的date值的yyyy-MM-dd hh:mm:ss格式
     */
    public static Object getCellDetail(Cell cell) {
        Object result = null;
        if (cell != null) {
            int cellType = cell.getCellType();
            switch (cellType) {
                case HSSFCell.CELL_TYPE_STRING:
                    result = cell.getRichStringCellValue().getString();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是date类型则 ，获取该cell的date值的yyyy-MM-dd hh:mm:ss格式
                        result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(HSSFDateUtil.getJavaDate(cell
                                        .getNumericCellValue()));
                    } else { // 纯数字
                        result = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    result = String.valueOf(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    result = null;
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    result = null;
                    break;
            }
        }
        return result;
    }

    /**
     * 创建头部的默认样式
     */
    private static CellStyle getDefautHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        // 线条
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // 背景
        // style.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
        return style;
    }

    /**
     * 设置默认数据部分格式
     */
    private static CellStyle getDefautDataStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        // 默认不设置
        return style;
    }


    /**
     * 写一组对象到excel sheet
     *
     * @param properties 按属性的get方法获取对象内容
     */
    private static <T> int writeRow(Sheet sheet, List<T> dataList,
                                    String[] properties, CellStyle style, int rowNumStart)
            throws Exception {
        if (dataList != null && dataList.size() > 0) {
            Method[] objMds = new Method[properties.length];
            int i = 0;
            for (String prop : properties) {
                Class<?> cls = dataList.get(0).getClass();
                while (cls != Object.class) {
                    try {
                        objMds[i] = cls.getDeclaredMethod("get"
                                + prop.substring(0, 1).toUpperCase()
                                + prop.substring(1));
                        break;
                    } catch (NoSuchMethodException e) {
                        cls = cls.getSuperclass();
                        if (cls == Object.class) {
                            throw new NoSuchMethodException();
                        }
                    }
                }
                i++;
            }

            for (Object dataObj : dataList) {
                Row row = sheet.createRow(rowNumStart);
                rowNumStart++;
                for (i = 0; i < objMds.length; i++) {
                    Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                    Object value = objMds[i].invoke(dataObj);
                    setCellValue(cell, value);
                    if (style != null) {
                        cell.setCellStyle(style);
                    }
                }
            }
        }
        return rowNumStart;
    }

    /**
     * 给格子设置值
     */
    private static void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof Float) {
            // 转成string，不然Excel显示的不一样
            cell.setCellValue(value.toString());
        } else if (value instanceof Double) {
            // 转成string，不然Excel显示的会不一样
            cell.setCellValue(value.toString());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    /**
     * 写数组数据到一个excel行
     */
    private static void writeRow(Sheet sheet, String[] values, CellStyle style,
                                 int rowNum) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < values.length; i++) {
            Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
            cell.setCellValue(values[i]);
            if (style != null) {
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 根据给定的模板导出数据
     *
     * @param sheetName       指定的Sheet名,默认为sheet1
     * @param dataList        输出数据列表 ，数据对象要有public的get方法
     * @param defineMapping   数据定义 {{"XX商家名","supplierName","100"},{"XX商家ID","supplierID"
     *                        ,"100"},{"表头名称","对象属性","列宽"}...}
     * @param dataRowNumStart 开始写入数据的行索引
     * @return
     * @throws Exception
     */
    public static Map<String, Object> defaultExportXlsxByMap(String sheetName,
                                                             List<Map<String, String>> dataList, Map<String, String> defineMapping, int dataRowNumStart, String suffix)
            throws Exception {
        if (StringUtil.isBlank(sheetName)) {
            sheetName = "sheet1";
        }

        // 创建book
        Workbook wb = null;
        if ("xlsx".equals(suffix)) {
            wb = new XSSFWorkbook();
        } else if ("xls".equals(suffix)) {
            wb = new HSSFWorkbook();
        } else {
            throw new RuntimeException("ExcelUtil 导出excel文件的后缀不符合要求!");
        }

        // 表头单元格边STYLE
        CellStyle headStyle = getDefautHeaderStyle(wb);
        // 数据 单元格STYLE
        CellStyle dataStyle = getDefautDataStyle(wb);
        // 创建 包裹信息sheet
        Sheet sheet = wb.createSheet(sheetName);

        String[] titles = new String[defineMapping.size()];
        String[] properties = new String[defineMapping.size()];
//        int[] widths = new int[defineMapping.size()];
        int i = 0;
        for (String key : defineMapping.keySet()) {
            // 表头
            titles[i] = key;
            // 数据映射对象属性
            properties[i] = defineMapping.get(key);
            i++;
//            if (defineMapping[i].length > 2) {
            // 列宽
//                widths[i] = Integer.parseInt(defineMapping[i][2]);
//            }
        }
        //设置列宽
//        if(defineMapping[0].length > 2) {
//            for (int j = 0; j < widths.length; j++) {
//                sheet.setColumnWidth(j, Integer.valueOf(widths[j]) * 38);
//            }
//        }
        // writeHeader
        writeRow(sheet, titles, headStyle, 0);

        // writeData
        int dataRowNumNext = writeRowByMap(sheet, dataList, properties, dataStyle,
                dataRowNumStart);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("workbook", wb);
        map.put("sheet", sheet);
        map.put("dataRowNumNext", dataRowNumNext);

        return map;
    }

    /**
     * 写一组对象到excel sheet
     *
     * @param properties 按Map获取对象内容
     */
    private static int writeRowByMap(Sheet sheet, List<Map<String, String>> dataList,
                                     String[] properties, CellStyle style, int rowNumStart)
            throws Exception {
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, String> map : dataList) {
                Row row = sheet.createRow(rowNumStart);
                rowNumStart++;
                int i = 0;
                for (String key : properties) {
                    Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                    Object value = map.get(key);
                    setCellValue(cell, value);
                    if (style != null) {
                        cell.setCellStyle(style);
                    }
                    i++;
                }
            }
        }
        return rowNumStart;
    }

    public static void outputExcel(String fileName, Map<String, Object> excel) throws Exception {
        String fileOutputPath = "/home/admin/downloads/" + fileName + "." + ExcelFileTypeEnum.EXCEL_2007.getCode();
        File localFile = new File(fileOutputPath);
        if (!localFile.exists()) {
            localFile.createNewFile();
            localFile.setLastModified(System.currentTimeMillis());
            localFile.setReadable(true, false);
            localFile.setWritable(true, false);
        }
        FileOutputStream outputFile = null;
        try {
            if (localFile.exists() && localFile.length() == 0) {
                localFile.setLastModified(System.currentTimeMillis());
                Workbook workbook = (Workbook) excel.get("workbook");
                outputFile = new FileOutputStream(localFile);
                workbook.write(outputFile);
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (outputFile != null)
                outputFile.close();
        }
    }
}
