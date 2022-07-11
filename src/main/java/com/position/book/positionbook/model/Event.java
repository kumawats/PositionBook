package com.position.book.positionbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.position.book.positionbook.constants.ActionType;

/**
 * This class is used to represent an Event - Buy, Sell, Cancel
 */
public class Event {
    @JsonProperty("ID")
    private Integer id;
    @JsonProperty("Action")
    private ActionType action;
    @JsonProperty("Account")
    private String account;
    @JsonProperty("Security")
    private String security;
    @JsonProperty("Quantity")
    private Integer quantity;

    public Event() {
    }



    public Event(Integer id, String action, String account, String security, Integer quantity) {
        this.id = id;
        if (action.equalsIgnoreCase("buy")) {
            this.action = ActionType.BUY;
        } else if (action.equalsIgnoreCase("sell")) {
            this.action = ActionType.SELL;
        } else if (action.equalsIgnoreCase("cancel")) {
            this.action = ActionType.CANCEL;
        }
        this.account = account;
        this.security = security;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action.toString();
    }

    public void setAction(String action) {
        if (action.equalsIgnoreCase("buy")) {
            this.action = ActionType.BUY;
        } else if (action.equalsIgnoreCase("sell")) {
            this.action = ActionType.SELL;
        } else if (action.equalsIgnoreCase("cancel")) {
            this.action = ActionType.CANCEL;
        }

    }

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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", account='" + account + '\'' +
                ", security='" + security + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
