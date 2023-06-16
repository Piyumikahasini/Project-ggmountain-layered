package lk.ijse.projectggmountain.bo;

import lk.ijse.projectggmountain.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO, ITEM_BO, EMPLOYEE_BO,DELIVERY_BO,EVENT_BO,ORDER_BO,ORDER_DETAIL_BO,SUPPLIER_BO,SUPPLIER_ORDER_DETAIL_BO,FORGOT_PASSWORD_BO,SIGNUP_BO,HOME_BO,LOGIN_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case ITEM_BO:
                return (T) new ItemBOImpl();
            case EMPLOYEE_BO:
                return (T) new EmployeeBOImpl();
            case DELIVERY_BO:
                return (T) new DeliveryBOImpl();
            case EVENT_BO:
                return (T) new EventBOImpl();
            case ORDER_BO:
                return (T) new OrderBOImpl();
            case ORDER_DETAIL_BO:
                return (T) new OrderDetailBOImpl();
            case SUPPLIER_BO:
                return (T) new SupplierBOImpl();
            case SUPPLIER_ORDER_DETAIL_BO:
                return (T) new SupplierOrderDetailBOImpl();
            case FORGOT_PASSWORD_BO:
                return (T) new ForgotPasswordBOImpl();
            case SIGNUP_BO:
                return (T) new SignUpBOImpl();
            case HOME_BO:
                return (T) new HomeBoImpl();
            case LOGIN_BO:
                return (T) new LoginBOImpl();
            default:
                return null;
        }
    }
}
