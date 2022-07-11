package com.position.book.positionbook.model;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

class EventsTest {

    @InjectMocks
    private Events subject;

    @BeforeEach
    void setUp() {
        subject = new Events();
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }

    @Test
    void getEvents() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setEvents(events);
        Assert.assertEquals(subject.getEvents().size(), 2);
    }

    @Test
    void setEvents() {
        Event e1 = new Event();
        e1.setId(1);
        Event e2 = new Event();
        e2.setId(2);
        List<Event> events = new ArrayList<>();
        events.add(e1);
        events.add(e2);
        subject.setEvents(events);
        Assert.assertEquals(subject.getEvents().size(), 2);
    }
}