package lk.ijse.projectggmountain.dao.custom.impl;

import lk.ijse.projectggmountain.dao.custom.DeliveryDAO;
import lk.ijse.projectggmountain.dao.util.CrudUtil;
import lk.ijse.projectggmountain.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    public ArrayList<Delivery> getAll() throws SQLException {
        String sql = "SELECT * FROM delivery";

        ArrayList<Delivery> arrayList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new Delivery(
                    resultSet.getString(5),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(6)
            ));
        }
        return arrayList;
    }

    @Override
    public boolean save(Delivery delivery) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO delivery(deliveryId,Date,location,orderId,empId)" +
                "VALUES (?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                delivery.getDeliveryId(),
                delivery.getDate(),
                delivery.getLocation(),
                delivery.getOrderId(),
                delivery.getEmpId()
        );
    }

    public boolean update(Delivery delivery) throws SQLException {
        String sql = "UPDATE delivery SET deliveryStatus=?,date=?,location=?,orderId=?,empId=? WHERE deliveryId=?";

        return CrudUtil.execute(
                sql,
                delivery.getDeliveryStatus(),
                delivery.getDate(),
                delivery.getLocation(),
                delivery.getOrderId(),
                delivery.getEmpId(),
                delivery.getDeliveryId()
        );
    }

    public boolean delete(String delid) throws SQLException {
        String sql = "DELETE FROM delivery WHERE deliveryId=?";

        return CrudUtil.execute(sql,delid);
    }

    public Delivery findById(String id) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE orderId=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Delivery(
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

    public ArrayList<Delivery> getByDeliveryStatus(String delists) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE deliveryStatus=?";

        ArrayList<Delivery> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,delists);
        while (resultSet.next()) {
            all.add(new Delivery(
                    resultSet.getString(5),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(6)
            ));
        }
        return all;
    }

    public ArrayList<Delivery> getByDueDate(String date) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE date=?";

        ArrayList<Delivery> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            all.add(new Delivery(
                    resultSet.getString(5),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(6)
            ));
        }
        return all;
    }

    public Delivery findBydeliveryId(String delid) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE deliveryId=?";

        ResultSet resultSet = CrudUtil.execute(sql,delid);
        if(resultSet.next()){
            return (new Delivery(
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
}
