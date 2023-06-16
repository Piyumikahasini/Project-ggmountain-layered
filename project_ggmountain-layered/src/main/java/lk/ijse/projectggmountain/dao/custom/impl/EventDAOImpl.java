package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.EventDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.entity.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM event WHERE eventId=?";
        return CrudUtil.execute(sql,id);
    }

    public boolean save(Event event) throws SQLException {
        String sql = "INSERT INTO event(eventId,eventName,date,time,eventType,empId) " +
                "VALUES(?,?,?,?,?,?)";

        return CrudUtil.execute(sql, event.getEventId(), event.getEventName(), event.getDate(), event.getTime(), event.getEventType(), event.getEmpId());

    }

    public boolean update(Event event) throws SQLException {
        String sql = "UPDATE event SET eventName=?,date=?,time=?,eventType=?,empId=? WHERE eventId=?";

        return CrudUtil.execute(sql, event.getEventName(), event.getDate(), event.getTime(), event.getEventType(), event.getEmpId(), event.getEventId());

    }

    public ArrayList<Event> getAll() throws SQLException {
        String sql = "SELECT * FROM event";

        ArrayList<Event> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new Event(
                    resultSet.getString(6),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)


            ));
        }
        return arrayList;
    }

    public Event findById(String id) throws SQLException {
        String sql = "SELECT * FROM event WHERE eventId=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Event(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)


            ));
        }
        return null;
    }

    public List<String> loadIds() throws SQLException {
        String sql = "SELECT empId FROM employee";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
