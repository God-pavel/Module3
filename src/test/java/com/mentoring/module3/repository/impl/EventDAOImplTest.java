//package com.mentoring.module3.repository.impl;
//
//import com.mentoring.module3.model.Event;
//import com.mentoring.module3.storage.Storage;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class EventDAOImplTest {
//
//    @Mock
//    private Storage storage;
//    @Mock
//    private Event event;
//
//    @InjectMocks
//    private EventDAOImpl eventDAO;
//
//    @Test
//    void getAll_shouldReturnEvent_whenStorageReturnEvent() {
//        when(storage.getEvents()).thenReturn(Map.of("1", event));
//
//        assertTrue(eventDAO.getAll().contains(event));
//        assertEquals(1, eventDAO.getAll().size());
//    }
//
//    @Test
//    void getAll_shouldReturnEmptyCollection_whenStorageReturnEmptyMap() {
//        when(storage.getEvents()).thenReturn(Map.of());
//
//        assertEquals(0, eventDAO.getAll().size());
//    }
//
//    @Test
//    void getEventById_shouldReturnEvent_whenStorageHasEvent() {
//        when(storage.getEvents()).thenReturn(Map.of("1", event));
//
//        assertEquals(event, eventDAO.getEventById(1));
//    }
//
//    @Test
//    void getEventById_shouldReturnNull_whenStorageNotHasEvent() {
//        when(storage.getEvents()).thenReturn(Map.of("2", event));
//
//        assertNull(eventDAO.getEventById(1));
//    }
//}