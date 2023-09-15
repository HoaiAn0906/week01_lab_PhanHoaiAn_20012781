<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Role" %>
<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Account" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: an
  Date: 13/09/2023
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Role Edit</h1>
        <button type="button" class="btn btn-primary mt-2" onclick="window.location.href = 'control-servlet?action=listAccount'">
            <i class="fas fa-arrow-left"></i> Back
        </button>
    </div>
    <div class="row">
        <div class="col-md-2">
            <!-- Menu bên trái -->
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="control-servlet?action=dashboard">Dashboard</a>
                </li>
                <li class="list-group-item">
                    <a href="control-servlet?action=listAccount">Account</a>
                </li>
                <li class="list-group-item">
                    <a href="control-servlet?action=listRole">Role</a>
                </li>
                <li class="list-group-item"><a href="">Log</a></li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-header">Edit account</div>
                        <div class="card-body">
                            <%
                                Optional<Account> accountOptional = (Optional<Account>) request.getAttribute("account");
                                System.out.println(accountOptional);
                            %>
<%--                            <form action="control-servlet" method="post">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="account_id">Account Id</label>--%>
<%--                                    <input type="text" class="form-control" id="account_id" name="account_id" value="<%= account.getAccountId() %>">--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="full_name">Account Name</label>--%>
<%--                                    <input type="text" class="form-control" id="full_name" name="full_name" value="<%= account.getFullName() %>">--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="password">Password</label>--%>
<%--                                    <input type="password" class="form-control" id="password" name="password" value="<%= account.getPassword() %>">--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="email">Email</label>--%>
<%--                                    <input type="email" class="form-control" id="email" name="email" value="<%= account.getEmail() %>">--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="phone">Phone</label>--%>
<%--                                    <input type="number" class="form-control" id="phone" name="phone" value="<%= account.getPhone() %>">--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="status">Status</label>--%>
<%--                                    <select class="form-control" id="status" name="status">--%>
<%--                                        <option value="1" <%= account.getStatus().getCode() == 1 ? "selected" : "" %>>Active</option>--%>
<%--                                        <option value="0" <%= account.getStatus().getCode() == 0 ? "selected" : "" %>>DEACTIVATE</option>--%>
<%--                                        <option value="-1" <%= account.getStatus().getCode() == -1 ? "selected" : "" %>>Delete</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <button type="submit" class="btn btn-primary">Submit</button>--%>
<%--                                <input type="hidden" name="action" value="editAccount">--%>
<%--                            </form>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
