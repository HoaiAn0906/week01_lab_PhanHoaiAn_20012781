<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Account" %>
<%@ page import="com.www.week1.week01_lab_phanhoaian_20012781.models.Logs" %><%--
  Created by IntelliJ IDEA.
  User: an
  Date: 11/09/2023
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Account</title>
<!-- Include Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Include jQuery and Select2 CSS & JS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Log List</h1>
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
                        <th scope="col" style="width: 10%;">ID</th>
                        <th scope="col" style="width: 20%;">Account Name</th>
                        <th scope="col" style="width: 20%;">Email</th>
                        <th scope="col" style="width: 20%;">Phone</th>
                        <th scope="col" style="width: 20%;">Login Time</th>
                        <th scope="col" style="width: 20%;">Logout Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object logObj = request.getAttribute("listLog");

                        if (logObj != null && logObj instanceof List) {
                            List<Logs> listLog = (List<Logs>) logObj;

                            for (Logs log : listLog) { %>
                    <tr>
                        <td scope="row">
                            <%= log.getId() %>
                        </td>
                        <td>
                            <%= log.getAccount().getFullName() %>
                        </td>
                        <td>
                            <%= log.getAccount().getEmail() %>
                        </td>
                        <td>
                            <%= log.getAccount().getPhone() %>
                        </td>
                        <td>
                            <%= log.getLoginTime() %>
                        </td>
                        <td>
                            <% if (log.getLogoutTime() == null) {
                                out.println("Chưa logout");
                            } else {
                                out.println(log.getLogoutTime());
                            }
                            %>
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
</html>
