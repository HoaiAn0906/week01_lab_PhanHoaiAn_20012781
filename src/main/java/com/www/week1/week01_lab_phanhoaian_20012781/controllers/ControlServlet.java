package com.www.week1.week01_lab_phanhoaian_20012781.controllers;

import com.www.week1.week01_lab_phanhoaian_20012781.models.Account;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Role;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Status;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.AccountRepository;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.RoleRepository;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "controlServlet", value = "/control-servlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RoleRepository roleRepository = new RoleRepository();

        if (action.equals("list_role")) {
            try {
                List<Role> listRole = roleRepository.getAll();
                System.out.println("ls" + listRole);
                req.setAttribute("listRole", listRole);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role/roles.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("edit_role")) {
            try {
                Role role = roleRepository.getById(req.getParameter("id"));
                req.setAttribute("role", role);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role/edit_role.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("delete_role")) {
            try {
                roleRepository.delete(req.getParameter("id"));
                resp.sendRedirect("control-servlet?action=list_role");
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("create_role")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("create_role.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();

        if (action.equals("logon")) {
            try {
                Optional<Account> account = accountRepository.logon(req.getParameter("email"), req.getParameter("password"));
                //if account is not exist
                if (account.isEmpty()) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Account is not exist');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                } else {
                    //information of account

                    // redirect dashboard and info account
                    req.setAttribute("account", account.get());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch (SQLException | ClassNotFoundException | ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("register")) {
            Account account = new Account();
            account.setAccountId(req.getParameter("accountId"));
            account.setPassword(req.getParameter("password"));
            account.setFullName(req.getParameter("fullName"));
            account.setEmail(req.getParameter("email"));
            account.setPhone(req.getParameter("phone"));
            Status status = Status.valueOf(req.getParameter("status"));
            account.setStatus(status);
            boolean res = false;
            try {
                res = accountRepository.create(account);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (res) {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register success');");
                out.println("location='index.jsp';");
                out.println("</script>");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register failed');");
                out.println("location='register.jsp';");
                out.println("</script>");
            }
        } else if (action.equals("edit_role")) {
            Role role = new Role();
            role.setRoleId(req.getParameter("role_id"));
            role.setRoleName(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if(req.getParameter("status").equals("1")){
                role.setStatus(Status.ACTIVE);
            }else if(req.getParameter("status").equals("0")){
                role.setStatus(Status.DEACTIVE);
            }
            boolean res = false;
            try {
                res = roleRepository.update(role);
                if (res) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update success');");
                    out.println("</script>");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("role/roles.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update failed');");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
