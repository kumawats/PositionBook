package com.position.book.positionbook;

import com.position.book.positionbook.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring boot application
 */
@SpringBootApplication
public class PositionBookApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(PositionBookApplication.class);

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/positionbook");
		SpringApplication.run(PositionBookApplication.class, args);
		LOGGER.info("Started Application");

		List<Event> events = new ArrayList<>() ;
		Event e1= new Event(1, "buy","account1", "security1", 1);
		Event e2= new Event(2, "buy","account2", "security2", 2);
		Event e3= new Event(3, "buy","account3", "security3", 3);
		events.add(e1);
		events.add(e2);
		events.add(e3);
		LOGGER.info(String.valueOf(events));

	}

}
