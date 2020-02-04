package mvc_tutorial.service;

import mvc_tutorial.db.dao.UserDao;
import mvc_tutorial.db.model.User;
import mvc_tutorial.service.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private UserDao userDao;

    public RegistrationServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDto regUser(UserDto userDto) {
        User user = new User(UUID.randomUUID(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
        userDao.addUser(user);
        user.setId(user.getId());
        return userDto;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userForm = (UserDto) target;
        if (userForm.getUsername().isEmpty()) {
            LOGGER.error("username is empty");
            errors.rejectValue("username", "username.empty", "Укажите логин");
        }

        if(errors.hasErrors()) return;

        if (!userForm.getPassword().equals(userForm.getRepeatpassword())) {
            LOGGER.error("passwords mismatch ");
            errors.rejectValue("repeatpassword", "password.mismatch", "Пароли не совпадают");
        }

        if(errors.hasErrors()) return;
    }
}
