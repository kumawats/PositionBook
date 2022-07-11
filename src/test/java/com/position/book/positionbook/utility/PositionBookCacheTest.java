package com.position.book.positionbook.utility;

import com.position.book.positionbook.model.Event;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class PositionBookCacheTest {

    @InjectMocks
    private PositionBookCache subject = new PositionBookCache();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void afterPropertiesSet() throws Exception {
        subject.afterPropertiesSet();
        Assertions.assertNotNull(subject.getPositions("ACCC1"));
    }

    @Test
    void addAccountNotFound() throws Exception {

        String account = "ACCC1";
        Event event = new Event();
        subject.afterPropertiesSet();
        subject.add(account, event);
        Assertions.assertNotNull(subject.getPositions("ACCC1"));
    }

    @Test
    void addAccountFound() throws Exception {

        String account = "ACCC1";
        Event event = new Event();
        event.setAccount("ACCC1");
        event.setAction("BUY");
        event.setId(1);
        event.setSecurity("SEC1");
        event.setQuantity(100);
        subject.afterPropertiesSet();
        subject.add(account, event);
        subject.add(account, event);
        Assertions.assertEquals(2, subject.getPositions("ACCC1").get(0).getEventsList().size());
    }

    @Test
    void addAccountVerifyQuantity() throws Exception {

        String account = "ACCC1";
        Event event = new Event();
        event.setAccount("ACCC1");
        event.setAction("BUY");
        event.setId(1);
        event.setSecurity("SEC1");
        event.setQuantity(100);
        subject.afterPropertiesSet();
        subject.add(account, event);
        subject.add(account, event);
        Assertions.assertEquals(200, subject.getPositions("ACCC1").get(0).getQuantity());
    }

    @Test
    void removeAccountNotFound() throws Exception {

        String account = "ACCC1";
        Event event = new Event();
        subject.afterPropertiesSet();
        Assertions.assertFalse(subject.remove(account, event));
    }

    @Test
    void removeAccountVerifyQuantity() throws Exception {

        String account = "ACCC1";
        Event event = new Event();
        event.setAccount("ACCC1");
        event.setAction("BUY");
        event.setId(1);
        event.setSecurity("SEC1");
        event.setQuantity(100);
        subject.afterPropertiesSet();
        subject.add(account, event);
        event.setQuantity(50);
        subject.remove("ACCC1", event);
        Assertions.assertEquals(50, subject.getPositions("ACCC1").get(0).getQuantity());
    }

    @Test
    void getPositionsSize() throws Exception {

        String account = "ACCC1";
        Event event = new Event();
        event.setId(1);
        event.setAccount("ACCC1");
        event.setAction("BUY");
        event.setSecurity("SEC1");
        event.setQuantity(100);
        subject.afterPropertiesSet();
        subject.add(account, event);
        event.setId(2);
        event.setAccount("ACCC1");
        event.setAction("BUY");
        event.setSecurity("SEC1");
        event.setQuantity(100);
        subject.add(account, event);
        Assertions.assertEquals(2, subject.getPositions("ACCC1").get(0).getEventsList().size());
    }
}