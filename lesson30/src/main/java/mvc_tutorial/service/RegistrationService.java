package mvc_tutorial.service;

import mvc_tutorial.service.dto.UserDto;
import org.springframework.validation.Validator;

public interface RegistrationService extends Validator {
    UserDto regUser(UserDto userDto);

}
