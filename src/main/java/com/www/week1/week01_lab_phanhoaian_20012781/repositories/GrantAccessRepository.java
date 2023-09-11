package com.www.week1.week01_lab_phanhoaian_20012781.repositories;

import com.www.week1.week01_lab_phanhoaian_20012781.db.ConnectDB;
import com.www.week1.week01_lab_phanhoaian_20012781.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessRepository {
    //get all
    public List<GrantAccess> getAll() throws SQLException, ClassNotFoundException {
        List<GrantAccess> listGrantAccess = new ArrayList<>();
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from grant_access " +
                    "join account on grant_access.account_id = account.account_id " +
                    "join role on grant_access.role_id = role.role_id)";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.fromCode(rs.getInt("status")));
                Role role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("description"),
                        Status.fromCode(rs.getInt("status")));
                listGrantAccess.add(new GrantAccess(role, account, Grant.fromCode(rs.getInt("is_grant")), rs.getString("note")));
            }

            return listGrantAccess;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //get by id
    public GrantAccess getById(String accountId, String roleId) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from grant_access " +
                    "join account on grant_access.account_id = account.account_id " +
                    "join role on grant_access.role_id = role.role_id " +
                    "where grant_access.account_id = ? and grant_access.role_id = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, accountId);
            statement.setString(2, roleId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.fromCode(rs.getInt("status")));
                Role role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("description"),
                        Status.fromCode(rs.getInt("status")));
                return new GrantAccess(role, account, Grant.fromCode(rs.getInt("is_grant")), rs.getString("note"));
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //create
    public boolean create(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, grantAccess.getAccount().getAccountId());
            statement.setString(2, grantAccess.getRole().getRoleId());
            statement.setInt(3, grantAccess.getGrant().getCode());
            statement.setString(4, grantAccess.getNote());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //update
    public boolean update(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE grant_access SET is_grant=?, note=? WHERE account_id=? and role_id=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, grantAccess.getGrant().getCode());
            statement.setString(2, grantAccess.getNote());
            statement.setString(3, grantAccess.getAccount().getAccountId());
            statement.setString(4, grantAccess.getRole().getRoleId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //delete
    public boolean delete(String accountId, String roleId) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM grant_access WHERE account_id=? and role_id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, accountId);
            statement.setString(2, roleId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
