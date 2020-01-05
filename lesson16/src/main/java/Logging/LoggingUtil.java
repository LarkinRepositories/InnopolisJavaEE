package Logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingUtil {
    public static final Logger LOGGER = LogManager.getLogger("Logger");
    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
    }

}
