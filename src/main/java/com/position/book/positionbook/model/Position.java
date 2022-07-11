package com.position.book.positionbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * POJO to represent a Position on an account
 */
public class Position {

    @JsonProperty("Account")
    private String account;
    @JsonProperty("Security")
    private String security;
    @JsonProperty("Quantity")
    private Integer quantity;
    @JsonProperty("Events")
    private List<Event> eventsList;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    @Override
    public String toString() {
        return "Position{" +
                "account='" + account + '\'' +
                ", security='" + security + '\'' +
                ", quantity=" + quantity +
                ", eventsList=" + eventsList +
                '}';
    }
}
