package com.position.book.positionbook.utility;

import com.position.book.positionbook.model.EventTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class is event processing pool. Creates pool of 10 fixed threads to process the events
 */
@Component
public class EventProcessingPool {
    private static Logger LOGGER = LoggerFactory.getLogger(PositionBookCache.class);
    private static ExecutorService executorService =  Executors.newFixedThreadPool(10);

    /**
     * This method submit tasks to thread pool
     * @param eventTask
     * @return
     */
    public static Future<EventTask> excuteTasks(Callable<EventTask> eventTask){
        LOGGER.info("Submitting task for processing");
        return executorService.submit(eventTask);
    }
}
