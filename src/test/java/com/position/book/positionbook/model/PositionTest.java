package com.position.book.positionbook.model;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

class PositionTest {
    @InjectMocks
    private Position subject;

    @BeforeEach
    void setUp() {
        subject = new Position();
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }

    @Test
    void getAccount() {
        subject.setAccount("Account1");
        Assert.assertEquals(subject.getAccount(), "Account1");
    }

    @Test
    void setAccount() {
        subject.setAccount("Account1");
        Assert.assertEquals(subject.getAccount(), "Account1");
    }

    @Test
    void getSecurity() {
        subject.setSecurity("Sec1");
        Assert.assertEquals(subject.getSecurity(), "Sec1");
    }

    @Test
    void setSecurity() {
        subject.setSecurity("Sec1");
        Assert.assertEquals(subject.getSecurity(), "Sec1");
    }

    @Test
    void getQuantity() {
        subject.setQuantity(1);
        Assert.assertEquals(subject.getQuantity().toString(), String.valueOf(1));
    }

    @Test
    void setQuantity() {
        subject.setQuantity(1);
        Assert.assertEquals(subject.getQuantity().toString(), String.valueOf(1));
    }

    @Test
    void getEventsList() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setEventsList(events);
        Assert.assertEquals(subject.getEventsList().size(), 2);
    }

    @Test
    void setEventsList() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setEventsList(events);
        Assert.assertEquals(subject.getEventsList().size(), 2);
    }
}