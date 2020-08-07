package cn.sixlab.six.web.models;

import java.io.Serializable;
import java.util.Date;

public class SiteMenu implements Serializable {
    private Integer id;

    private String menuPosition;

    private Integer menuLevel;

    private String menuName;

    private String menuPath;

    private String menuSummary;

    private Integer menuOrder;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(String menuPosition) {
        this.menuPosition = menuPosition == null ? null : menuPosition.trim();
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    public String getMenuSummary() {
        return menuSummary;
    }

    public void setMenuSummary(String menuSummary) {
        this.menuSummary = menuSummary == null ? null : menuSummary.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}