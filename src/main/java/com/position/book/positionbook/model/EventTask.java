package com.position.book.positionbook.model;

import com.position.book.positionbook.constants.ActionType;
import com.position.book.positionbook.utility.PositionBookCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * This class is a Callable task to submit events on new thread for buy, sell or cancel security
 */
public class EventTask implements Callable<EventTask>,Comparable {

    private static Logger LOGGER = LoggerFactory.getLogger(EventTask.class);
    private Event event;

    private CountDownLatch countDownLatch;

    /**
     * Constructor with countdown latch and event
     * @param event
     * @param countDownLatch
     */
    public EventTask(Event event, CountDownLatch countDownLatch) {
        this.event = event;
        this.countDownLatch = countDownLatch;
    }

    /**
     * This method runs in new thread to buy, sell or cancel the security
     * @return
     */
    @Override
    public EventTask call() {
        LOGGER.info("Task Execution started ={}",event.getAccount());
        if(event != null){
            if(event.getAction().equalsIgnoreCase("buy")){
                LOGGER.info("Buy security ={}",event.getSecurity());
                PositionBookCache.add(event.getAccount(),event);


            }else if(event.getAction().equalsIgnoreCase("sell")){
                LOGGER.info("Sell security ={}",event.getSecurity());
                PositionBookCache.remove(event.getAccount(),event);

            }else if(event.getAction().equalsIgnoreCase("cancel")){
                LOGGER.info("Cancel security ={}",event.getSecurity());
                PositionBookCache.remove(event.getAccount(),event);
            }
           // return PositionBookCache.getPositions(event.getAccount());
        }
        LOGGER.info("Calling count down on thread={}",Thread.currentThread().getName());
        countDownLatch.countDown();
        return null;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public int compareTo(Object o) {
        if(this.event.getAction().equalsIgnoreCase(ActionType.BUY.toString()))
            return 1;
        else if((this.event.getAction().equalsIgnoreCase(ActionType.SELL.toString())))
            return -1;
        return 0;
    }
}
