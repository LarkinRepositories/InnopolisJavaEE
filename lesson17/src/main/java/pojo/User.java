package pojo;

import lombok.Data;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Data
public class User {
    private Integer id;
    private String name;
    private Date birthday;
    private Integer loginId;
    private String city;
    private String email;
    private String description;


    public User(Integer id, String name, Date birthday, Integer loginId, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.loginId = loginId;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(loginId, user.loginId) &&
                Objects.equals(city, user.city) &&
                Objects.equals(email, user.email) &&
                Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday, loginId, city, email, description);
    }
}
