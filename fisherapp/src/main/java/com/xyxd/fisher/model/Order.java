
package com.xyxd.fisher.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private Integer id;
    private Date orderTime;
    private Double orderPrice;
    private String description;
    private Integer quantity;
    private Integer orderStatuId;
    private OrderStatu orderStatu;
    private String code;
    private String phoneNumber;
    private Integer eventId;
    private Event event;
    private Integer paymentId;
    private Payment payment;
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
     *     The orderTime
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 
     * @param orderTime
     *     The orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 
     * @return
     *     The orderPrice
     */
    public Double getOrderPrice() {
        return orderPrice;
    }

    /**
     * 
     * @param orderPrice
     *     The orderPrice
     */
    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
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
     *     The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *     The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return
     *     The orderStatuId
     */
    public Integer getOrderStatuId() {
        return orderStatuId;
    }

    /**
     * 
     * @param orderStatuId
     *     The orderStatuId
     */
    public void setOrderStatuId(Integer orderStatuId) {
        this.orderStatuId = orderStatuId;
    }

    /**
     * 
     * @return
     *     The orderStatu
     */
    public OrderStatu getOrderStatu() {
        return orderStatu;
    }

    /**
     * 
     * @param orderStatu
     *     The orderStatu
     */
    public void setOrderStatu(OrderStatu orderStatu) {
        this.orderStatu = orderStatu;
    }

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
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
     *     The paymentId
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * 
     * @param paymentId
     *     The paymentId
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 
     * @return
     *     The payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * 
     * @param payment
     *     The payment
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
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
