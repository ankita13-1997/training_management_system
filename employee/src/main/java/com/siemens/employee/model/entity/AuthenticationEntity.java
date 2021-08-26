package com.siemens.employee.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_authentication")
public class AuthenticationEntity {

    @Id
    private String username;
    private String password;
    private Boolean change_password;
    private Long last_authentication_time;
    private String status;


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

    public Boolean getChange_password() {
        return change_password;
    }

    public void setChange_password(Boolean change_password) {
        this.change_password = change_password;
    }

    public Long getLast_authentication_time() {
        return last_authentication_time;
    }

    public void setLast_authentication_time(Long last_authentication_time) {
        this.last_authentication_time = last_authentication_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
