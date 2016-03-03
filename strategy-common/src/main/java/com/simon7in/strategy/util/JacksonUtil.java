package com.simon7in.strategy.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * json字符串与java对象的互相转换包装类
 * Created by fachao.zfc on 2016/1/28.
 */
public class JacksonUtil {
    public static ObjectMapper objectMapper;

    /**
     * 泛型方法，将json字符串转换为相应的javaBean对象
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转换为具有泛型类型参数的java对象，如List<String>
     * @param jsonStr
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 递归转化为List和Map的嵌套对象
     * @param jsonStr
     * @return
     */
    public static Object readValueRecursionNew(String jsonStr) {


        return jsonStr;
    }

    /**
     * 递归转化为List和Map的嵌套对象
     * @param jsonStr
     * @return
     */
    public static Object readValueRecursion(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return jsonStr;
        }
        //对不符合json规则的字符串进行标准化适配
        if (jsonStr.startsWith("\"[") && jsonStr.endsWith("]\"") || jsonStr.startsWith("\"{") && jsonStr.endsWith("}\"")) {
            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
        }

        //如果以["开头，认为是数组类型
        if (jsonStr.trim().startsWith("[") && jsonStr.endsWith("]")) {
            List stringList = JacksonUtil.readValue(jsonStr, new TypeReference<List>() {
            });
            //没有成功
            if (stringList == null) {
                //判断从1位置开始的是否为"，并且倒数第二位是不是"
                int startIndex = getIndexFirstChar(jsonStr, 1, ' ', false);
                int endIndex = getLastIndexFirstChar(jsonStr, jsonStr.length() - 2, ' ', false);
                if (startIndex != jsonStr.length() && endIndex != -1 && jsonStr.charAt(startIndex) == '"' && jsonStr.charAt(endIndex) == '"') {
                    jsonStr = "[" + jsonStr.substring(startIndex + 1, endIndex) + "]";
                    Object o = JacksonUtil.readValueRecursion(jsonStr);
                    if (o == null) {
                        return jsonStr;
                    } else if (o instanceof List) {
                        stringList = (List) o;
                    } else {
                        return o;
                    }
                } else {
                    return jsonStr;
                }
            }
            for (int i = 0; i < stringList.size(); i++) {
                Object item = stringList.get(i);
                if (item instanceof String) {
                    Object child = readValueRecursion((String) item);
                    stringList.set(i, child);
                }
            }

            return stringList;
        }

        //如果以{开头，认为是Map类型
        if (jsonStr.startsWith("{") && jsonStr.endsWith("}")) {
            //首先判断{后面的字符是否"或者数字，如果是，则继续，否则，认为就是一个字符串
            int indexFirstChar = getIndexFirstChar(jsonStr, 1, ' ', false);
            if (indexFirstChar == jsonStr.length()) {
                return jsonStr;
            }
            char charAt = jsonStr.charAt(indexFirstChar);
            if (charAt != '\"' && "0123456789".indexOf(charAt + "") == -1) {
                return jsonStr;
            }

            //当成Map类型解析
            Map stringStringMap = JacksonUtil.readValue(jsonStr, new TypeReference<Map>() {
            });

            //遍历
            Set<Map.Entry> entrySet = stringStringMap.entrySet();
            Iterator<Map.Entry> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry next = iterator.next();
                Object value = next.getValue();
                if (value instanceof String) {
                    Object child = readValueRecursion((String) value);
                    stringStringMap.put(next.getKey(), child);
                }
            }

            return stringStringMap;
        }

        //返回该对象
        return jsonStr;
    }

    /**
     * 转换为json字符串
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从数据源的指定位置查找第一次出现的字符
     * @param source
     * @param fromIndex
     * @param target
     * @param isTarget 查询的目标是，是目标字符第一次出现还是目标字符第一次不出现
     * @return
     */
    public static int getIndexFirstChar(String source, int fromIndex, char target, Boolean isTarget) {
        int i = fromIndex;

        //是目标字符第一次出现就结束
        if (isTarget) {
            while (i<source.length()-1) {
                if (source.charAt(i) != target) {
                    i++;
                    continue;
                } else {
                    break;
                }
            }

        } else {//目标字符第一次不出现就结束
            while (i<source.length()-1) {
                if (source.charAt(i) == target) {
                    i++;
                    continue;
                } else {
                    break;
                }
            }
        }

        return i;
    }

    /**
     * 从数据源的指定位置向前查找第一次出现的字符
     * @param source
     * @param fromIndex
     * @param target
     * @param isTarget 查询的目标是，是目标字符第一次出现还是目标字符第一次不出现
     * @return
     */
    public static int getLastIndexFirstChar(String source, int fromIndex, char target, Boolean isTarget) {
        int i = fromIndex;

        //是目标字符第一次出现就结束
        if (isTarget) {
            while (i>0) {
                if (source.charAt(i) != target) {
                    i--;
                    continue;
                } else {
                    break;
                }
            }

        } else {//目标字符第一次不出现就结束
            while (i>0) {
                if (source.charAt(i) == target) {
                    i--;
                    continue;
                } else {
                    break;
                }
            }
        }

        return i;
    }
}
