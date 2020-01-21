package pojo.user;

import lombok.*;

@Data
public class User {
    private Integer id;
    private String login;
    private String password;
    private String phone;
    private String email;

    private User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public static class Builder {
        private Integer id;
        private String login;
        private String password;
        private String phone;
        private String email;

        public Builder(String login, String password) {
            this.id = null;
            this.login = login;
            this.password = password;
        }
        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return  this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

