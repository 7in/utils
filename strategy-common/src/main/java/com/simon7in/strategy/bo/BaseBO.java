package com.simon7in.strategy.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jiayu.shenjy on 15-4-9.
 */
public class BaseBO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 是否删除
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * @return isDeleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }
    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    /**
     * @return gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    /**
     * @param gmtCreate the gmtCreate to set
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    /**
     * @return gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }
    /**
     * @param gmtModified the gmtModified to set
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}
