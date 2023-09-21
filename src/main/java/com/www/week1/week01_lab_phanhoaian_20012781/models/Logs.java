package com.www.week1.week01_lab_phanhoaian_20012781.models;

import java.sql.Timestamp;

public class Logs {
    private String id;
    private Account account;

    private Timestamp loginTime;

    private Timestamp logoutTime;
    private String notes;

    public Logs() {
    }

    public Logs(String id, Account account, Timestamp loginTime, Timestamp logoutTime, String notes) {
        this.id = id;
        this.account = account;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    public Logs(String id, Account account, Timestamp loginTime, String notes) {
        this.id = id;
        this.account = account;
        this.loginTime = loginTime;
        this.notes = notes;
    }

    public Logs(String id, Account account, Timestamp logoutTime) {
        this.id = id;
        this.account = account;
        this.logoutTime = logoutTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id='" + id + '\'' +
                ", account=" + account +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}
