package com.cscorner.calculatrice.database;


    public class User {
        private long id;
        private String email;
        private String password1;
        private String username;
        private String password2;
        private String password3;

        public User() {

        }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword1() {
            return password1;
        }

        public void setPassword1(String password1) {
            this.password1 = password1;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword2() {
            return password2;
        }

        public void setPassword2(String password2) {
            this.password2 = password2;
        }

        public String getPassword3() {
            return password3;
        }

        public void setPassword3(String password3) {
            this.password3 = password3;
        }
    }

