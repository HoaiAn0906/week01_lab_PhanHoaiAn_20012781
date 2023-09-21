package com.www.week1.week01_lab_phanhoaian_20012781.repositories;

import com.www.week1.week01_lab_phanhoaian_20012781.db.ConnectDB;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Account;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Logs;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Status;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
            String sql = "Select * from log join account on log.account_id = account.account_id order by login_time desc";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.fromCode(rs.getInt("status")));
                //if logout_time is null
                if (rs.getDate("logout_time") == null) {
                    listLog.add(new Logs(rs.getString("id"), account, new Timestamp(rs.getTimestamp("login_time").getTime()), rs.getString("notes")));
                } else {
                    listLog.add(new Logs(rs.getString("id"), account, new Timestamp(rs.getTimestamp("login_time").getTime()), new Timestamp(rs.getTimestamp("logout_time").getTime()), rs.getString("notes")));
                }
            }

            System.out.println(listLog);
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
                return new Logs(rs.getString("id"), account, new Timestamp(rs.getTimestamp("login_time").getTime()), new Timestamp(rs.getTimestamp("logout_time").getTime()), rs.getString("notes"));
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
            String sql;
            if (log.getLoginTime() == null) {
                sql = "insert into log(account_id, logout_time, notes) values(?, ?, ?)";
                statement = con.prepareStatement(sql);
                statement.setString(1, log.getAccount().getAccountId());
                statement.setTimestamp(2, new Timestamp(log.getLogoutTime().getTime()));
                statement.setString(3, log.getNotes());
                statement.executeUpdate();
            } else {
                sql = "insert into log(account_id, login_time, logout_time, notes) values(?, ?, ?, ?)";
                statement = con.prepareStatement(sql);
                statement.setString(1, log.getAccount().getAccountId());
                statement.setTimestamp(2, new Timestamp(log.getLoginTime().getTime()));
                //set value null
                statement.setDate(3, null);
                statement.setString(4, log.getNotes());
                statement.executeUpdate();
            }

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
            //get log last
            Logs logLast = this.getLastLogByAccountId(log.getAccount().getAccountId());
            String sql = "update log set account_id = ?, logout_time = ?, notes = ? where id = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, log.getAccount().getAccountId());
            statement.setTimestamp(2, new Timestamp(log.getLogoutTime().getTime()));
            statement.setString(3, log.getNotes());
            statement.setString(4, logLast.getId());
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

    public Logs getLastLogByAccountId(String accountId) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        System.out.println(accountId);
        try {
            String sql = "select * from log where account_id = ? order by id desc limit 1";
            statement = con.prepareStatement(sql);
            statement.setString(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("account_id"));
                if (rs.getDate("logout_time") == null) {
                    return new Logs(rs.getString("id"), account, new Timestamp(rs.getTimestamp("login_time").getTime()), rs.getString("notes"));
                } else {
                    return new Logs(rs.getString("id"), account, new Timestamp(rs.getTimestamp("login_time").getTime()), new Timestamp(rs.getTimestamp("logout_time").getTime()), rs.getString("notes"));
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
