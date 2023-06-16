package lk.ijse.projectggmountain.dao.custom;

import lk.ijse.projectggmountain.dao.CrudDAO;
import lk.ijse.projectggmountain.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<Delivery,String> {
    public Delivery findById(String id) throws SQLException;

    public Delivery findBydeliveryId(String delid) throws SQLException;

    public ArrayList<Delivery> getByDueDate(String duedate) throws SQLException;

    public ArrayList<Delivery> getByDeliveryStatus(String delists) throws SQLException;

}
