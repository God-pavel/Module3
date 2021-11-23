package com.mentoring.module2.config;

import com.mentoring.module2.dto.TicketDto;
import com.mentoring.module2.model.Ticket;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class TicketConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(TicketDto.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        // Don't do anything
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        reader.moveDown();
        TicketDto ticket = new TicketDto();
        ticket.setUserId(Long.parseLong(reader.getValue()));

        reader.moveUp();
        reader.moveDown();
        ticket.setEventId(Long.parseLong(reader.getValue()));

        reader.moveUp();
        reader.moveDown();
        ticket.setCategory(Ticket.Category.valueOf(reader.getValue()));

        reader.moveUp();
        reader.moveDown();
        ticket.setPlace(Integer.parseInt(reader.getValue()));

        return ticket;
    }
}