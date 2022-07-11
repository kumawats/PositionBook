package com.position.book.positionbook;

import com.position.book.positionbook.exceptions.PositionException;
import com.position.book.positionbook.model.Events;
import com.position.book.positionbook.model.Positions;
import com.position.book.positionbook.service.EventProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.BrokenBarrierException;

/**
 * Controller to accept events request on rest end point to process the events
 */
@Controller
public class EventProcessController {

    private static Logger LOGGER = LoggerFactory.getLogger(EventProcessController.class);
    @Autowired
    private EventProcessingService eventProcessingService;

    /**
     * Endpoint to process the events
     * @param events
     * @return Latest Positions on the accounts came in the events
     * @throws BrokenBarrierException
     * @throws InterruptedException
     * @throws PositionException
     */
    @RequestMapping(value="/process", method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public ResponseEntity<Positions> process(@RequestBody Events events) throws BrokenBarrierException, InterruptedException {
        Positions positions = null;
        try {
            LOGGER.info("Started processing for events size={}", events.getEvents().size());
            positions = eventProcessingService.process(events);
            LOGGER.info("Processing Done for events size={}", events.getEvents().size());
        } catch (PositionException pe) {
            LOGGER.error("Error in processing events size={}", events.getEvents().size());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(positions);
    }

    /**
     * This method returns the latest positions on the account
     * @param accountName
     * @return - Latest positions
     */
    @RequestMapping(value="/getpositions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Positions> getPositions(@RequestParam String accountName){
        LOGGER.info("Getting Positions for account={} ",accountName);
        Positions positions = eventProcessingService.getPositions(accountName);
        LOGGER.info("Processing Done for account={} ",accountName);
        return  ResponseEntity.ok(positions);
    }
}
