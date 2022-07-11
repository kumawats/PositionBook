package com.position.book.positionbook.model;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

class PositionsTest {

    @InjectMocks
    private Positions subject;

    @BeforeEach
    void setUp() {
        subject = new Positions();
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }

    @Test
    void getPositions() {
        Position position1 = new Position();
        position1.setEventsList(new ArrayList<>());
        position1.setQuantity(10);
        position1.setSecurity("Sec");
        Position position2 = new Position();
        position2.setEventsList(new ArrayList<>());
        position2.setQuantity(10);
        position2.setSecurity("Sec");
        List<Position> positions= new ArrayList<>();
        positions.add(position1);
        positions.add(position2);
        subject.setPositions(positions);
        Assert.assertEquals(subject.getPositions().size(), 2);

    }

    @Test
    void setPositions() {
        Position position1 = new Position();
        position1.setEventsList(new ArrayList<>());
        position1.setQuantity(10);
        position1.setSecurity("Sec");
        Position position2 = new Position();
        position2.setEventsList(new ArrayList<>());
        position2.setQuantity(10);
        position2.setSecurity("Sec");
        List<Position> positions= new ArrayList<>();
        positions.add(position1);
        positions.add(position2);
        subject.setPositions(positions);
        Assert.assertEquals(subject.getPositions().size(), 2);
    }
}