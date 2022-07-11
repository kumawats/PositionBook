package com.position.book.positionbook.model;

import java.util.List;

/**
 * Security POJO to create the security and holding the events
 */
public class Security {
    private String name;
    private Integer quantity;
    private List<Event> secEventsList;

    public Security(){};

    public Security(String name, Integer quantity, List<Event> secEventsList) {
        this.name = name;
        this.quantity = quantity;
        this.secEventsList = secEventsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Event> getSecEventsList() {
        return secEventsList;
    }

    public void setSecEventsList(List<Event> secEventsList) {
        this.secEventsList = secEventsList;
    }
    public void addEvent(Event event) {
        this.secEventsList.add(event) ;
    }


}
