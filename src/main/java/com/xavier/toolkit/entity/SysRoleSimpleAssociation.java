package com.xavier.toolkit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SysRoleSimpleAssociation {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String roleName;

    /**
     *
     */
    private Integer enabled;

    /**
     *
     */
    private Integer createdBy;

    /**
     *
     */
    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}

