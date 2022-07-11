package com.position.book.positionbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.position.book.positionbook.utility.PositionBookCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * POJO to hold the events came for processing
 */
public class Events {

    private static Logger LOGGER = LoggerFactory.getLogger(PositionBookCache.class);
    @JsonProperty("Events")
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        LOGGER.info("Setting Events");
        this.events = events;
    }

    @Override
    public String toString() {
        return "Events{" +
                "events=" + events +
                '}';
    }
}
