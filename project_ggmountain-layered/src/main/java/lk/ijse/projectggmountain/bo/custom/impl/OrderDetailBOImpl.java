package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.OrderDetailBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.OrderDAO;
import lk.ijse.projectggmountain.dao.custom.QueryDAO;
import lk.ijse.projectggmountain.dto.CustomDTO;
import lk.ijse.projectggmountain.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    OrderDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    public ArrayList<CustomDTO> getAll() throws SQLException {
        ArrayList<Custom> all = queryDAO.getAll();
        ArrayList<CustomDTO> arrayList = new ArrayList<>();

        for (Custom c : all) {
            CustomDTO dto = new CustomDTO();
            dto.setOrderId(c.getOrderId());
            dto.setItemId(c.getItemId());
            dto.setItemName(c.getItemName());
            dto.setOrderQty(c.getOrderQty());
            arrayList.add(dto);
        }
        return arrayList;
    }

    public CustomDTO fillFields(String orderid) throws SQLException {
        Custom custom = queryDAO.fillFields(orderid);
        CustomDTO dto = new CustomDTO();
        dto.setCustId(custom.getCustId());
        dto.setCustName(custom.getCustName());
        dto.setDeliveryStatus(custom.getDeliveryStatus());
        dto.setDate(custom.getDate());
        dto.setTime(custom.getTime());
        dto.setPayment(custom.getPayment());

        return dto;
    }

    public int totalOrdersToday() throws SQLException {
        return ordersDAO.totalOrdersToday();
    }

    public int totalOrdersMonth() throws SQLException {
        return ordersDAO.totalOrdersMonth();
    }

    public ArrayList<CustomDTO> searchbyorderdate(String date) throws SQLException {
        ArrayList<Custom> all = queryDAO.searchbyorderdate(date);
        ArrayList<CustomDTO> arrayList = new ArrayList<>();

        for (Custom c : all) {
            CustomDTO dto =new CustomDTO();
            dto.setOrderId(c.getOrderId());
            dto.setItemId(c.getItemId());
            dto.setItemName(c.getItemName());
            dto.setOrderQty(c.getOrderQty());
            arrayList.add(dto);
        }
        return arrayList;
    }
}
