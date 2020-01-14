import ConnectionManager.ConnectionManager;
import dao.UserDao;
import dao.UserDaoJdbcImpl;
import lectureAdaptation.Main;
import mocks.ConnectionManagerMock;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;


class MainTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);
    private UserDao userDao;
    private ConnectionManager connectionManager;
    private Main main;

    @BeforeEach
    void setUp() {
        LOGGER.trace("BeforeEach in MainTest");
        main = new Main();
        connectionManager = new ConnectionManagerMock();
        userDao = new UserDaoJdbcImpl(connectionManager);
    }

    @BeforeAll
    static void tearDownAll() {
        LOGGER.trace("Beforeall in MainTest");
    }

    @AfterAll
    static void setupAll() {
        LOGGER.trace("AfterAll in MainTest");
    }

    @Test
    @DisplayName("MAIN TEST")
    void main() {
        assumeTrue(main != null);
       assertDoesNotThrow(() -> main.method1(userDao));
    }

    @Test
    void mainWithException() {
        assertThrows(NullPointerException.class, () -> main.method1(null));

    }

}