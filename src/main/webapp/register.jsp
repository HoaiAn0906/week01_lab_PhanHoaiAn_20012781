<%--
  Created by IntelliJ IDEA.
  User: an
  Date: 09/09/2023
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <!-- Thêm link đến các tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="./bootstrap.min.css">
    <script src="./bootstrap.min.css"></script>
</head>
<body>
<div class="container">
    <h1 class="mt-5">User Registration</h1>
    <form action="control-servlet" method="post">
        <div class="form-group">
            <label for="accountId">Account id:</label>
            <input type="text" class="form-control" id="accountId" name="accountId" required>
        </div>

        <div class="form-group">
            <label for="username">Full name:</label>
            <input type="text" class="form-control" id="username" name="fullName" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="number" class="form-control" id="phone" name="phone" required>
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" id="status" name="status" required>
                <option value="1">Active</option>
                <option value="0">DEACTIVATE</option>
                <option value="-1">Delete</option>
            </select>
        </div>

        <input type="submit" class="btn btn-primary" value="Register">
        <input type="hidden" name="action" value="register">
    </form>
    <button class="btn btn-primary" onclick="window.location.href='index.jsp'">Login</button>
</div>
</body>
</html>
