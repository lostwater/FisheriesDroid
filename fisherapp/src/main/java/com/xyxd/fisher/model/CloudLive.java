
package com.xyxd.fisher.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class CloudLive {

    private String activityId;
    private String activityName;
    private Integer activityStatus;
    private String coverImgUrl;
    private String createTime;
    private String description;
    private String endTime;
    private Integer liveNum;
    private Integer needIpWhiteList;
    private Integer needRecord;
    private Integer needFullView;
    private Integer needTimeShift;
    private Integer neededPushAuth;
    private String pushIpWhiteList;
    private Integer pushUrlValidTime;
    private String startTime;
    private Integer userCount;
    private Integer playMode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The activityId
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * 
     * @param activityId
     *     The activityId
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * 
     * @return
     *     The activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 
     * @param activityName
     *     The activityName
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 
     * @return
     *     The activityStatus
     */
    public Integer getActivityStatus() {
        return activityStatus;
    }

    /**
     * 
     * @param activityStatus
     *     The activityStatus
     */
    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    /**
     * 
     * @return
     *     The coverImgUrl
     */
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    /**
     * 
     * @param coverImgUrl
     *     The coverImgUrl
     */
    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    /**
     * 
     * @return
     *     The createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime
     *     The createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The liveNum
     */
    public Integer getLiveNum() {
        return liveNum;
    }

    /**
     * 
     * @param liveNum
     *     The liveNum
     */
    public void setLiveNum(Integer liveNum) {
        this.liveNum = liveNum;
    }

    /**
     * 
     * @return
     *     The needIpWhiteList
     */
    public Integer getNeedIpWhiteList() {
        return needIpWhiteList;
    }

    /**
     * 
     * @param needIpWhiteList
     *     The needIpWhiteList
     */
    public void setNeedIpWhiteList(Integer needIpWhiteList) {
        this.needIpWhiteList = needIpWhiteList;
    }

    /**
     * 
     * @return
     *     The needRecord
     */
    public Integer getNeedRecord() {
        return needRecord;
    }

    /**
     * 
     * @param needRecord
     *     The needRecord
     */
    public void setNeedRecord(Integer needRecord) {
        this.needRecord = needRecord;
    }

    /**
     * 
     * @return
     *     The needFullView
     */
    public Integer getNeedFullView() {
        return needFullView;
    }

    /**
     * 
     * @param needFullView
     *     The needFullView
     */
    public void setNeedFullView(Integer needFullView) {
        this.needFullView = needFullView;
    }

    /**
     * 
     * @return
     *     The needTimeShift
     */
    public Integer getNeedTimeShift() {
        return needTimeShift;
    }

    /**
     * 
     * @param needTimeShift
     *     The needTimeShift
     */
    public void setNeedTimeShift(Integer needTimeShift) {
        this.needTimeShift = needTimeShift;
    }

    /**
     * 
     * @return
     *     The neededPushAuth
     */
    public Integer getNeededPushAuth() {
        return neededPushAuth;
    }

    /**
     * 
     * @param neededPushAuth
     *     The neededPushAuth
     */
    public void setNeededPushAuth(Integer neededPushAuth) {
        this.neededPushAuth = neededPushAuth;
    }

    /**
     * 
     * @return
     *     The pushIpWhiteList
     */
    public String getPushIpWhiteList() {
        return pushIpWhiteList;
    }

    /**
     * 
     * @param pushIpWhiteList
     *     The pushIpWhiteList
     */
    public void setPushIpWhiteList(String pushIpWhiteList) {
        this.pushIpWhiteList = pushIpWhiteList;
    }

    /**
     * 
     * @return
     *     The pushUrlValidTime
     */
    public Integer getPushUrlValidTime() {
        return pushUrlValidTime;
    }

    /**
     * 
     * @param pushUrlValidTime
     *     The pushUrlValidTime
     */
    public void setPushUrlValidTime(Integer pushUrlValidTime) {
        this.pushUrlValidTime = pushUrlValidTime;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The userCount
     */
    public Integer getUserCount() {
        return userCount;
    }

    /**
     * 
     * @param userCount
     *     The userCount
     */
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    /**
     * 
     * @return
     *     The playMode
     */
    public Integer getPlayMode() {
        return playMode;
    }

    /**
     * 
     * @param playMode
     *     The playMode
     */
    public void setPlayMode(Integer playMode) {
        this.playMode = playMode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
