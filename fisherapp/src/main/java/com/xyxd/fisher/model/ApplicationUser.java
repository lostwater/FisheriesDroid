
package com.xyxd.fisher.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ApplicationUser {

    private List<Shop> followedShops = new ArrayList<Shop>();
    private List<Live> followedLives = new ArrayList<Live>();
    private Integer liveId;
    private Live live;
    private String avatar;
    private String createdTime;
    private String signupClient;
    private String email;
    private Boolean emailConfirmed;
    private String passwordHash;
    private String securityStamp;
    private String phoneNumber;
    private Boolean phoneNumberConfirmed;
    private Boolean twoFactorEnabled;
    private String lockoutEndDateUtc;
    private Boolean lockoutEnabled;
    private Integer accessFailedCount;
    private List<Object> roles = new ArrayList<Object>();
    private List<Object> claims = new ArrayList<Object>();
    private List<Object> logins = new ArrayList<Object>();
    private String id;
    private String userName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The followedShops
     */
    public List<Shop> getFollowedShops() {
        return followedShops;
    }

    /**
     * 
     * @param followedShops
     *     The followedShops
     */
    public void setFollowedShops(List<Shop> followedShops) {
        this.followedShops = followedShops;
    }

    /**
     * 
     * @return
     *     The followedLives
     */
    public List<Live> getFollowedLives() {
        return followedLives;
    }

    /**
     * 
     * @param followedLives
     *     The followedLives
     */
    public void setFollowedLives(List<Live> followedLives) {
        this.followedLives = followedLives;
    }

    /**
     * 
     * @return
     *     The liveId
     */
    public Integer getLiveId() {
        return liveId;
    }

    /**
     * 
     * @param liveId
     *     The liveId
     */
    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    /**
     * 
     * @return
     *     The live
     */
    public Live getLive() {
        return live;
    }

    /**
     * 
     * @param live
     *     The live
     */
    public void setLive(Live live) {
        this.live = live;
    }

    /**
     * 
     * @return
     *     The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return
     *     The createdTime
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * 
     * @param createdTime
     *     The createdTime
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 
     * @return
     *     The signupClient
     */
    public String getSignupClient() {
        return signupClient;
    }

    /**
     * 
     * @param signupClient
     *     The signupClient
     */
    public void setSignupClient(String signupClient) {
        this.signupClient = signupClient;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The emailConfirmed
     */
    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    /**
     * 
     * @param emailConfirmed
     *     The emailConfirmed
     */
    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    /**
     * 
     * @return
     *     The passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * 
     * @param passwordHash
     *     The passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * 
     * @return
     *     The securityStamp
     */
    public String getSecurityStamp() {
        return securityStamp;
    }

    /**
     * 
     * @param securityStamp
     *     The securityStamp
     */
    public void setSecurityStamp(String securityStamp) {
        this.securityStamp = securityStamp;
    }

    /**
     * 
     * @return
     *     The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return
     *     The phoneNumberConfirmed
     */
    public Boolean getPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    /**
     * 
     * @param phoneNumberConfirmed
     *     The phoneNumberConfirmed
     */
    public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    /**
     * 
     * @return
     *     The twoFactorEnabled
     */
    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    /**
     * 
     * @param twoFactorEnabled
     *     The twoFactorEnabled
     */
    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    /**
     * 
     * @return
     *     The lockoutEndDateUtc
     */
    public String getLockoutEndDateUtc() {
        return lockoutEndDateUtc;
    }

    /**
     * 
     * @param lockoutEndDateUtc
     *     The lockoutEndDateUtc
     */
    public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
        this.lockoutEndDateUtc = lockoutEndDateUtc;
    }

    /**
     * 
     * @return
     *     The lockoutEnabled
     */
    public Boolean getLockoutEnabled() {
        return lockoutEnabled;
    }

    /**
     * 
     * @param lockoutEnabled
     *     The lockoutEnabled
     */
    public void setLockoutEnabled(Boolean lockoutEnabled) {
        this.lockoutEnabled = lockoutEnabled;
    }

    /**
     * 
     * @return
     *     The accessFailedCount
     */
    public Integer getAccessFailedCount() {
        return accessFailedCount;
    }

    /**
     * 
     * @param accessFailedCount
     *     The accessFailedCount
     */
    public void setAccessFailedCount(Integer accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    /**
     * 
     * @return
     *     The roles
     */
    public List<Object> getRoles() {
        return roles;
    }

    /**
     * 
     * @param roles
     *     The roles
     */
    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }

    /**
     * 
     * @return
     *     The claims
     */
    public List<Object> getClaims() {
        return claims;
    }

    /**
     * 
     * @param claims
     *     The claims
     */
    public void setClaims(List<Object> claims) {
        this.claims = claims;
    }

    /**
     * 
     * @return
     *     The logins
     */
    public List<Object> getLogins() {
        return logins;
    }

    /**
     * 
     * @param logins
     *     The logins
     */
    public void setLogins(List<Object> logins) {
        this.logins = logins;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
