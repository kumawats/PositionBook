package com.position.book.positionbook.model;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

class SecurityTest {

    @InjectMocks
    private Security subject;

    @BeforeEach
    void setUp() {
        subject = new Security();
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }

    @Test
    void getName() {
        subject.setName("Sec1");
        Assert.assertEquals(subject.getName(), "Sec1");
    }

    @Test
    void setName() {
        subject.setName("Sec1");
        Assert.assertEquals(subject.getName(), "Sec1");
    }

    @Test
    void getQuantity() {
        subject.setQuantity(10);
        Assert.assertEquals(subject.getQuantity().toString(), String.valueOf(10));
    }

    @Test
    void setQuantity() {
        subject.setQuantity(10);
        Assert.assertEquals(subject.getQuantity().toString(), String.valueOf(10));
    }

    @Test
    void getSecEventsList() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setSecEventsList(events);
        Assert.assertEquals(subject.getSecEventsList().size(), 2);
    }

    @Test
    void setSecEventsList() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setSecEventsList(events);
        Assert.assertEquals(subject.getSecEventsList().size(), 2);
    }

    @Test
    void addEvent() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setSecEventsList(events);
        subject.addEvent(new Event(1,"buy","ACC1","Sec1",10));
        Assert.assertEquals(subject.getSecEventsList().size(), 3);

    }
}