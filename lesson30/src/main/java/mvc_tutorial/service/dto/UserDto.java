package mvc_tutorial.service.dto;

import java.util.Objects;
import java.util.UUID;

public class UserDto {
    public UserDto() {
    }

    public UserDto(String username, String password, String repeatpassword, String email) {
        this.username = username;
        this.password = password;
        this.repeatpassword = repeatpassword;
        this.email = email;
    }

    private UUID id;
    private String username;
    private String password;
    private String repeatpassword;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatpassword() {
        return repeatpassword;
    }

    public void setRepeatpassword(String repeatpassword) {
        this.repeatpassword = repeatpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, repeatpassword, email);
    }
}
