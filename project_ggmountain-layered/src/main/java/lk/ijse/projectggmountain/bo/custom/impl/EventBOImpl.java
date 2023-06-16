package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.EventBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.EventDAO;
import lk.ijse.projectggmountain.dto.EventDTO;
import lk.ijse.projectggmountain.entity.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventBOImpl implements EventBO {
    EventDAO eventDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EVENT);

    public boolean saveEvent(EventDTO dto) throws SQLException, ClassNotFoundException {
        return eventDAO.save( new Event(dto.getId(), dto.getName(),dto.getDate(),dto.getTime(),dto.getType(),dto.getEmpId()));
    }

    public ArrayList<EventDTO> getAllEvent() throws SQLException, ClassNotFoundException {
        ArrayList<Event> all = eventDAO.getAll();
        ArrayList<EventDTO> arrayList = new ArrayList<>();

        for (Event e : all) {
            arrayList.add(new EventDTO(e.getEventId(),e.getEventName(),e.getDate(),e.getTime(),e.getEventType(),e.getEmpId()));
        }
        return arrayList;
    }

    public EventDTO findByEventId(String id) throws SQLException {
        Event event = eventDAO.findById(id);
        return new EventDTO(event.getEventId(),event.getEventName(),event.getDate(),event.getTime(),event.getEventType(),event.getEmpId());
    }

    public boolean updateEvent(EventDTO dto) throws SQLException, ClassNotFoundException {
        return eventDAO.update(new Event(dto.getId(), dto.getName(),dto.getDate(),dto.getTime(),dto.getType(),dto.getEmpId()));
    }

    public boolean deleteEvent(String id) throws SQLException, ClassNotFoundException {
        return eventDAO.delete(id);
    }

    public List<String> loadIds() throws SQLException {
        return eventDAO.loadIds();
    }
}
