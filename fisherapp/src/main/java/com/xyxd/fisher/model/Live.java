
package com.xyxd.fisher.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Live {

    private Integer id;
    private String cloudLiveId;
    private CloudLive cloudLive;
    private Integer liveTypeId;
    private LiveType liveType;
    private String chatId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The cloudLiveId
     */
    public String getCloudLiveId() {
        return cloudLiveId;
    }

    /**
     * 
     * @param cloudLiveId
     *     The cloudLiveId
     */
    public void setCloudLiveId(String cloudLiveId) {
        this.cloudLiveId = cloudLiveId;
    }

    /**
     * 
     * @return
     *     The cloudLive
     */
    public CloudLive getCloudLive() {
        return cloudLive;
    }

    /**
     * 
     * @param cloudLive
     *     The cloudLive
     */
    public void setCloudLive(CloudLive cloudLive) {
        this.cloudLive = cloudLive;
    }

    /**
     * 
     * @return
     *     The liveTypeId
     */
    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    /**
     * 
     * @param liveTypeId
     *     The liveTypeId
     */
    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    /**
     * 
     * @return
     *     The liveType
     */
    public LiveType getLiveType() {
        return liveType;
    }

    /**
     * 
     * @param liveType
     *     The liveType
     */
    public void setLiveType(LiveType liveType) {
        this.liveType = liveType;
    }

    /**
     * 
     * @return
     *     The chatId
     */
    public String getChatId() {
        return chatId;
    }

    /**
     * 
     * @param chatId
     *     The chatId
     */
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
