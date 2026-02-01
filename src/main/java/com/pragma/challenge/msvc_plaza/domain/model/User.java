package com.pragma.challenge.msvc_plaza.domain.model;

public class User {
    private String name;
    private String lastname;
    private String phone;

    public User(UserBuilder builder) {
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.phone = builder.phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String lastname;
        private String phone;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

}
