import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import dao.MobileDao;
import dao.MobileDaoJdbcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Mobile;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
        MobileDao mobileDao = new MobileDaoJdbcImpl(connectionManager);
        mobileDao.createTable();
        Main main = new Main();
        main.method1(mobileDao);
    }

    public void method1(MobileDao mobileDao) {
        Mobile mobile = new Mobile(null, "Iphone 2", 25000, "Apple");
        mobileDao.addMobile(mobile);
        mobile = mobileDao.getMobileById(1);
        LOGGER.info("Начальный объект: {}", mobile);
        mobile.setPrice(70000);
        mobileDao.updateMobileById(mobile);
        mobile = mobileDao.getMobileById(1);
        LOGGER.info("Итоговый объект: {}", mobile);
    }
}
