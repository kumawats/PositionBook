package com.position.book.positionbook.service.impl;

import com.position.book.positionbook.constants.ActionType;
import com.position.book.positionbook.exceptions.PositionException;
import com.position.book.positionbook.model.*;
import com.position.book.positionbook.service.EventProcessingService;
import com.position.book.positionbook.utility.EventProcessingPool;
import com.position.book.positionbook.utility.PositionBookCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * This class is responsible to process the events. It creates separate lists for buy, sell and cancel
 * It processes buy events first and then sell, cancel subsequently
 */
@Service
public class EventProcessingServiceImpl implements EventProcessingService {

    private static Logger LOGGER = LoggerFactory.getLogger(PositionBookCache.class);

    /**
     * Method to process the events - buy, sell and cancel
     * @param events - Events
     * @return - Positions processed by the method on all the accounts
     * @throws PositionException
     */
    @Override
    public Positions process(Events events) throws PositionException {
        Positions positions = null;
        try {
            LOGGER.info("Received events for processing - Checking for NULL = {}", events);
            if (events == null || events.getEvents() == null || events.getEvents().isEmpty()) {
                return null;
            }
            LOGGER.info("Received events for processing numberOfEvents={}", events.getEvents().size());

            List<Event> eventList = events.getEvents();
            Set<String> accountNames = new HashSet<>();
            eventList.stream().forEach(e -> accountNames.add(e.getAccount()));
            //Buys security first because sell will not be successful if done before buy on the same account and security
            List<Event> buyEventList = eventList.stream().filter(e -> e.getAction().equalsIgnoreCase(ActionType.BUY.toString())).collect(Collectors.toList());
            if (!buyEventList.isEmpty()) {
                CountDownLatch buyCountDownLatch = new CountDownLatch(buyEventList.size());
                buyEventList.stream().forEach(e -> EventProcessingPool.excuteTasks(new EventTask(e, buyCountDownLatch)));
                buyCountDownLatch.await();
            }
            //Sell security after bought all the securities to ensure we can remove on the same account and security if required
            List<Event> sellEventList = eventList.stream().filter(e -> e.getAction().equalsIgnoreCase(ActionType.SELL.toString())).collect(Collectors.toList());
            if (!sellEventList.isEmpty()) {
                CountDownLatch sellCountDownLatch = new CountDownLatch(sellEventList.size());
                sellEventList.stream().forEach(e -> EventProcessingPool.excuteTasks(new EventTask(e, sellCountDownLatch)));
                sellCountDownLatch.await();
            }

            List<Event> cancelEventList = eventList.stream().filter(e -> e.getAction().equalsIgnoreCase(ActionType.CANCEL.toString())).collect(Collectors.toList());
            if (!cancelEventList.isEmpty()) {
                CountDownLatch cancelCountDownLatch = new CountDownLatch(cancelEventList.size());
                cancelEventList.stream().forEach(e -> EventProcessingPool.excuteTasks(new EventTask(e, cancelCountDownLatch)));
                cancelCountDownLatch.await();
            }

            positions = new Positions();
            List<Position> listOfPositions = new ArrayList<>();

            //Get the positions on all the accounts and returns
            for (String accountName : accountNames) {

                List<Position> returnedPositions = PositionBookCache.getPositions(accountName);
                listOfPositions.addAll(returnedPositions);
            }
            positions.setPositions(listOfPositions);
            LOGGER.info("processed events for processing numberOfEvents={} accountNamesSize={}", events.getEvents().size(), accountNames.size());


        } catch (Exception e) {
            LOGGER.error("Processing error for events {}", events);
            throw new PositionException(e.getMessage());
        }
        return positions;
    }

    /**
     * This method query PositionBook to get the securities present on the account
     * @param accountName
     * @return
     */
    public Positions getPositions(String accountName) {
        Positions positions = new Positions();
        List<Position> listOfPositions = new ArrayList<>();
        positions.setPositions(listOfPositions);
        List<Position> returnedPositions = PositionBookCache.getPositions(accountName);
        listOfPositions.addAll(returnedPositions);

        LOGGER.info("Returning Positions for accountName={}", accountName);
        return positions;
    }
}
