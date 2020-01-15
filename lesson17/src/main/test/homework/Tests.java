package homework;

/*
Задание1
Взять за основу ДЗ_15. Написать тест на CRUD операции
Инициализацию соединение с БД произвести один раз перед началом тестов, после завершения всех тестов, закрыть соединие с БД
Подготовку данных для CRUD операций производить в методе Init использую @Before
Задание 2
Использую Spy и Mockito создать заглушки для интерфейса JDBC
*/

import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import dao.UserDao;
import dao.UserDaoJdbcImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class Tests {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tests.class);
    private UserDao userDao;
    private  ConnectionManager connectionManager;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSetMock;
    private User user;
//    @BeforeAll
//    static void init() {
//        initMocks(this);
//    }

    @BeforeEach
    void startUp() {
        initMocks(this);
        connectionManager = spy(ConnectionManagerJdbcImpl.getInstance());
        userDao = new UserDaoJdbcImpl(connectionManager);
    }

//    @AfterAll
//    static void closeConnection() {
//        connectionManager.closeConnection(connection);
//    }

    @Test
    void addUser() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(UserDaoJdbcImpl.INSERT_INTO_USERS);
        //User(Integer id, String name, Date birthday, Integer loginId, String city, String email, String description)
        Integer id = 1;
        String name = "Username";
        Date birthday = Date.valueOf("1985-11-05");
        Integer loginId = 10;
        String city = "City";
        String email = "mail@mail.com";
        String description = "random description";
        User user = new User(id, name, birthday, loginId, city, email, description);
        boolean result = userDao.addUser(user);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.INSERT_INTO_USERS);
        verify(preparedStatement, times(1)).setString(1, name);
        verify(preparedStatement, times(1)).setDate(2, birthday);
        verify(preparedStatement, times(1)).setInt(3, loginId);
        verify(preparedStatement, times(1)).setString(4, city);
        verify(preparedStatement, times(1)).setString(5, email);
        verify(preparedStatement, times(1)).setString(6, description);

        assertAll("Assert all", () -> assertTrue(result));
    }

//    @Disabled("Not working correctly")
    @Test
    void getUserById() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getInt("ID")).thenReturn(1);
        when(resultSetMock.getString("NAME")).thenReturn("Username");
        when(resultSetMock.getDate("BIRTHDAY")).thenReturn(Date.valueOf("1985-11-05"));
        when(resultSetMock.getInt("LOGIN_ID")).thenReturn(-1452484329);
        when(resultSetMock.getString("CITY")).thenReturn("City");
        when(resultSetMock.getString("EMAIL")).thenReturn("mail@mail.com");
        when(resultSetMock.getString("DESCRIPTION")).thenReturn("random description");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDaoJdbcImpl.GET_USER_BY_ID);
        doReturn(resultSetMock).when(preparedStatement).executeQuery();
//        when(userDao.getUserById(1)).thenReturn(new User(
//                1,
//                "Username",
//                Date.valueOf("1985-11-05"),
//                -1452484329,
//                "City",
//                "mail@mail.com",
//                "random description"
//        ));
        User user = new User(
                1,
                "Username",
                Date.valueOf("1985-11-05"),
                -1452484329,
                "City",
                "mail@mail.com",
                "random description"
        );
        Integer id = 1;
        User user1 = userDao.getUserById(id);
        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.GET_USER_BY_ID);
//        verify(preparedStatement, times(1)).getResultSet();

//        assertEquals(user, user1);
        assertAll(() -> assertEquals(user, user1));
    }

    @Test
    void updateUserById() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(UserDaoJdbcImpl.UPDATE_USER_BY_ID);
        User user = new User(2, "New userName", Date.valueOf("1955-05-11"), 24, "new City", "newmail@mail.com", "new description");
        boolean result = userDao.updateUserById(user);

        verify(connectionManager, times (1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.UPDATE_USER_BY_ID);
        verify(preparedStatement, times(1)).setString(1, user.getName());
        verify(preparedStatement, times(1)).setDate(2, user.getBirthday());
        verify(preparedStatement, times(1)).setInt(3, user.getLoginId());
        verify(preparedStatement, times(1)).setString(4,user.getCity());
        verify(preparedStatement, times(1)).setString(5, user.getEmail());
        verify(preparedStatement, times(1)).setString(6, user.getDescription());
        verify(preparedStatement, times(1)).setInt(7, user.getId());

        assertAll(() -> assertTrue(result));

    }

    @Test
    void deleteUserById() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(UserDaoJdbcImpl.DELETE_USER_BY_ID);
        Integer id = 2;
        boolean result = userDao.deleteUserById(id);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.DELETE_USER_BY_ID);
        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).execute();

        assertAll(() -> assertTrue(result));
    }
}
