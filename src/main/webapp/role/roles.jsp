<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: an
  Date: 11/09/2023
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Role List</h1>
    <div class="row">
        <div class="col-md-3">
            <!-- Menu bên trái -->
            <ul class="list-group">
                <li class="list-group-item">
                    <form action="control-servlet" method="get">
                        <button type="submit" class="btn btn-primary">Account</button>
                        <input type="hidden" name="action" value="list_account">
                    </form>
                </li>
                <li class="list-group-item">
                    <form action="control-servlet" method="get">
                        <button type="submit" class="btn btn-primary">Role</button>
                        <input type="hidden" name="action" value="list_role">
                    </form>
                </li>
                <li class="list-group-item"><a href="">Log</a></li>
            </ul>
        </div>
        <div class="col-md-9">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <%
                    Object accountObj = request.getAttribute("listRole");

                    if (accountObj != null && accountObj instanceof List) {
                        List<Role> listRole = (List<Role>) accountObj;

                        for (Role role : listRole) { %>
                <tr>
                    <td><%= role.getRoleId() %>
                    </td>
                    <td><%= role.getRoleName() %>
                    </td>
                    <td><%= role.getStatus() %>
                    </td>
                    <!-- Add more table cells if needed -->
                    <td>
                        <form action="control-servlet" method="get">
                            <input type="hidden" name="id" value="<%= role.getRoleId() %>">
                            <button type="submit" class="btn btn-primary">Edit</button>
                            <input type="hidden" name="action" value="edit_role">
                        </form>
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
</body>
</html>
