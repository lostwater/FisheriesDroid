
package com.xyxd.fisher.model;

import java.util.HashMap;
import java.util.Map;

public class Video {

    private Integer id;
    private Integer lcId;
    private String name;
    private String imageUrl;
    private String vu;
    private Integer celebrityId;
    private Celebrity celebrity;
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
     *     The lcId
     */
    public Integer getLcId() {
        return lcId;
    }

    /**
     * 
     * @param lcId
     *     The lcId
     */
    public void setLcId(Integer lcId) {
        this.lcId = lcId;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
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
     *     The vu
     */
    public String getVu() {
        return vu;
    }

    /**
     * 
     * @param vu
     *     The vu
     */
    public void setVu(String vu) {
        this.vu = vu;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
