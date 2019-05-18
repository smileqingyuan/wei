package win.smile.house;

import java.util.Date;

/**
 * @author weifw
 * @date 2019-03-15-22:02
 */
public class HouseAugentUrl {

    private Integer id;
    private String name;
    private String tel;
    private String sourceType;
    private Integer agentUrlId;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getAgentUrlId() {
        return agentUrlId;
    }

    public void setAgentUrlId(Integer agentUrlId) {
        this.agentUrlId = agentUrlId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
