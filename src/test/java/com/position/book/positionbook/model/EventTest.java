package com.position.book.positionbook.model;

import com.position.book.positionbook.constants.ActionType;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class EventTest {

    @InjectMocks
    private Event subject;

    @BeforeEach
    void setUp() {
        subject = new Event();
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }

    @Test
    void getId() {
        subject.setId(1);
        Assert.assertEquals(subject.getId().toString(), String.valueOf(1));
    }

    @Test
    void setId() {
        subject.setId(1);
        Assert.assertEquals(subject.getId().toString(), String.valueOf(1));
    }

    @Test
    void getAction() {
        subject.setAction("buy");
        Assert.assertEquals(subject.getAction(), ActionType.BUY.toString());
    }

    @Test
    void setAction() {
        subject.setAction("buy");
        Assert.assertEquals(subject.getAction(), ActionType.BUY.toString());
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
    void testToString() {
    }
}