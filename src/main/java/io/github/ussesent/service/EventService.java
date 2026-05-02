package io.github.ussesent.service;

import io.github.ussesent.model.Event;
import io.github.ussesent.repositories.EventRepository;

import javax.sql.DataSource;
import java.util.List;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(DataSource dataSource) {
        this.eventRepository = new EventRepository(dataSource);
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public List<Event> getEventsByGameId(int gameId) {
        return eventRepository.getEventsByGameId(gameId);
    }

    public Event getEventById(int id) {
        return eventRepository.getEventById(id);
    }

    public void updateEvent(Event event) {
        eventRepository.updateEvent(event);
    }

    public void addEvent(Event event) {
        eventRepository.addEvent(event);
    }

    public boolean deleteEventById(int id) {
        return eventRepository.deleteEventById(id);
    }

    public List<Event> getLastThreeEvents() {
         return eventRepository.getLastThreeEvents();
    }
}
