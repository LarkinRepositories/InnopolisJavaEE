package service.mobile;

import pojo.mobile.Mobile;

import java.util.Collection;

public interface MobileService {
    Collection<Mobile> getMobiles();
    boolean addMobile(String model, Integer price, String manufacturer);
    Mobile getMobileById(Integer id);
    boolean deleteMobileById(Integer id);
    boolean updateMobileById(Integer id, String model, Integer price, String manufacturer);

}
