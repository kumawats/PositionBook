package com.position.book.positionbook.model;

import com.position.book.positionbook.utility.PositionBookCache;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.concurrent.CountDownLatch;

class EventTaskTest {

    @InjectMocks
    private EventTask subject;

    @InjectMocks
    private PositionBookCache cache = new PositionBookCache();

    @BeforeEach
    void setUp() throws Exception {
        cache.afterPropertiesSet();
        Event event1 = new Event();
        event1.setId(1);
        event1.setAccount("ACCC1");
        event1.setAction("BUY");
        event1.setSecurity("SEC1");
        event1.setQuantity(100);
        subject = new EventTask(event1,new CountDownLatch(1));
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }


    @Test
    void call() {

        subject.call();

        Assertions.assertEquals(100, PositionBookCache.getPositions("ACCC1").get(0).getQuantity());
    }

    @Test
    void getEvent() {
        Event event1 = new Event();
        event1.setId(1);
        event1.setAccount("ACCC1");
        event1.setAction("BUY");
        event1.setSecurity("SEC1");
        event1.setQuantity(100);
        subject.setEvent(event1);
        Assert.assertNotNull(subject.getEvent());
    }

    @Test
    void setEvent() {
        Event event1 = new Event();
        event1.setId(1);
        event1.setAccount("ACCC1");
        event1.setAction("BUY");
        event1.setSecurity("SEC1");
        event1.setQuantity(100);
        subject.setEvent(event1);
        Assert.assertNotNull(subject.getEvent());
    }
}