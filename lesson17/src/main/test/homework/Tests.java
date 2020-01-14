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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
