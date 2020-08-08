package cn.sixlab.six.web.models;

import java.io.Serializable;
import java.util.Date;

public class RankItem implements Serializable {
    private Integer id;

    private Integer groupId;

    private Integer itemRank;

    private String itemName;

    private Integer itemHit;

    private Integer itemChange;

    private String itemIntro;

    private String baikeId;

    private String doubanId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getItemRank() {
        return itemRank;
    }

    public void setItemRank(Integer itemRank) {
        this.itemRank = itemRank;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getItemHit() {
        return itemHit;
    }

    public void setItemHit(Integer itemHit) {
        this.itemHit = itemHit;
    }

    public Integer getItemChange() {
        return itemChange;
    }

    public void setItemChange(Integer itemChange) {
        this.itemChange = itemChange;
    }

    public String getItemIntro() {
        return itemIntro;
    }

    public void setItemIntro(String itemIntro) {
        this.itemIntro = itemIntro == null ? null : itemIntro.trim();
    }

    public String getBaikeId() {
        return baikeId;
    }

    public void setBaikeId(String baikeId) {
        this.baikeId = baikeId == null ? null : baikeId.trim();
    }

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId == null ? null : doubanId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}