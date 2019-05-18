package win.smile.house;

import java.util.Date;

/**
 * @author weifw
 * @date 2019-03-15-22:02
 */
public class HouseAugentInfo {

    private Integer id;
    private String orignalUrl;
    private String destinationUrl;
    private String sourceType;
    private Integer status;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrignalUrl() {
        return orignalUrl;
    }

    public void setOrignalUrl(String orignalUrl) {
        this.orignalUrl = orignalUrl;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
