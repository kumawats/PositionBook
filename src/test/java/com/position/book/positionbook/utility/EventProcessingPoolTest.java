package com.position.book.positionbook.utility;

import com.position.book.positionbook.model.Event;
import com.position.book.positionbook.model.EventTask;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CountDownLatch;

class EventProcessingPoolTest {

    @InjectMocks
    private EventProcessingPool subject = new EventProcessingPool();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void excuteTasks() {

        EventTask event = new EventTask(new Event(),new CountDownLatch(1));
        Assertions.assertNotNull(subject.excuteTasks(event));
    }
}