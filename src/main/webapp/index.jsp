<!DOCTYPE html>
<html>
<head>
    <title>JSP - Login</title>
    <!-- Đảm bảo bạn đã bao gồm các tệp CSS và JavaScript của Bootstrap -->
    <link rel="stylesheet" href="./bootstrap.min.css">
    <script src="./bootstrap.min.js"></script>
    <script>
        function validateForm() {
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;

            // Kiểm tra xem các trường có được điền đầy đủ không
            if (email === "" || password === "") {
                alert("Please fill all fields...!!!!!!");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <form action="control-servlet" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="email">Username:</label>
            <input type="email" class="form-control" id="email" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <a href="register.jsp" class="btn btn-link">Register</a>
        <input type="hidden" name="action" value="logon">
    </form>
</div>
</body>
</html>
