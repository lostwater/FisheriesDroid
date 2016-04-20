
package com.xyxd.fisher.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventStatu {

    @SerializedName("isOrderable")
    @Expose
    private Boolean isOrderable;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The isOrderable
     */
    public Boolean getIsOrderable() {
        return isOrderable;
    }

    /**
     * 
     * @param isOrderable
     *     The isOrderable
     */
    public void setIsOrderable(Boolean isOrderable) {
        this.isOrderable = isOrderable;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
