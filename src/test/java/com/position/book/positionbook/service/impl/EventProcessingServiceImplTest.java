package com.position.book.positionbook.service.impl;

import com.position.book.positionbook.exceptions.PositionException;
import com.position.book.positionbook.model.Event;
import com.position.book.positionbook.model.Events;
import com.position.book.positionbook.utility.PositionBookCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

class EventProcessingServiceImplTest {


    @InjectMocks
    private EventProcessingServiceImpl subject = new EventProcessingServiceImpl();

    @InjectMocks
    private PositionBookCache cache = new PositionBookCache();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void processEventsNull() throws  PositionException {
        Assertions.assertNull(subject.process(new Events()));
    }

    @Test
    void processBuy() throws  PositionException {

        Event event1 = new Event();
        event1.setId(1);
        event1.setAccount("ACCC1");
        event1.setAction("BUY");
        event1.setSecurity("SEC1");
        event1.setQuantity(100);
        Event event2 = new Event();
        event2.setId(2);
        event2.setAccount("ACCC1");
        event2.setAction("BUY");
        event2.setSecurity("SEC1");
        event2.setQuantity(101);
        Events events = new Events();
        events.setEvents(new ArrayList<>());
        events.getEvents().add(event1);
        events.getEvents().add(event2);
        subject.process(events);
        Assertions.assertEquals(201, subject.getPositions("ACCC1").getPositions().get(0).getQuantity());
    }



    @Test
    void getPositions() throws PositionException {


        Event event1 = new Event();
        event1.setId(1);
        event1.setAccount("ACCC1");
        event1.setAction("BUY");
        event1.setSecurity("SEC1");
        event1.setQuantity(100);
        Event event2 = new Event();
        event2.setId(2);
        event2.setAccount("ACCC1");
        event2.setAction("BUY");
        event2.setSecurity("SEC1");
        event2.setQuantity(101);
        Events events = new Events();
        events.setEvents(new ArrayList<>());
        events.getEvents().add(event1);
        events.getEvents().add(event2);
        subject.process(events);
        subject.getPositions("ACCC1");
        Assertions.assertEquals(402, subject.getPositions("ACCC1").getPositions().get(0).getQuantity());
    }
}