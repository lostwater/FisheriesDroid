
package com.xyxd.fisher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Live {

    @SerializedName("activityCategory")
    @Expose
    private String activityCategory;
    @SerializedName("activityId")
    @Expose
    private String activityId;
    @SerializedName("activityName")
    @Expose
    private String activityName;
    @SerializedName("activityStatus")
    @Expose
    private Integer activityStatus;
    @SerializedName("coverImgUrl")
    @Expose
    private String coverImgUrl;
    @SerializedName("createTime")
    @Expose
    private String createTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("endTime")
    @Expose
    private Date endTime;
    @SerializedName("liveNum")
    @Expose
    private Integer liveNum;
    @SerializedName("needFullView")
    @Expose
    private Integer needFullView;
    @SerializedName("needIpWhiteList")
    @Expose
    private Integer needIpWhiteList;
    @SerializedName("needRecord")
    @Expose
    private Integer needRecord;
    @SerializedName("needTimeShift")
    @Expose
    private Integer needTimeShift;
    @SerializedName("neededPushAuth")
    @Expose
    private Integer neededPushAuth;
    @SerializedName("playMode")
    @Expose
    private Integer playMode;
    @SerializedName("pushIpWhiteList")
    @Expose
    private String pushIpWhiteList;
    @SerializedName("pushUrlValidTime")
    @Expose
    private Integer pushUrlValidTime;
    @SerializedName("startTime")
    @Expose
    private Date startTime;
    @SerializedName("userCount")
    @Expose
    private String userCount;

    /**
     * 
     * @return
     *     The activityCategory
     */
    public String getActivityCategory() {
        return activityCategory;
    }

    /**
     * 
     * @param activityCategory
     *     The activityCategory
     */
    public void setActivityCategory(String activityCategory) {
        this.activityCategory = activityCategory;
    }

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
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The endTime
     */
    public void setEndTime(Date endTime) {
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
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The userCount
     */
    public String getUserCount() {
        return userCount;
    }

    /**
     * 
     * @param userCount
     *     The userCount
     */
    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

}
