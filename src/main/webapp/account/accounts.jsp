<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: an
  Date: 11/09/2023
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Account List</h1>
        <button type="button" class="btn btn-success mt-1" onclick="window.location.href = 'control-servlet?action=addAccount'">
            <i class="fas fa-plus"></i> Add account
        </button>
    </div>
    <div class="row">
        <div class="col-md-3">
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
        <div class="col-md-9">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 10%;">ID</th>
                        <th scope="col" style="width: 20%;">Full Name</th>
                        <th scope="col" style="width: 20%;">Email</th>
                        <th scope="col" style="width: 20%;">Phone</th>
                        <th scope="col" style="width: 20%;">Status</th>
                        <th scope="col" style="width: 10%;">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object accountObj = request.getAttribute("listAccount");

                        if (accountObj != null && accountObj instanceof List) {
                            List<Account> listAccount = (List<Account>) accountObj;

                            for (Account account : listAccount) { %>
                    <tr>
                        <td scope="row">
                            <%= account.getAccountId() %>
                        </td>
                        <td>
                            <%= account.getFullName() %>
                        </td>
                        <td>
                            <%= account.getEmail() %>
                        </td>
                        <td>
                            <%= account.getPhone() %>
                        </td>
                        <td>
                            <%
                                String status = String.valueOf(account.getStatus());
                                String color = "success";
                                if (status.equals("Active")) {
                                    color = "success";
                                } else if (status.equals("DEACTIVATE")) {
                                    color = "warning";
                                } else if (status.equals("PENDING")) {
                                    color = "danger";
                                }
                            %>
                            <span class="badge badge-<%= color %>"><%= status %></span>
                        </td>
                        <td class="d-flex">
                            <button type="button" class="btn btn-warning mr-2" onclick="window.location.href = 'control-servlet?action=editAccount&id=<%= account.getAccountId() %>'">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button type="button" class="btn btn-danger" onclick="deleteRole(<%= account.getAccountId() %>)">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                        </td>
                    </tr>
                    <% }
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function deleteRole(id) {
        if (confirm("Are you sure?")) {
            window.location.href = "control-servlet?action=delete_account&id=" + id;
        }
    }
</script>
</html>
