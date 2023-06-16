package lk.ijse.projectggmountain.bo.custom.impl;

import lk.ijse.projectggmountain.bo.custom.ItemBO;
import lk.ijse.projectggmountain.dao.DAOFactory;
import lk.ijse.projectggmountain.dao.custom.ItemDAO;
import lk.ijse.projectggmountain.dto.InventoryDTO;
import lk.ijse.projectggmountain.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public boolean saveItem(InventoryDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getId(),dto.getName(),dto.getCategory(),dto.getUnitPrice(),dto.getQtyOnHand()));}

    public ArrayList<InventoryDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<InventoryDTO> arrayList = new ArrayList<>();

        for (Item i : all) {
            arrayList.add(new InventoryDTO(i.getItemId(),i.getItemName(),i.getCategory(),i.getUnitPrice(),i.getQtyOnHand()));
        }
        return arrayList;
    }

    public InventoryDTO findByItemId(String id) throws SQLException {
        Item item = itemDAO.findById(id);
        return new InventoryDTO(item.getItemId(),item.getItemName(),item.getCategory(),item.getUnitPrice(),item.getQtyOnHand());
    }

    public boolean updateItem(InventoryDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getId(), dto.getName(),dto.getCategory(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
}
