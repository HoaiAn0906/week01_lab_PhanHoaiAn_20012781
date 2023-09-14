<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Account" %>
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
    <div class="d-flex align-items-center justify-content-between">
        <h1>Dashboard</h1>
        <!-- Logout Button -->
        <a href="index.jsp" class="btn btn-danger">Logout</a>
    </div>
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
            <div class="row">
                <div class="col-md-5">
                    <div class="card">
                        <div class="card-header">Information Account</div>
                        <div class="card-body">
                            <%
                                Object accountObj = request.getAttribute("account");
                                Account account = (Account) accountObj;
                            %>
                            <p><strong>Full Name:</strong> <%= account.getFullName() %></p>
                            <p><strong>Email:</strong> <%= account.getEmail() %></p>
                            <p><strong>Phone:</strong> <%= account.getPhone() %></p>
                            <p><strong>Status:</strong> <span class="badge badge-primary"><%= account.getStatus() %></span></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">Information Role</div>
                        <div class="card-body">
                            <p><strong>Test 1:</strong></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
