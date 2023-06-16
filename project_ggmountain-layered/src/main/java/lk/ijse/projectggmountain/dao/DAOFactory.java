package lk.ijse.projectggmountain.dao;

import lk.ijse.projectggmountain.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,EMPLOYEE,DELIVERY,EVENT,ORDER,ORDER_DETAIL,QUERY,SUPPLIER,SUPPLIER_ORDER_DETAIL,USER
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case DELIVERY:
                return (T) new DeliveryDAOImpl();
            case EVENT:
                return (T) new EventDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case SUPPLIER_ORDER_DETAIL:
                return (T) new SupplierOrderDetailDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }
}
