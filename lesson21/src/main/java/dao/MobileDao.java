package dao;

import pojo.Mobile;

import java.util.Collection;

public interface MobileDao {
    boolean addMobile(Mobile mobile);

    Mobile getMobileById(Integer id);

    boolean updateMobileById(Mobile mobile);

    boolean deleteMobileById(Integer id);

    void createTable();

    Collection<Mobile> getAllMobile();
}
