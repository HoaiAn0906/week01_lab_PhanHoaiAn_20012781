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
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
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
                            <button type="button" class="btn btn-danger mr-2" onclick="deleteAccount('<%= account.getAccountId() %>')">
                                <i class="fas fa-trash-alt"></i>
                            </button>
<%--                            <button type="button" class="btn btn-secondary" onclick="dialogGrantPermissions('<%= account.getAccountId() %>')">--%>
<%--                                Grant permissions--%>
<%--                            </button>--%>
                            <!-- Thêm nút "Grant permissions" vào mỗi hàng của bảng -->
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#grantPermissionModal<%= account.getAccountId() %>">
                                Grant permissions
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="grantPermissionModal<%= account.getAccountId() %>" tabindex="-1" role="dialog" aria-labelledby="grantPermissionModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="grantPermissionModalLabel">Grant Permissions to: <%= account.getFullName() %></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="">
                                                <div class="form-group">
                                                    <label for="roles">Select Roles:</label>
                                                    <select multiple class="form-control" id="roles">
                                                        <%
                                                            Object roleObj = request.getAttribute("listRole");
                                                            if (roleObj != null && roleObj instanceof List) {
                                                                List<Role> listRoles = (List<Role>) roleObj;
                                                                for (Role role : listRoles) {
                                                        %>
                                                        <option value="<%= role.getRoleId() %>"><%= role.getRoleName() %></option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                            </form>
                                            <div id="selectedRoles"></div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
    function deleteAccount(id) {
        if (confirm("Are you sure?")) {
            window.location.href = "control-servlet?action=deleteAccount&id=" + id;
        }
    }

    / Hàm để hiển thị các vai trò đã chọn
    function saveSelectedRoles() {
        var selectedRoles = document.getElementById("roles").selectedOptions;
        var selectedRolesDiv = document.getElementById("selectedRoles");
        selectedRolesDiv.innerHTML = "<p>Selected Roles:</p>";

        for (var i = 0; i < selectedRoles.length; i++) {
            selectedRolesDiv.innerHTML += selectedRoles[i].text + "<br>";
        }
    }
</script>
</html>
