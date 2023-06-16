package lk.ijse.projectggmountain.dao.custom;

import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventDAO extends CrudDAO<Event,String> {
    public Event findById(String id) throws SQLException;

    public List<String> loadIds() throws SQLException;
}
