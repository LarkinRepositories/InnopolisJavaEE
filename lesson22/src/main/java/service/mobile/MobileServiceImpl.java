package service.mobile;

import ConnectionManager.ConnectionManager;
import dao.mobile.MobileDao;
import dao.mobile.MobileDaoJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.mobile.Mobile;

import java.util.Collection;


public class MobileServiceImpl implements MobileService {
    private Logger logger = LoggerFactory.getLogger(MobileService.class);
    private final MobileDao mobileDao;

    public MobileServiceImpl(ConnectionManager connectionManager) {
        mobileDao = new MobileDaoJdbcImpl(connectionManager);
    }


    @Override
    public Collection<Mobile> getMobiles() {
        return mobileDao.getAllMobile();
    }

    @Override
    public boolean addMobile(String model, Integer price, String manufacturer) {
        Mobile mobile = new Mobile(null, model, price, manufacturer);
        return mobileDao.addMobile(mobile);
    }

    @Override
    public Mobile getMobileById(Integer id) {
        return mobileDao.getMobileById(id);
    }

    @Override
    public boolean deleteMobileById(Integer id) {
        return mobileDao.deleteMobileById(id);
    }

    @Override
    public boolean updateMobileById(Integer id, String model, Integer price, String manufacturer) {
        Mobile mobile = mobileDao.getMobileById(id);
        mobile.setModel(model);
        mobile.setPrice(price);
        mobile.setManufacturer(manufacturer);
        return mobileDao.updateMobileById(mobile);
    }
}
