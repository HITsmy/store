package com.hit.store.entity;

import java.io.Serializable;

public class OrderStr implements Serializable {
    String initiatorName;
    Integer initiatorID;
    String receiverName;
    Integer receiverID;
    Long amount;
    Integer orderNumber;

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public Integer getInitiatorID() {
        return initiatorID;
    }

    public void setInitiatorID(Integer initiatorID) {
        this.initiatorID = initiatorID;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "OrderStr{" +
                "initiatorName='" + initiatorName + '\'' +
                ", initiatorID=" + initiatorID +
                ", receiverName='" + receiverName + '\'' +
                ", receiverID=" + receiverID +
                ", amount=" + amount +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
