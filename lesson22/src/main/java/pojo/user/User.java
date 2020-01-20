package pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String login;
    private String password;
    private String phone;
    private String email;

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
