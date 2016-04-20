
package com.xyxd.fisher.model;

import java.util.HashMap;
import java.util.Map;

public class Ad {

    private Integer id;
    private String avatarUrl;
    private Integer adCat;
    private Integer adType;
    private Integer eventId;
    private Event event;
    private Integer informationId;
    private Information information;
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
     *     The avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 
     * @param avatarUrl
     *     The avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 
     * @return
     *     The adCat
     */
    public Integer getAdCat() {
        return adCat;
    }

    /**
     * 
     * @param adCat
     *     The adCat
     */
    public void setAdCat(Integer adCat) {
        this.adCat = adCat;
    }

    /**
     * 
     * @return
     *     The adType
     */
    public Integer getAdType() {
        return adType;
    }

    /**
     * 
     * @param adType
     *     The adType
     */
    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    /**
     * 
     * @return
     *     The eventId
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * 
     * @param eventId
     *     The eventId
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * 
     * @return
     *     The event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * 
     * @param event
     *     The event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * 
     * @return
     *     The informationId
     */
    public Integer getInformationId() {
        return informationId;
    }

    /**
     * 
     * @param informationId
     *     The informationId
     */
    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    /**
     * 
     * @return
     *     The information
     */
    public Information getInformation() {
        return information;
    }

    /**
     * 
     * @param information
     *     The information
     */
    public void setInformation(Information information) {
        this.information = information;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
