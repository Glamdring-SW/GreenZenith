<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
%>
<jsp:forward page="login.jsp"/>
<%
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesprofile.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5 d-flex justify-content-center">
            <div class="profile-container text-center">
                <div class="profile-pic mx-auto mb-3">
                    <img src="data:image/png;base64, <%= user.getBase64Picture()%>" alt="Profile Picture" class="img-fluid responsive-img profile-photo-preview">
                </div>
                <h2 class="profile-name"><%= user.getName()%></h2>
                <a href="editprofile.jsp" class="btn btn-outline-light btn-outline-light-custom">EDITAR PERFIL</a>
                <a href="plantsexplore.jsp" class="btn btn-outline-light btn-outline-light-custom">MIS PLANTAS</a>
                <a href="productsexplore.jsp" class="btn btn-outline-light btn-outline-light-custom">MIS PRODUCTOS</a>
                <form action="Handlers/logOut.jsp" method="post">
                    <input type="submit" value="CERRAR SESION" class="btn btn-outline-light btn-outline-light-custom">
                </form>

                <!-- -->
                <!--
                <div class="social-icons mt-4">
                    <a href="#"><img src="https://cdn-icons-png.flaticon.com/512/2111/2111463.png" alt="Instagram"></a>
                    <a href="#"><img src="https://cdn-icons-png.flaticon.com/512/733/733547.png" alt="Facebook"></a>
                    <a href="#"><img src="https://cdn-icons-png.flaticon.com/512/733/733579.png" alt="Twitter"></a>
                </div>
                -->
            </div>
        </div>

    </body>
</html>
