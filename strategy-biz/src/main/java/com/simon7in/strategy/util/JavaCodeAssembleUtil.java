package com.simon7in.strategy.util;

import com.alibaba.common.lang.StringUtil;

import javax.validation.constraints.NotNull;

/**
 * Created by jiayu.shenjy on 2016/2/29.
 */
public class JavaCodeAssembleUtil {

    private static final String JAVA_CODE_HEADER_PLACE_HOLDER = "_javaCodeHeaderPlaceHolder_";
    private static final String JAVA_CODE_PLACE_HOLDER = "_javaCodePlaceHolder_";
    private static final String JAVA_BEAN_PLACE_HOLDER = "_javaBeanPlaceHolder_";

    static String headImport = "package tmp;\n" +
            "import com.simon7in.strategy.util.ScriptInvoker;\n" +
            "import javax.annotation.Resource;\n" +
            "import org.slf4j.Logger;\n" +
            "import org.slf4j.LoggerFactory;\n" +
            "import com.google.gson.Gson;\n" +
            "import JSONUtil;\n" +
            "import com.alibaba.fastjson.TypeReference;\n"+
            JAVA_CODE_HEADER_PLACE_HOLDER;

    static String contentCode ="public class _fileName_ extends ScriptInvoker {\n" +
            "    private Logger logger = LoggerFactory.getLogger(this.getClass());\n" +
            "  @Resource \n" + JAVA_BEAN_PLACE_HOLDER + ";" +
            "\n" +
            "  @Override   \n" +
            " public void callScript() throws Exception {\n" +
            " logger.error(\"javaCodeRunResult!fileName=_fileName_;run start!\");\n" +
            JAVA_CODE_PLACE_HOLDER +
            "\n" +
            " logger.error(\"javaCodeRunResult!fileName=_fileName_;run success!\");\n" +
            "}\n" +
            "}";

    public static String getJavaCodeText(String header, @NotNull String bean,@NotNull String content) {
        if (StringUtil.isBlank(header)) {
            header = "";
        }
        String headerCode = headImport.replaceAll(JAVA_CODE_HEADER_PLACE_HOLDER, header.replaceAll("\\$", "\\\\\\$"));
        String beanCode = contentCode.replaceAll(JAVA_BEAN_PLACE_HOLDER, bean.replaceAll("\\$", "\\\\\\$"));
        String contentCode = beanCode.replaceAll(JAVA_CODE_PLACE_HOLDER, content.replaceAll("\\$", "\\\\\\$"));
        return headerCode + contentCode;
    }

}

