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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Role List</h1>
        <button type="button" class="btn btn-success mt-1" onclick="window.location.href = 'control-servlet?action=addRole'">
            <i class="fas fa-plus"></i> Add Role
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
                <li class="list-group-item">
                    <a href="control-servlet?action=listLog">Log</a>
                </li>
            </ul>
        </div>
        <div class="col-md-9">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 20%;">ID</th>
                        <th scope="col" style="width: 20%;">Name</th>
                        <th scope="col" style="width: 20%;">Description</th>
                        <th scope="col" style="width: 20%;">Status</th>
                        <th scope="col" style="width: 20%;">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object accountObj = request.getAttribute("listRole");

                        if (accountObj != null && accountObj instanceof List) {
                            List<Role> listRole = (List<Role>) accountObj;

                            for (Role role : listRole) { %>
                    <tr>
                        <td scope="row">
                            <%= role.getRoleId() %>
                        </td>
                        <td>
                            <%= role.getRoleName() %>
                        </td>
                        <td>
                            <%
                                String description = role.getDescription();
                                if (description.length() > 20) {
                                    description = description.substring(0, 20) + "...";
                                }
                            %>
                            <%= description %>
                        </td>
                        <td>
                            <%
                                String status = String.valueOf(role.getStatus());
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
                            <button type="button" class="btn btn-warning mr-2" onclick="window.location.href = 'control-servlet?action=editRole&id=<%= role.getRoleId() %>'">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button type="button" class="btn btn-danger" onclick="deleteRole('<%= role.getRoleId() %>')">
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
            window.location.href = "control-servlet?action=deleteRole&id=" + id;
        }
    }
</script>
</html>
