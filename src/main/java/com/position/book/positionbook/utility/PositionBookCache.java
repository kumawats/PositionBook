package com.position.book.positionbook.utility;

import com.position.book.positionbook.model.Event;
import com.position.book.positionbook.model.Position;
import com.position.book.positionbook.model.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is code of Position book and holding all the positions' data in a ConcurrentHashMap
 * It is responsible for maintaining the real time position data using add, remove methods
 * <p>
 * This class also returns the positions in a account
 */
@Component
public class PositionBookCache implements InitializingBean {

    private static Logger LOGGER = LoggerFactory.getLogger(PositionBookCache.class);
    private static Map<String, List<Security>> positionBook;

    @Override
    public void afterPropertiesSet() throws Exception {
        positionBook = new ConcurrentHashMap<>();
    }

    /**
     * This method add account and security to a account, if not exists OR add value to existing security
     *
     * @param account - Account name
     * @param event   - Event - Buy event
     * @return true if event is processed on the account
     */
    public static boolean add(String account, Event event) {
        boolean found = false;
        LOGGER.info("Adding security accountName={} securityName={} quantity={}", event.getAccount(), event.getSecurity(), event.getQuantity());
        if (positionBook.get(account) != null) {
            LOGGER.info("Found account {}", event.getAccount());
            List<Security> secList = positionBook.get(account);
            List<Security> updatedSecList = new ArrayList<>();
            for (Security sec : secList) {
                if (sec.getName().equalsIgnoreCase(event.getSecurity())) {
                    sec.setQuantity(sec.getQuantity() + event.getQuantity());
                    sec.addEvent(event);
                    found = true;
                    LOGGER.info("Security added to accountName={} securityName={}", event.getAccount(), event.getSecurity());

                }
                updatedSecList.add(sec);
            }
            if (found) {
                positionBook.put(account, updatedSecList);
            } else {
                LOGGER.info("Security NOT Found {}", event.getAccount());
                List<Security> currentSecList = positionBook.get(account);
                List<Event> eventList = new ArrayList<>();
                eventList.add(event);
                Security newSecurity = new Security(event.getSecurity(), event.getQuantity(), eventList);
                currentSecList.add(newSecurity);
                positionBook.put(account, currentSecList);
                LOGGER.info("Security added to accountName={} securityName={} quantity={}", event.getAccount(), event.getSecurity(), event.getQuantity());

            }

        } else {
            LOGGER.info("Account NOT Found {}", event.getAccount());
            positionBook.put(account, new ArrayList<>());
            List<Event> eventList = new ArrayList<Event>();
            eventList.add(event);
            Security newSecurity = new Security(event.getSecurity(), event.getQuantity(), eventList);
            positionBook.get(account).add(newSecurity);
            LOGGER.info("Security added to accountName={} securityName={} quantity={}", event.getAccount(), event.getSecurity(), event.getQuantity());

        }
        return true;
    }

    /**
     * This method remove security from account, if not exists OR return false if security not found
     *
     * @param account - Account name
     * @param event   - Sell event
     * @return
     */
    public static boolean remove(String account, Event event) {
        boolean found = false;
        LOGGER.info("Removing security accountName={} securityName={} quantity={}", event.getAccount(), event.getSecurity(), event.getQuantity());

        if (positionBook.get(account) != null) {
            LOGGER.info("Found account {}", event.getAccount());
            List<Security> secList = positionBook.get(account);
            for (Security sec : secList) {
                if (sec.getName().equalsIgnoreCase(event.getSecurity())) {
                    sec.setQuantity(sec.getQuantity() - event.getQuantity());
                    sec.addEvent(event);
                    found = true;
                    LOGGER.info("Security removed to accountName={} securityName={}", event.getAccount(), event.getSecurity());

                    break;
                }
            }

        }
        if (!found) {
            LOGGER.info("Account NOT Found {} and Nothing to remove", event.getAccount());
            return false;
        }
        return true;
    }

    /**
     * This method returns position of a account
     *
     * @param account - Account name
     * @return - Positions on a account
     */
    public static List<Position> getPositions(String account) {
        LOGGER.info("Getting positions for accountName={}", account);

        List<Position> listofPositions = new ArrayList<>();

        if (positionBook.get(account) != null) {
            synchronized (positionBook.get(account)) {
                List<Security> secList = positionBook.get(account);
                for (Security sec : secList) {
                    Position position = new Position();
                    position.setAccount(account);
                    position.setSecurity(sec.getName());
                    position.setQuantity(sec.getQuantity());
                    position.setEventsList(sec.getSecEventsList());
                    listofPositions.add(position);
                }
            }


        }
        LOGGER.info("Positions for accountName={}", listofPositions);
        return listofPositions;
    }

}
