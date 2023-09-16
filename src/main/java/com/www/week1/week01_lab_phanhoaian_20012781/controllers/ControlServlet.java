package com.www.week1.week01_lab_phanhoaian_20012781.controllers;

import com.www.week1.week01_lab_phanhoaian_20012781.models.Account;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Logs;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Role;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Status;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.AccountRepository;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.GrantAccessRepository;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.LogRepository;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.RoleRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

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
        AccountRepository accountRepository = new AccountRepository();
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

        if (action.equals("listRole")) {
            try {
                List<Role> listRole = roleRepository.getAll();
                req.setAttribute("listRole", listRole);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role/roles.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("editRole")) {
            try {
                Role role = roleRepository.getById(req.getParameter("id"));
                req.setAttribute("role", role);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role/edit_role.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("deleteRole")) {
            try {
                boolean res = roleRepository.delete(String.valueOf(req.getParameter("id")));
                if (res) {
                    resp.sendRedirect("control-servlet?action=listRole");
                    //show toast delete success
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete success');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete failed');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addRole")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/role/add_role.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("dashboard")) {
            //get role of account
            try {
                List<Role> listRoleByAccount = roleRepository.getRoleByAccount(req.getCookies()[0].getValue());
                req.setAttribute("listRoleByAccount", listRoleByAccount);
                RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("logout")) {
            System.out.println(req.getParameter("id"));
            // Lấy danh sách các cookie hiện tại
            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                // Lặp qua từng cookie và đặt thời gian sống của nó thành 0 để xóa cookie
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("listAccount")) {
            try {
                List<Account> listAccount = accountRepository.getAll();
                List<Role> listRole = roleRepository.getAll();
                req.setAttribute("listAccount", listAccount);
                req.setAttribute("listRole", listRole);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/account/accounts.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("editAccount")) {
            try {
                Optional<Account> account = accountRepository.getById(req.getParameter("id"));
                req.setAttribute("account", account.get());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/account/edit_account.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("deleteAccount")) {
            try {
                if (req.getParameter("id").equals(req.getCookies()[0].getValue())) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Can not delete account login');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
                boolean res = accountRepository.delete(String.valueOf(req.getParameter("id")));
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                    //show toast delete success
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete success');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addAccount")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/account/add_account.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        LogRepository logRepository = new LogRepository();

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
                    System.out.println(account);
                    Logs log = new Logs();
                    log.setAccount(account.get());
                    log.setLoginTime(java.time.LocalDate.now());
                    log.setNotes("login");
                    logRepository.create(log);
                    //save account to cookie
                    Cookie account_id = new Cookie("account_id", account.get().getAccountId());
                    Cookie full_name = new Cookie("full_name", account.get().getFullName());
                    Cookie email = new Cookie("email", account.get().getEmail());
                    Cookie phone = new Cookie("phone", account.get().getPhone());
                    Cookie status = new Cookie("status", account.get().getStatus().toString());
                    resp.addCookie(account_id);
                    resp.addCookie(full_name);
                    resp.addCookie(email);
                    resp.addCookie(phone);
                    resp.addCookie(status);
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
            if(req.getParameter("status").equals("1")){
                account.setStatus(Status.ACTIVE);
            }else if(req.getParameter("status").equals("0")){
                account.setStatus(Status.DEACTIVATE);
            } else {
                account.setStatus(Status.DELETE);
            }
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
        } else if (action.equals("editRole")) {
            Role role = new Role();
            role.setRoleId(req.getParameter("role_id"));
            role.setRoleName(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if(req.getParameter("status").equals("1")){
                role.setStatus(Status.ACTIVE);
            }else if(req.getParameter("status").equals("0")){
                role.setStatus(Status.DEACTIVATE);
            } else {
                role.setStatus(Status.DELETE);
            }
            try {
                boolean res = roleRepository.update(role);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listRole");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update failed');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addRole")) {
            Role role = new Role();
            role.setRoleId(req.getParameter("role_id"));
            role.setRoleName(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if(req.getParameter("status").equals("1")){
                role.setStatus(Status.ACTIVE);
            }else if(req.getParameter("status").equals("0")){
                role.setStatus(Status.DEACTIVATE);
            } else {
                role.setStatus(Status.DELETE);
            }
            try {
                boolean res = roleRepository.add(role);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listRole");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add failed');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addAccount")) {
            Account account = new Account();
            account.setAccountId(req.getParameter("account_id"));
            account.setPassword(req.getParameter("password"));
            account.setFullName(req.getParameter("full_name"));
            account.setEmail(req.getParameter("email"));
            account.setPhone(req.getParameter("phone"));
            if(req.getParameter("status").equals("1")){
                account.setStatus(Status.ACTIVE);
            }else if(req.getParameter("status").equals("0")){
                account.setStatus(Status.DEACTIVATE);
            } else {
                account.setStatus(Status.DELETE);
            }
            try {
                boolean res = accountRepository.create(account);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("editAccount")) {
            Account account = new Account();
            account.setAccountId(req.getParameter("account_id"));
            account.setPassword(req.getParameter("password"));
            account.setFullName(req.getParameter("full_name"));
            account.setEmail(req.getParameter("email"));
            account.setPhone(req.getParameter("phone"));
            if(req.getParameter("status").equals("1")){
                account.setStatus(Status.ACTIVE);
            }else if(req.getParameter("status").equals("0")){
                account.setStatus(Status.DEACTIVATE);
            } else {
                account.setStatus(Status.DELETE);
            }
            try {
                boolean res = accountRepository.update(account);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("grantPermission")) {
            try {
                boolean res = grantAccessRepository.grantPermission(req.getParameter("accountId"), req.getParameter("roleIds"), req.getParameter("grantType"), req.getParameter("note"));
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Grant success');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Grant failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
