<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Role" %><%--
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
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Role</h1>
    </div>
    <div class="row">
        <div class="col-md-2">
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
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-header">Edit role</div>
                        <div class="card-body">
                            <%
                                Role role = (Role) request.getAttribute("role");
                            %>
                            <form action="control-servlet" method="post">
                                <input type="hidden" name="role_id" value="<%= role.getRoleId() %>">
                                <div class="form-group">
                                    <label for="role_name">Role Name</label>
                                    <input type="text" class="form-control" id="role_name" name="role_name" value="<%= role.getRoleName() %>">
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea class="form-control" id="description" name="description"><%= role.getDescription() %></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="status">Status</label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="1" <%= role.getStatus().getCode() == 1 ? "selected" : "" %>>Active</option>
                                        <option value="0" <%= role.getStatus().getCode() == 0 ? "selected" : "" %>>Deactive</option>
                                        <option value="-1" <%= role.getStatus().getCode() == -1 ? "selected" : "" %>>Delete</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <input type="hidden" name="action" value="edit_role">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
