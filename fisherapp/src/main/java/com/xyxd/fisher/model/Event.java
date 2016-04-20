
package com.xyxd.fisher.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event {

    private Integer id;
    private Boolean isPublished;
    private String avatarUrl;
    private String name;
    private Date eventFrom;
    private Date evenUntil;
    private Date regeristFrom;
    private Date regeristUntil;
    private Double price;
    private Double discount;
    private Double discountPrice;
    private Date oxygenTime;
    private Double buyPrice;
    private String fishType;
    private Double fishQuantity;
    private Integer positions;
    private Integer positionsRemain;
    private String description;
    private String intro;
    private Integer shopId;
    private Shop shop;
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
     *     The eventFrom
     */
    public Date getEventFrom() {
        return eventFrom;
    }

    /**
     * 
     * @param eventFrom
     *     The eventFrom
     */
    public void setEventFrom(Date eventFrom) {
        this.eventFrom = eventFrom;
    }

    /**
     * 
     * @return
     *     The evenUntil
     */
    public Date getEvenUntil() {
        return evenUntil;
    }

    /**
     * 
     * @param evenUntil
     *     The evenUntil
     */
    public void setEvenUntil(Date evenUntil) {
        this.evenUntil = evenUntil;
    }

    /**
     * 
     * @return
     *     The regeristFrom
     */
    public Date getRegeristFrom() {
        return regeristFrom;
    }

    /**
     * 
     * @param regeristFrom
     *     The regeristFrom
     */
    public void setRegeristFrom(Date regeristFrom) {
        this.regeristFrom = regeristFrom;
    }

    /**
     * 
     * @return
     *     The regeristUntil
     */
    public Date getRegeristUntil() {
        return regeristUntil;
    }

    /**
     * 
     * @param regeristUntil
     *     The regeristUntil
     */
    public void setRegeristUntil(Date regeristUntil) {
        this.regeristUntil = regeristUntil;
    }

    /**
     * 
     * @return
     *     The price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * 
     * @param discount
     *     The discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * 
     * @return
     *     The discountPrice
     */
    public Double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 
     * @param discountPrice
     *     The discountPrice
     */
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 
     * @return
     *     The oxygenTime
     */
    public Date getOxygenTime() {
        return oxygenTime;
    }

    /**
     * 
     * @param oxygenTime
     *     The oxygenTime
     */
    public void setOxygenTime(Date oxygenTime) {
        this.oxygenTime = oxygenTime;
    }

    /**
     * 
     * @return
     *     The buyPrice
     */
    public Double getBuyPrice() {
        return buyPrice;
    }

    /**
     * 
     * @param buyPrice
     *     The buyPrice
     */
    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * 
     * @return
     *     The fishType
     */
    public String getFishType() {
        return fishType;
    }

    /**
     * 
     * @param fishType
     *     The fishType
     */
    public void setFishType(String fishType) {
        this.fishType = fishType;
    }

    /**
     * 
     * @return
     *     The fishQuantity
     */
    public Double getFishQuantity() {
        return fishQuantity;
    }

    /**
     * 
     * @param fishQuantity
     *     The fishQuantity
     */
    public void setFishQuantity(Double fishQuantity) {
        this.fishQuantity = fishQuantity;
    }

    /**
     * 
     * @return
     *     The positions
     */
    public Integer getPositions() {
        return positions;
    }

    /**
     * 
     * @param positions
     *     The positions
     */
    public void setPositions(Integer positions) {
        this.positions = positions;
    }

    /**
     * 
     * @return
     *     The positionsRemain
     */
    public Integer getPositionsRemain() {
        return positionsRemain;
    }

    /**
     * 
     * @param positionsRemain
     *     The positionsRemain
     */
    public void setPositionsRemain(Integer positionsRemain) {
        this.positionsRemain = positionsRemain;
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
     *     The shopId
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * 
     * @param shopId
     *     The shopId
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * 
     * @return
     *     The shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * 
     * @param shop
     *     The shop
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
