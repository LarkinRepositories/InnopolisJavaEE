package pojo.user;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String login;
    private String password;
    private String phone;
    private String email;

    private User() {}

    public static class Builder extends UserBuilderTemplate {
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

        @Override
        void setLogin(String login) {
            this.login = login;
        }

        @Override
        void setPassword(String password) {
            this.password = password;
        }

        @Override
        void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        void setEmail(String email) {
            this.email = email;
        }

        public User build() {
            User user = new User();
            user.login = this.login;
            user.password = this.password;
            user.phone = this.phone;
            user.email = this.email;
            return user;
        }
    }
}

