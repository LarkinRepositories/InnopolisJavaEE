package dao;

import pojo.Mobile;

public interface MobileDao {
    boolean addMobile(Mobile mobile);

    Mobile getMobileById(Integer id);

    boolean updateMobileById(Mobile mobile);

    boolean deleteMobileById(Integer id);

    void createTable();
}
