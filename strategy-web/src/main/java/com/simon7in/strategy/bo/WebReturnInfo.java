package com.simon7in.strategy.bo;

/**
 * Created with IntelliJ IDEA.
 * User: jiayu.shenjy
 * Date: 14-3-29
 * Time: 下午5:38
 * To change this template use File | Settings | File Templates.
 */
public class WebReturnInfo {
	
	/**
	 * 结果是否为成功
	 */
	private boolean  success;
	/**
     * 成功失败标记位
     */
    private String code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 调用成功返回的数据
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public boolean isSuccess() {
 		return success;
 	}

 	public void setSuccess(boolean success) {
 		this.success = success;
 	}


    public static WebReturnInfo succeed(Object data){
        WebReturnInfo result = new WebReturnInfo();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static WebReturnInfo unSucceed(String code, String msg){
        WebReturnInfo result = new WebReturnInfo();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static WebReturnInfo unSucceed(String msg){
        WebReturnInfo result = new WebReturnInfo();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

}
