package com.www.week1.week01_lab_phanhoaian_20012781.repositories;

import com.www.week1.week01_lab_phanhoaian_20012781.db.ConnectDB;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Account;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Logs;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogRepository {
    //crud log
//    id	account_id	login_time	logout_time	notes
//1	1	2023-09-10 22:08:44	2023-09-10 22:08:48	no
    //crud
    //get all
    public List<Logs> getAll() throws SQLException, ClassNotFoundException {
        List<Logs> listLog = new ArrayList<>();
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from log";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.fromCode(rs.getInt("status")));
                listLog.add(new Logs(rs.getString("id"), account, rs.getDate("login_time").toLocalDate(), rs.getDate("logout_time").toLocalDate(), rs.getString("notes")));
            }

            return listLog;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //get by id
    public Logs getById(String id) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from log where id = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.fromCode(rs.getInt("status")));
                return new Logs(rs.getString("id"), account, rs.getDate("login_time").toLocalDate(), rs.getDate("logout_time").toLocalDate(), rs.getString("notes"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //create
    public boolean create(Logs log) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "insert into log(id, account_id, login_time, logout_time, notes) values(?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, log.getId());
            statement.setString(2, log.getAccount().getAccountId());
            statement.setDate(3, java.sql.Date.valueOf(log.getLoginTime()));
            statement.setDate(4, java.sql.Date.valueOf(log.getLogoutTime()));
            statement.setString(5, log.getNotes());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //update
    public boolean update(Logs log) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "update log set account_id = ?, login_time = ?, logout_time = ?, notes = ? where id = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, log.getAccount().getAccountId());
            statement.setDate(2, java.sql.Date.valueOf(log.getLoginTime()));
            statement.setDate(3, java.sql.Date.valueOf(log.getLogoutTime()));
            statement.setString(4, log.getNotes());
            statement.setString(5, log.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //delete
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "delete from log where id = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
