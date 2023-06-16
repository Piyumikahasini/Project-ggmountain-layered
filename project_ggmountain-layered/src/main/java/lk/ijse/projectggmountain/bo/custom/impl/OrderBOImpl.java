package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.OrderBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.*;
import lk.ijse.projectggmountain.db.DBConnection;
import lk.ijse.projectggmountain.dto.InventoryDTO;
import lk.ijse.projectggmountain.dto.NewDeliveryDTO;
import lk.ijse.projectggmountain.dto.OrderDTO;
import lk.ijse.projectggmountain.entity.Delivery;
import lk.ijse.projectggmountain.entity.Item;
import lk.ijse.projectggmountain.entity.Order;
import lk.ijse.projectggmountain.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    DeliveryDAO deliveryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public String getNextOrderId() throws SQLException {
        return ordersDAO.getNextOrderId();
    }

    public List<String> loadCustomerIds() throws SQLException {
        return customerDAO.loadCustomerIds();
    }

    public List<String> loadItemCodes() throws SQLException {
        return itemDAO.loadItemId();
    }

    public String getCustomerName(String custId) throws SQLException {
        return customerDAO.getCustName(custId);
    }

    public InventoryDTO findByItemCode(String id) throws SQLException {
        Item item = itemDAO.findById(id);
        return new InventoryDTO(item.getItemId(), item.getItemName(), item.getCategory(), item.getUnitPrice(), String.valueOf(item.getQtyOnHand()));
    }

    static NewDeliveryDTO gotnewdelivery;
    public void sendObject(NewDeliveryDTO newDelivery) {
        gotnewdelivery = newDelivery;
    }

    public boolean placeOrder(String orderId, String custId, Boolean delivery, String ordpay, List<OrderDTO> orderList) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = ordersDAO.save(new Order(orderId,LocalDate.now(), LocalTime.now(), delivery, Double.valueOf(ordpay), custId));
            if (isSaved) {
                System.out.println("orderSave");
                boolean isUpdated = updateQty(orderList);
                if (isUpdated) {
                    System.out.println("Updated");
                    boolean isOrdered = saveOrderDetails(orderId, orderList);
                    if (isOrdered) {
                        System.out.println("ordered");
                        if (delivery) {
                            System.out.println("True");
                            Delivery entity = new Delivery();
                            entity.setOrderId(gotnewdelivery.getOrderId());
                            entity.setDeliveryId(gotnewdelivery.getDelId());
                            entity.setLocation(gotnewdelivery.getLocation());
                            entity.setEmpId(gotnewdelivery.getEmpId());
                            entity.setDate(gotnewdelivery.getDueDate());

                            boolean isDelivered = deliveryDAO.save(entity);
                            if (isDelivered) {
                                System.out.println("delivered");
                                con.commit();
                                return true;
                            }
                        } else {
                            con.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    public boolean updateQty(List<OrderDTO> placeOrderList) throws SQLException {
        System.out.println("updateQtyList");
        for(OrderDTO placeorder : placeOrderList) {
            OrderDetail orderDetail = new OrderDetail(placeorder.getItemId(),placeorder.getOrderQty());
            if(!itemDAO.updateQty(orderDetail)) {
                return false;
            }
        }
        return true;
    }

    public boolean saveOrderDetails(String orderid, List<OrderDTO> orderList) throws SQLException, ClassNotFoundException {
        for(OrderDTO placeOrder : orderList) {
            OrderDetail orderDetail = new OrderDetail(orderid,placeOrder.getItemId(),placeOrder.getOrderQty());
            if(!orderDetailDAO.save(orderDetail)) {
                return false;
            }
        }
        return true;
    }

    public List<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }
}
