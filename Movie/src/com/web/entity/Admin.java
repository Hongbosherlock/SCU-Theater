package com.web.entity;

/**
 * Created by Hongb on 2018/11/8.
 * 电影管理员   后台管理员/售票员
 */
public class Admin {
    private Long id;
    private String account;
    private String password;
    private Boolean role;
    //管理员类型,role=1为后台管理员，role=0为售票员


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Admin [id=" + id + ", adminName=" + account + ", adminPassword=" + password +",role"+role+ "]";
    }
    public Admin(Long id, String adminAccount, String adminPassword,Boolean role) {
        super();
        this.id = id;
        this.account = adminAccount;
        this.password = adminPassword;
        this.role = role;
    }
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

}
