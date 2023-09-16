<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Role" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=, initial-scale=1.0">
    <title>Dashboard</title>
    <!-- Thêm liên kết đến Bootstrap CSS -->
    <link rel="stylesheet" href="./bootstrap.min.css">
</head>
<body>
<div class="container">
    <%-- Lấy thông tin từ các cookie --%>
    <% Cookie[] cookies = request.getCookies(); %>
    <% String accountId = null, fullName = null, email = null, phone = null, status = null; %>
    <% if (cookies != null) { %>
    <% for (Cookie cookie : cookies) { %>
    <% if ("full_name".equals(cookie.getName())) { %>
    <% fullName = cookie.getValue(); %>
    <% } else if ("email".equals(cookie.getName())) { %>
    <% email = cookie.getValue(); %>
    <% } else if ("phone".equals(cookie.getName())) { %>
    <% phone = cookie.getValue(); %>
    <% } else if ("status".equals(cookie.getName())) { %>
    <% status = cookie.getValue(); %>
    <% } else if ("account_id".equals(cookie.getName())) { %>
    <% accountId = cookie.getValue(); %>
    <% } %>
    <% } %>
    <% } %>
    <div class="d-flex align-items-center justify-content-between">
        <h1>Dashboard</h1>
        <!-- Logout Button -->
        <form action="control-servlet" method="get">
            <button type="submit" class="btn btn-danger" onclick="window.location.href = 'control-servlet?action=logout&id=<%= accountId %>'">Logout</button>
            <input type="hidden" name="action" value="logout">
        </form>
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
            <div class="row">
                <div class="col-md-5">
                    <div class="card">
                        <div class="card-header">Information Account</div>
                        <div class="card-body">
                            <%-- Hiển thị thông tin từ các cookie --%>
                            <p><strong>Full Name:</strong> <%= fullName %>
                            </p>
                            <p><strong>Email:</strong> <%= email %>
                            </p>
                            <p><strong>Phone:</strong> <%= phone %>
                            </p>
                            <p><strong>Status:</strong> <span class="badge badge-primary"><%= status %></span></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">Role off account</div>
                        <div class="card-body">
                            <span>Name: </span>
                            <% Object listRoleByAccount = request.getAttribute("listRoleByAccount");
                                if (listRoleByAccount != null) {
                                    List<Role> roles = (List<Role>) listRoleByAccount;
                                    for (Role role : roles) {
                            %>
                            <span>
                                <%= role.getRoleName() %>,
                            </span>
                            <% }
                            } else { %>
                            <p>Not found</p>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>