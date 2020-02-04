package service.mobile;

import ConnectionManager.ConnectionManager;
import dao.mobile.MobileDao;
import dao.mobile.MobileDaoJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pojo.mobile.Mobile;

import java.util.Collection;

@Service
public class MobileServiceImpl implements MobileService {
    private Logger logger = LoggerFactory.getLogger(MobileService.class);
//    @Autowired
    private final MobileDao mobileDao;

    @Autowired
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
