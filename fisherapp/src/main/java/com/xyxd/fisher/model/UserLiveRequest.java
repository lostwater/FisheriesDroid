
package com.xyxd.fisher.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class UserLiveRequest {

    private Integer id;
    private String fullName;
    private String citizenId;
    private String liveName;
    private Integer state;
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
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return
     *     The citizenId
     */
    public String getCitizenId() {
        return citizenId;
    }

    /**
     * 
     * @param citizenId
     *     The citizenId
     */
    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    /**
     * 
     * @return
     *     The liveName
     */
    public String getLiveName() {
        return liveName;
    }

    /**
     * 
     * @param liveName
     *     The liveName
     */
    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    /**
     * 
     * @return
     *     The state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(Integer state) {
        this.state = state;
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
