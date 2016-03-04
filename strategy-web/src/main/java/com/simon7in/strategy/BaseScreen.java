package com.simon7in.strategy;

import com.simon7in.strategy.util.JSONUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class BaseScreen {
    private Logger logger = LoggerFactory
            .getLogger(BaseScreen.class);

    @Resource
    public HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;



    protected void contextJsonReturn(Object obj) {
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            String jsonObject = JSONUtil.toJsonString(obj);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("异步反回信息给客户端出现异常", e);
        }
    }

    /**
     * 对象转换为String
     *
     * @param object
     * @return
     */
    protected String Object2String(Object object) {
        return ToStringBuilder.reflectionToString(object);
    }

}
