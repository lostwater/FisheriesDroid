
package com.xyxd.fisher.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Information {

    private Integer id;
    private String title;
    private String videoUrl;
    private String imageUrl;
    private String intro;
    private String content;
    private Date createdTime;
    private Date publishedTime;
    private Boolean isPublished;
    private Integer informationTypeId;
    private InformationType informationType;
    private Integer celebrityId;
    private Celebrity celebrity;
    private String applicationUserId;
    private ApplicationUser applicationUser;
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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 
     * @param videoUrl
     *     The videoUrl
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 
     * @return
     *     The intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 
     * @param intro
     *     The intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 
     * @param createdTime
     *     The createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 
     * @return
     *     The publishedTime
     */
    public Date getPublishedTime() {
        return publishedTime;
    }

    /**
     * 
     * @param publishedTime
     *     The publishedTime
     */
    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    /**
     * 
     * @return
     *     The isPublished
     */
    public Boolean getIsPublished() {
        return isPublished;
    }

    /**
     * 
     * @param isPublished
     *     The isPublished
     */
    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    /**
     * 
     * @return
     *     The informationTypeId
     */
    public Integer getInformationTypeId() {
        return informationTypeId;
    }

    /**
     * 
     * @param informationTypeId
     *     The informationTypeId
     */
    public void setInformationTypeId(Integer informationTypeId) {
        this.informationTypeId = informationTypeId;
    }

    /**
     * 
     * @return
     *     The informationType
     */
    public InformationType getInformationType() {
        return informationType;
    }

    /**
     * 
     * @param informationType
     *     The informationType
     */
    public void setInformationType(InformationType informationType) {
        this.informationType = informationType;
    }

    /**
     * 
     * @return
     *     The celebrityId
     */
    public Integer getCelebrityId() {
        return celebrityId;
    }

    /**
     * 
     * @param celebrityId
     *     The celebrityId
     */
    public void setCelebrityId(Integer celebrityId) {
        this.celebrityId = celebrityId;
    }

    /**
     * 
     * @return
     *     The celebrity
     */
    public Celebrity getCelebrity() {
        return celebrity;
    }

    /**
     * 
     * @param celebrity
     *     The celebrity
     */
    public void setCelebrity(Celebrity celebrity) {
        this.celebrity = celebrity;
    }

    /**
     * 
     * @return
     *     The applicationUserId
     */
    public String getApplicationUserId() {
        return applicationUserId;
    }

    /**
     * 
     * @param applicationUserId
     *     The applicationUserId
     */
    public void setApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    /**
     * 
     * @return
     *     The applicationUser
     */
    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    /**
     * 
     * @param applicationUser
     *     The applicationUser
     */
    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
