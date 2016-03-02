package com.simon7in.strategy.util;

import com.alibaba.common.lang.StringUtil;

import java.util.HashMap;
import java.util.Map;


public class KeyValueParser {

    public static final char KEY_VALUE_SPLIT = ':';
    public static final char KEY_VALUE_GROUP_SPLIT = ';';
    public static final char KEY_VALUE_SPLIT_EQUALS_SIGN = '=';

    /**
     * wlb_ext_order.feature使用=分隔，wlb_order使用的是:分隔，wlb_order_bill使用的是:分隔
     */

    public static String parseMap2String(Map<String, String> mapData) throws Exception {
        StringBuilder sb = new StringBuilder(512);
        if (mapData == null) {
            return sb.toString();
        }
        for (Map.Entry<String, String> mapEntry : mapData.entrySet()) {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();

            dataFormatCheck(key);
            dataFormatCheck(value);


            sb.append(key);
            sb.append(KEY_VALUE_SPLIT);
            sb.append(value);
            sb.append(KEY_VALUE_GROUP_SPLIT);
        }
        return sb.toString();
    }


    public static String parseMap2String(Map<String, String> mapData, char keyValueGroupSplit, char keyValueSplit) throws Exception {
        StringBuilder sb = new StringBuilder(512);
        if (mapData == null) {
            return sb.toString();
        }
        for (Map.Entry<String, String> mapEntry : mapData.entrySet()) {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();

            try {
                dataFormatCheck(key);
                dataFormatCheck(value);
            } catch (Exception e) {
                //e.printStackTrace();
                throw e;
            }

            sb.append(key);
            sb.append(keyValueSplit);
            sb.append(value);
            sb.append(keyValueGroupSplit);
        }
        return sb.toString();
    }

    /**
     * mapData需要组装成toString的数据源
     * templateKey 代表一些标准的key,没有这些key这个方法不执行
     *
     * @param mapData
     * @param templateKey
     * @return
     */
    public static String parseMap2String(Map<String, String> mapData, Map<String, String> templateKey) throws Exception {
        StringBuilder sb = new StringBuilder(512);
        if (mapData != null && mapData.size() > 0) {
            for (Map.Entry<String, String> mapEntry : mapData.entrySet()) {
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();
                if (templateKey != null && StringUtil.isEmpty(templateKey.get(key))) {
                    throw new Exception("缺少必须参数"+ key);
                }
                dataFormatCheck(key);
                dataFormatCheck(value);

                sb.append(key);
                sb.append(KEY_VALUE_SPLIT);
                sb.append(value);
                sb.append(KEY_VALUE_GROUP_SPLIT);
            }
        }


        return sb.toString();
    }

    public static Map<String, String> parseString2Map(String stringData) {
        Map<String, String> map = new HashMap<String, String>();
        if (stringData == null) {
            return map;
        }
        if (StringUtil.isNotEmpty(stringData)) {
            String[] strList = stringData.split(String.valueOf(KEY_VALUE_GROUP_SPLIT));
            for (String line : strList) {
                String[] kv = line.split(String.valueOf(KEY_VALUE_SPLIT));
                if (kv.length >= 2) {
                    map.put(kv[0], kv[1]);
                }
            }
        }
        return map;
    }


    public static Map<String, String> parseString2Map(String stringData, char keyValueGroupSplit, char keyValueSplit) {
        Map<String, String> map = new HashMap<String, String>();
        if (stringData == null) {
            return map;
        }
        if (StringUtil.isNotEmpty(stringData)) {
            String[] strList = stringData.split(String.valueOf(keyValueGroupSplit));
            for (String line : strList) {
                String[] kv = line.split(String.valueOf(keyValueSplit));
                if (kv.length >= 2) {
                    map.put(kv[0], kv[1]);
                }
            }
        }
        return map;
    }

    private static void dataFormatCheck(String str) throws Exception {
        if (str == null) {
            return;
        }
        if (str.indexOf(KEY_VALUE_GROUP_SPLIT) != -1 || str.indexOf(KEY_VALUE_SPLIT) != -1) {
            //throw new Exception(WhcErrorConstants.WHC_UNKNOW_ERROR.getErrorCode(), "character ':' or ';' key value structure is not allowed:[" + str + "]");
            //modify by shengp 20140901 提示语改成中文
            throw new Exception("输入信息[" + str + "]中包含有特殊字符':',';'!");
            //end
        }

    }

    public static String getAddKeyVauleStr(String key, String value) {
        StringBuffer sb = new StringBuffer();
        sb.append(KEY_VALUE_GROUP_SPLIT);
        sb.append(key);
        sb.append(KEY_VALUE_SPLIT_EQUALS_SIGN);
        sb.append(value);
        sb.append(KEY_VALUE_GROUP_SPLIT);
        return sb.toString();
    }

}
