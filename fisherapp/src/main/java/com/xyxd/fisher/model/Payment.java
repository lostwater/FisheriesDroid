
package com.xyxd.fisher.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment {

    private Integer id;
    private Date paymentTime;
    private Date createTime;
    private Double amount;
    private Double refundAmount;
    private String description;
    private String channel;
    private Boolean isPaid;
    private Boolean isRefund;
    private String pingChargeId;
    private String channelPaymentId;
    private String channelPaymentUserId;
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
     *     The paymentTime
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 
     * @param paymentTime
     *     The paymentTime
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 
     * @return
     *     The createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime
     *     The createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The refundAmount
     */
    public Double getRefundAmount() {
        return refundAmount;
    }

    /**
     * 
     * @param refundAmount
     *     The refundAmount
     */
    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
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
     *     The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 
     * @param channel
     *     The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 
     * @return
     *     The isPaid
     */
    public Boolean getIsPaid() {
        return isPaid;
    }

    /**
     * 
     * @param isPaid
     *     The isPaid
     */
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * 
     * @return
     *     The isRefund
     */
    public Boolean getIsRefund() {
        return isRefund;
    }

    /**
     * 
     * @param isRefund
     *     The isRefund
     */
    public void setIsRefund(Boolean isRefund) {
        this.isRefund = isRefund;
    }

    /**
     * 
     * @return
     *     The pingChargeId
     */
    public String getPingChargeId() {
        return pingChargeId;
    }

    /**
     * 
     * @param pingChargeId
     *     The pingChargeId
     */
    public void setPingChargeId(String pingChargeId) {
        this.pingChargeId = pingChargeId;
    }

    /**
     * 
     * @return
     *     The channelPaymentId
     */
    public String getChannelPaymentId() {
        return channelPaymentId;
    }

    /**
     * 
     * @param channelPaymentId
     *     The channelPaymentId
     */
    public void setChannelPaymentId(String channelPaymentId) {
        this.channelPaymentId = channelPaymentId;
    }

    /**
     * 
     * @return
     *     The channelPaymentUserId
     */
    public String getChannelPaymentUserId() {
        return channelPaymentUserId;
    }

    /**
     * 
     * @param channelPaymentUserId
     *     The channelPaymentUserId
     */
    public void setChannelPaymentUserId(String channelPaymentUserId) {
        this.channelPaymentUserId = channelPaymentUserId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
