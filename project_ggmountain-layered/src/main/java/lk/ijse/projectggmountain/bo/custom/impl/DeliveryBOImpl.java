package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.DeliveryBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.DeliveryDAO;
import lk.ijse.projectggmountain.dao.custom.EmployeeDAO;
import lk.ijse.projectggmountain.dao.custom.OrderDAO;
import lk.ijse.projectggmountain.dto.DeliveryDTO;
import lk.ijse.projectggmountain.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeliveryBOImpl implements DeliveryBO {
    DeliveryDAO deliveryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    OrderDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    public boolean saveDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.save( new Delivery(dto.getDelid(), dto.getDeldate(),dto.getDelsts(),dto.getLoc(),dto.getOrdid(),dto.getEmpid()));
    }

    public boolean cancelDelivery(String ordid) throws SQLException {
        return ordersDAO.cancelDelivery(ordid);
    }

    public ArrayList<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException {
        ArrayList<Delivery> all = deliveryDAO.getAll();
        ArrayList<DeliveryDTO> arrayList = new ArrayList<>();

        for (Delivery d : all) {
            arrayList.add(new DeliveryDTO(d.getDeliveryId(), d.getDate(),d.getDeliveryStatus(),d.getLocation(),d.getOrderId(),d.getEmpId()));
        }
        return arrayList;
    }

    public DeliveryDTO findByDeliveryId(String id) throws SQLException {
        Delivery delivery = deliveryDAO.findById(id);
        return new DeliveryDTO(delivery.getDeliveryId(), delivery.getDate(),delivery.getDeliveryStatus(),delivery.getLocation(),delivery.getOrderId(),delivery.getEmpId());
    }

    public boolean updateDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.update(new Delivery(dto.getDelid(), dto.getDeldate(),dto.getDelsts(),dto.getLoc(),dto.getOrdid(),dto.getEmpid()));
    }

    public boolean deleteDelivery(String id) throws SQLException, ClassNotFoundException {
        return deliveryDAO.delete(id);
    }

    @Override
    public DeliveryDTO findByOrderID(String id) throws SQLException {
        Delivery del = deliveryDAO.findById(id);
        return new DeliveryDTO(del.getDeliveryId(),del.getDate(),del.getDeliveryStatus(),del.getLocation(),del.getOrderId(),del.getEmpId());

    }

    @Override
    public DeliveryDTO findBydeliveryId(String delid) throws SQLException {
        Delivery del = deliveryDAO.findBydeliveryId(delid);
        return new DeliveryDTO(del.getDeliveryId(),del.getDate(),del.getDeliveryStatus(),del.getLocation(),del.getOrderId(),del.getEmpId());

    }

    @Override
    public ArrayList<DeliveryDTO> getByDueDate(String duedate) throws SQLException {
        ArrayList<Delivery> all = deliveryDAO.getByDueDate(duedate);
        ArrayList<DeliveryDTO> arrayList = new ArrayList<>();

        for (Delivery d : all) {
            arrayList.add(new DeliveryDTO(d.getDeliveryId(),d.getDate(),d.getDeliveryStatus(),d.getLocation(),d.getOrderId(),d.getEmpId()));
        }
        return arrayList;
    }

    @Override
    public List<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }

    @Override
    public ArrayList<DeliveryDTO> getByDeliveryStatus(String delists) throws SQLException {
        ArrayList<Delivery> all = deliveryDAO.getByDeliveryStatus(delists);
        ArrayList<DeliveryDTO> arrayList = new ArrayList<>();

        for (Delivery d : all) {
            arrayList.add(new DeliveryDTO(d.getDeliveryId(),d.getDate(),d.getDeliveryStatus(),d.getLocation(),d.getOrderId(),d.getEmpId()));
        }
        return arrayList;
    }
}
