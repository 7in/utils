package com.cainiao.strategy;

import com.google.gson.Gson;
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


    /**
     * 判断是否是白名单用户 ,域账号前缀，即邮箱前缀
     *
     * @return
     */
//    protected String getUserABCName() {
//        BucSSOUser user = getCurrentUser();
//        String[] userAccount = user.getEmailAddr().split("@");
//        return userAccount[0];
//    }

//    protected String getUserName() {
//        BucSSOUser user = getCurrentUser();
//        return user.getNickNameCn();
//    }


//    protected BucSSOUser getCurrentUser() {
//        BucSSOUser user = null;
//        try {
//            user = SimpleUserUtil.getBucSSOUser(request);
//        } catch (IOException e) {
//            logger.error("IOException", e);
//        } catch (ServletException e) {
//            logger.error("ServletException", e);
//        }
//        return user;
//    }

    protected void getMessage() {
//        BucSSOUser user = getCurrentUser();
//        if (null != user) {
//            request.setAttribute("logined", true);
//            request.setAttribute("userNick", user.getNickNameCn());
//            request.setAttribute("empId",user.getEmpId());
//        } else {
//            request.setAttribute("logined", false);
//        }
//        request.setAttribute("envDesc", EnvConfig.GETDESC(EnvConfig.ENV));
        request.setAttribute("requestUrl", request.getRequestURL().toString());
        request.setAttribute("requestUri", request.getRequestURI().toString());
//        request.setAttribute("envCode", EnvConfig.ENV);
    }

//    protected boolean judgeEnvIsDaily() {
//        if (EnvConfig.ENV.equals("DAILY")) {
//            return true;
//        } else
//            return false;
//    }

    protected void contextJsonReturn(Object obj) {
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            String jsonObject = new Gson().toJson(obj);
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
