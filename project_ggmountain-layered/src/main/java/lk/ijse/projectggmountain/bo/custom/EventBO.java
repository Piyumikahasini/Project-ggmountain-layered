package lk.ijse.projectggmountain.bo.custom;

import lk.ijse.projectggmountain.bo.SuperBO;
import lk.ijse.projectggmountain.dto.EventDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EventBO extends SuperBO {
    public boolean saveEvent(EventDTO dto) throws SQLException, ClassNotFoundException;

    public ArrayList<EventDTO> getAllEvent() throws SQLException, ClassNotFoundException;

    public EventDTO findByEventId(String id) throws SQLException;

    public boolean updateEvent(EventDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteEvent(String id) throws SQLException, ClassNotFoundException;

    public List<String> loadIds() throws SQLException;

}
