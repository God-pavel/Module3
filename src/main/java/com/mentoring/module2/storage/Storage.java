package com.mentoring.module2.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoring.module2.model.Event;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.User;
import com.mentoring.module2.model.impl.EventImpl;
import com.mentoring.module2.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Storage {

    @Value("${storage.users}")
    private String usersFilePath;
    @Value("${storage.events}")
    private String eventsFilePath;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, Event> events;
    private Map<String, Ticket> tickets;
    private Map<String, User> users;

    public Map<String, Event> getEvents() {
        return events;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    @PostConstruct
    private void initializeStorage() throws IOException {

        final List<UserImpl> userList = objectMapper.readValue(getJsonString(usersFilePath), new TypeReference<>() {
        });

        final List<EventImpl> eventList = objectMapper.readValue(getJsonString(eventsFilePath), new TypeReference<>() {
        });

        users = userList.stream().collect(Collectors.toMap(user -> String.valueOf(user.getId()), Function.identity()));
        events = eventList.stream().collect(Collectors.toMap(event -> String.valueOf(event.getId()), Function.identity()));
        tickets = new HashMap<>();
    }

    private String getJsonString(final String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void setUsersFilePath(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }

    public void setEventsFilePath(String eventsFilePath) {
        this.eventsFilePath = eventsFilePath;
    }
}
