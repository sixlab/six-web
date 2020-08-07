package cn.sixlab.six.web.models;

import java.io.Serializable;
import java.util.Date;

public class PostAttr implements Serializable {
    private Integer id;

    private String attrCode;

    private String attrType;

    private String attrName;

    private String attrSummary;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode == null ? null : attrCode.trim();
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public String getAttrSummary() {
        return attrSummary;
    }

    public void setAttrSummary(String attrSummary) {
        this.attrSummary = attrSummary == null ? null : attrSummary.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}