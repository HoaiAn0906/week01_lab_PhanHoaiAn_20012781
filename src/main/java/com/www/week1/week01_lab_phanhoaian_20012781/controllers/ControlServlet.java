package com.www.week1.week01_lab_phanhoaian_20012781.controllers;

import com.www.week1.week01_lab_phanhoaian_20012781.models.Account;
import com.www.week1.week01_lab_phanhoaian_20012781.models.Status;
import com.www.week1.week01_lab_phanhoaian_20012781.repositories.AccountRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "controlServlet", value = "/control-servlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("create_account")) {
//            accountRepository.create(account);
        }
        else if (action.equals("create_role")) {
//            roleRepository.create(role);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();

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
        }
    }
}
